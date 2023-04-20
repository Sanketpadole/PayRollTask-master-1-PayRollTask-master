package com.example.nimap.PayrollTask.springboot.Controllers;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nimap.PayrollTask.springboot.Dto.AuthResponseDto;
import com.example.nimap.PayrollTask.springboot.Dto.ErrorResponseDto;
import com.example.nimap.PayrollTask.springboot.Dto.LoggerDto;
import com.example.nimap.PayrollTask.springboot.Dto.ModelDto;
import com.example.nimap.PayrollTask.springboot.Dto.OTPDto;
import com.example.nimap.PayrollTask.springboot.Dto.SuccessResponseDto;
import com.example.nimap.PayrollTask.springboot.Dto.UsersDto;
import com.example.nimap.PayrollTask.springboot.Entities.OtpEntity;
import com.example.nimap.PayrollTask.springboot.Entities.Users;
import com.example.nimap.PayrollTask.springboot.Repository.AuthRepository;
import com.example.nimap.PayrollTask.springboot.Repository.OTPRepository;
import com.example.nimap.PayrollTask.springboot.Security.JwtTokenUtil;
import com.example.nimap.PayrollTask.springboot.ServiceImpl.AuthServiceImpl;
import com.example.nimap.PayrollTask.springboot.ServiceImpl.EmailService;
import com.example.nimap.PayrollTask.springboot.ServiceImpl.OTPServiceImpl;
import com.example.nimap.PayrollTask.springboot.Services.AuthInterface;
import com.example.nimap.PayrollTask.springboot.Services.LoggerService;
import com.springboot.nimap.PayrollTask.springboot.Util.PasswordValidator;

@RestController
@RequestMapping("/auth")

public class AuthController {
	@Autowired
	private AuthInterface authInterface;

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private AuthServiceImpl authServiceImpl;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private LoggerService loggerService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private OTPServiceImpl OtpserviceImpl;

	@Autowired
	private OTPRepository otpRepository;

	@PostMapping("/register")

	ResponseEntity<?> registerUser(@RequestBody UsersDto usersDto) {

		String password = usersDto.getPassword();
		String email = usersDto.getEmail();

		if (PasswordValidator.isValid(password) && PasswordValidator.isValidforEmail(email)) {

			Users users = authRepository.findByEmail(usersDto.getEmail());

			if (users == null) {

				authInterface.registerUser(usersDto);

				return new ResponseEntity<>(new SuccessResponseDto("User created", "userCreated", "Data added"),
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(
						new ErrorResponseDto("User email id already exist", "userEmailIdAlreadyExist"),
						HttpStatus.CONFLICT);
			}
		}

		else {

			return new ResponseEntity<>(new ErrorResponseDto(
					"Password should have Minimum 8 and maximum 50 characters, at least one uppercase letter, one lowercase letter, one number and one special character and no white spaces",
					"Password validation not done"), HttpStatus.BAD_REQUEST);

		}

	}

	@PostMapping("/login")

	ResponseEntity<?> loginUser(@RequestBody UsersDto usersDto) {

		try {
			Users users = authRepository.findByEmail(usersDto.getEmail());

			String password = usersDto.getPassword();
			if (this.authServiceImpl.comparePassword(password, users.getPassword())) {

				final UserDetails userDetails = this.authServiceImpl.loadUserByUsername(usersDto.getEmail());

				users = this.authRepository.findByEmail(usersDto.getEmail());
				final String token = jwtTokenUtil.generateToken(userDetails);
				String refreshToken = jwtTokenUtil.refreshToken(token, userDetails);

				LoggerDto logger = new LoggerDto();
				logger.setToken(token);
				Calendar calender = Calendar.getInstance();
				calender.add(Calendar.HOUR_OF_DAY, 5);
				logger.setExpireAt(calender.getTime());
				this.loggerService.createlogger(logger, users);
				return new ResponseEntity<SuccessResponseDto>(
						new SuccessResponseDto("Token Created", "Token", new AuthResponseDto(token, refreshToken)),
						HttpStatus.ACCEPTED);
			} else {
				throw new Exception("Password not matched");

			}
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponseDto>(
					new ErrorResponseDto("Invalid Email Or password", "Please enter valid password"),
					HttpStatus.ACCEPTED);

		}

	}

	@PostMapping("/forgotpass")
	ResponseEntity<?> forgotpassword(@RequestBody OTPDto otpDto, HttpServletRequest request) {

		try {
			Users users = authRepository.findByEmail(otpDto.getEmail());

			final int otp = emailService.generateOTP();

			final String url = "OTP For Forgot Email Is-" + otp;
			Calendar calender = Calendar.getInstance();
			calender.add(Calendar.MINUTE, 5);
			otpDto.setOtp(otp);

			otpDto.setSetExpiry(calender.getTime());

			this.OtpserviceImpl.saveOtp(otpDto, users);

			this.emailService.sendSimpleMessage(users.getEmail(), "subject", url);
			return ResponseEntity
					.ok(new SuccessResponseDto("OTP send user email", "OTP send succesfully", users.getEmail()));

		} catch (Exception e) {

			return ResponseEntity.ok(new ErrorResponseDto("User not found", "Sorry !!"));

		}
	}

	@PutMapping("/forgot-pass-confirms")
	public ResponseEntity<?> createForgotPasswordConfirm(@RequestBody ModelDto modelDto) throws Exception {

		try {

			Users users = this.authRepository.findByEmail(modelDto.getEmail());

			if (null == users) {
				return new ResponseEntity<>(new ErrorResponseDto("Invalid email ", "Please enter registered email "),
						HttpStatus.BAD_REQUEST);
			}

			OtpEntity otpEntity = this.otpRepository.findByOtp(modelDto.getOtp());

			if (null == otpEntity) {
				return new ResponseEntity<>(new ErrorResponseDto("Invalid OTP", "Please enter valid otp"),
						HttpStatus.BAD_REQUEST);
			} else {
				if (!otpEntity.getEmail().equals(modelDto.getEmail())) {
					return new ResponseEntity<>(new ErrorResponseDto("Invalid email or otp", "invalid email or otp"),
							HttpStatus.BAD_REQUEST);
				}
			}

			if (!PasswordValidator.isValid(modelDto.getPassword())) {
				return new ResponseEntity<>(new ErrorResponseDto(
						"Password should have Minimum 8 and maximum 50 characters, at least one uppercase letter, one lowercase letter, one number and one special character and no white spaces",
						"Password validation not done"), HttpStatus.BAD_REQUEST);
			}

			this.authServiceImpl.updateUserwithPassword(modelDto, users, otpEntity);

			return ResponseEntity.ok("password updated successfully");
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "Not found"), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/logout")

	public ResponseEntity<?> logoutUser(@RequestHeader("Authorization") String token, HttpServletRequest request)
			throws Exception {

		loggerService.logoutUser(token);
		return new ResponseEntity<>(new ErrorResponseDto("Logout Successfully", "logoutSuccess"), HttpStatus.OK);

	}

	@PostMapping("/refreshTokens")
	public ResponseEntity<?> refreshAndGetAuthenticationToken(@RequestParam(defaultValue = "") String refreshToken)
			throws Exception {
		String email = jwtTokenUtil.getUsernameFromToken(refreshToken);
		Users users = authRepository.findByEmail(email);
		if (users == null) {
			return new ResponseEntity<>(new ErrorResponseDto("Invalid user", "Invalid user"), HttpStatus.UNAUTHORIZED);
		}
		final UserDetails userDetails = authInterface.loadUserByUsername(email);
		if (jwtTokenUtil.canTokenBeRefreshed(refreshToken) && jwtTokenUtil.validateToken(refreshToken, userDetails)
				&& jwtTokenUtil.getTokenType(refreshToken).equalsIgnoreCase("refresh")) {
			String newAccessToken = jwtTokenUtil.generateToken(userDetails);
			LoggerDto logger = new LoggerDto();

			this.loggerService.createlogger(logger, users);
			return new ResponseEntity<>(new SuccessResponseDto("Access token", "Successfull", newAccessToken),
					HttpStatus.OK);

		} else {
			return new ResponseEntity<>(new ErrorResponseDto("Invalid token", "Invalid token"),
					HttpStatus.UNAUTHORIZED);

		}
	}

//	public ResponseEntity<?> refreshAndGetAuthenticationToken(@RequestParam(defaultValue = "") String refreshToken)
//            throws Exception {
//
//        String email = jwtTokenUtilInterface.getUsernameFromToken(refreshToken);
//        UserEntity userEntity = authRepository.findByEmailIgnoreCaseAndIsActiveTrue(email);
//        if (userEntity == null) {
//            return new ResponseEntity<>(new ErrorResponseDto("Invalid user", "Invalid user"), HttpStatus.UNAUTHORIZED);
//        }
//        final UserDetails userDetails = authInterface.loadUserByUsername(email);
//        if (jwtTokenUtilInterface.canTokenBeRefreshed(refreshToken)
//                && jwtTokenUtilInterface.validateToken(refreshToken, userDetails)
//                && jwtTokenUtilInterface.getTokenType(refreshToken).equalsIgnoreCase("refresh")) {
//
//            String newAccessToken = jwtTokenUtilInterface.generateToken(userDetails);
//            LoggerDto loggerDto = new LoggerDto();
//
//            this.loggerInterface.createLogger(loggerDto, userEntity);
//            return new ResponseEntity<>(new SuccessResponseDto("Access token", "Successfull", newAccessToken),
//                    HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(new ErrorResponseDto("Invalid token", "Invalid token"),
//                    HttpStatus.UNAUTHORIZED);
//
//        }

}
