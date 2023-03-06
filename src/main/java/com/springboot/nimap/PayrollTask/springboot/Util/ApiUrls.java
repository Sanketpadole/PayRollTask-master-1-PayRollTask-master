package com.springboot.nimap.PayrollTask.springboot.Util;

public class ApiUrls {
	public static final String API = "/api";
	public static final String GET_ALL = "/all";
	public static final String USERS = "/users";
	public static final String PERMISSIONS = "/permissions";
	public static final String ROLES = "/roles";
	public static final String TOPIC = "/topics";
	public static final String USERTECHNOLOGIES = "/usertechnologies";
	public static final String TECHNOLOGIES = "/technologies";
	public static final String ROLEPERMISSION = "/role-permission";
	public static final String AUTH = "/auth";
	public static final String LOGIN = AUTH + "/login";
	public static final String REGISTER = AUTH + "/register";
	public static final String FORGOT_PASSWORD = AUTH + "/forgot-password";
	public static final String FORGOT_PASSWORD_CONFIRM = AUTH + "/forgot-password/confirm";
	public static final String LOGIN_WITH_OTP = AUTH + "/login-with-otp";
	public static final String LOGIN_WITH_OTP_CONFIRM = AUTH + "/login-with-otp/confirm";
	public static final String UPDATE_EMAIL = AUTH + "/update-email";
	public static final String UPDATE_EMAIL_CONFIRM = AUTH + "/update-email/confirm";
	public static final String CATEGORY = "/category";
	public static final String TASKS = "/tasks";
	public static final String ASSIGNMENT = "/assignment";
	public static final String SUB_TOPIC = "/sub_topic";
	public static final String USER_TOPIC_HISTORY = "/user-topic-history";
	public static final String USER_TOPIC = "/user-topic";
	public static final String USER_MENTOR = "/user-mentor";
	public static final String DOWNLOAD_FILE = "/{fileName:.+}";
	public static final String USER_ASSIGNMENT = "/user-assignment";
	public static final String USER_ASSIGNMENT_History = "/user-assignment-history";
	public static final String[] SWAGGER_URLS = { "/v3/api-docs", "/v2/api-docs", "/swagger-resources/**",
			"/swagger-ui/**", "/webjars/**", "/api/swagger-ui/index.html" };

	public static final String[] URLS_WITHOUT_HEADER = { ApiUrls.API + ApiUrls.LOGIN, ApiUrls.API + ApiUrls.REGISTER,
			ApiUrls.API + ApiUrls.FORGOT_PASSWORD, ApiUrls.API + ApiUrls.FORGOT_PASSWORD_CONFIRM,
			ApiUrls.API + ApiUrls.LOGIN_WITH_OTP, ApiUrls.API + ApiUrls.LOGIN_WITH_OTP_CONFIRM };

}



































//// Important
////2nd point Admin filter
//
//// ii. Can see a list of all registered candidates, recruiters and jobs. All the
//// lists should be paginated.
//@PreAuthorize("hasRole('candidaterecruiterview')")
//@GetMapping("/candidaterecruiterview")
//public ResponseEntity<?> getAllTasks(@RequestParam(required = false) String Candidate,
//	@RequestParam(required = false) String recruiter, String Search,
//	@RequestParam(defaultValue = "1") String pageNumber, @RequestParam(defaultValue = "20") String pageSize) {
//
//Page<IUserRoleListDto> allRoles = userJobIntf.getAlltasks11(Candidate, recruiter, Search, pageNumber, pageSize);
//
//if (allRoles.getTotalElements() != 0) {
//
//	return new ResponseEntity<>(allRoles.getContent(), HttpStatus.OK);
//}
//return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("not found", "not found", null),
//		HttpStatus.BAD_REQUEST);
//}
//
//}
//
////Admin 3rd point
//// can see which candidate applied to which job
//@PreAuthorize("hasRole('candidatejobview')")
//@GetMapping("/candidatejobView")
//ResponseEntity<?> getallcandidateJob(@RequestParam(defaultValue = "") String Search,
//	@RequestParam(defaultValue = "1") String pageNumber, @RequestParam(defaultValue = "20") String pageSize) {
//Page<UserJobListDto> userjob = userJobIntf.getallcandidateJob(pageNumber, pageSize);
//if (userjob.getTotalElements() != 0) {
//
//	return new ResponseEntity<>(userjob.getContent(), HttpStatus.OK);
//
//}
//
//return new ResponseEntity<>(new ErrorResponseDto("", "Failed!!"), HttpStatus.BAD_REQUEST);
//
//}

//// Important
////2nd point Admin filter
//
//// ii. Can see a list of all registered candidates, recruiters and jobs. All the
//// lists should be paginated.
//@PreAuthorize("hasRole('candidaterecruiterview')")
//@GetMapping("/candidaterecruiterview")
//public ResponseEntity<?> getAllTasks(@RequestParam(required = false) String Candidate,
//	@RequestParam(required = false) String recruiter, String Search,
//	@RequestParam(defaultValue = "1") String pageNumber, @RequestParam(defaultValue = "20") String pageSize) {
//
//Page<IUserRoleListDto> allRoles = userJobIntf.getAlltasks11(Candidate, recruiter, Search, pageNumber, pageSize);
//
//if (allRoles.getTotalElements() != 0) {
//
//	return new ResponseEntity<>(allRoles.getContent(), HttpStatus.OK);
//}
//return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("not found", "not found", null),
//		HttpStatus.BAD_REQUEST);
//}
//
//}

////Important candidate 6th point
//@PreAuthorize("hasRole('userjobsview3')")
//@GetMapping("/{Id}/userjobsview3")
//public ResponseEntity<?> getuserJobById(@PathVariable Long Id) {
//List<UserJobListDto> iJobUserTaskDto = this.userJobIntf.getuserJobById(Id);
//
//return new ResponseEntity<>(new SuccessResponseDto("Tasks assign to user", "Successfull", iJobUserTaskDto),
//		HttpStatus.OK);
//
//}

//@GetMapping("/nikhil")
//ResponseEntity<?> getallUsersAndJobs(@RequestParam(defaultValue = "") String Search,
//	@RequestParam(defaultValue = "1") String pageNumber, @RequestParam(defaultValue = "20") String pageSize) {
//
//Page<UserJobListDto> userjob = userJobIntf.getallUsersAndJobs(pageNumber, pageSize);
//if (userjob.getTotalElements() != 0) {
//
//	return new ResponseEntity<SuccessResponseDto>(
//			new SuccessResponseDto("Success", "Success", userjob.getContent()), HttpStatus.OK);
//
//}
//
//return new ResponseEntity<>(new ErrorResponseDto("", "Failed!!"), HttpStatus.BAD_REQUEST);
//
//}

////4th point recruiter
////@PreAuthorize("hasRole('candidatejob1View')")
//@GetMapping("/getbyid")
//
//ResponseEntity<?> getalluserJob9(HttpServletRequest request, @RequestParam(defaultValue = "") String Search,
//
//	@RequestParam(defaultValue = "1") String pageNumber, @RequestParam(defaultValue = "20") String pageSize) {
//System.out.println("ajbbb");
//Page<UserJobListDto> userjob = userJobIntf.getalluserJob9(request, pageNumber, pageSize);
//
//if (userjob.getTotalElements() != 0) {
//
//	return new ResponseEntity<>(userjob.getContent(), HttpStatus.OK);
//
//}
//
//return new ResponseEntity<>(new ErrorResponseDto("", "Failed!!"), HttpStatus.BAD_REQUEST);
//
//}

//@PreAuthorize("hasRole('userjobsview4')")
//@GetMapping("/{Id}/userjobsview4")
//public ResponseEntity<?> getuserJobById1(@PathVariable Long Id) {
//List<UserJobListDto> iJobUserTaskDto = this.userJobIntf.getuserJobById1(Id);
//
//return new ResponseEntity<>(new SuccessResponseDto("Tasks assign to user", "Successfull", iJobUserTaskDto),
//		HttpStatus.OK);
//
//}
//***Important

////Candidate
////###vi. See a list of jobs they have applied to (order by recent jobs applied). Server side (at DB )pagination should be enabled.
//@GetMapping("/userjobsview5")
//public ResponseEntity<?> getuserJobById2(HttpServletRequest request) {
//System.out.println("asbhbhj");
//List<UserJobListDto> iJobUserTaskDto = this.userJobIntf.getuserJobById2( request);
//
//return new ResponseEntity<>(new SuccessResponseDto("Tasks assign to user", "Successfull", iJobUserTaskDto),
//		HttpStatus.OK);
//
//}

//@PreAuthorize("hasRole('userjobAdd')")
//@PostMapping("/suraj")
//ResponseEntity<?> addUserJob(@RequestBody UserJobDto userJobDto) {
//
//userJobIntf.addUserJob1(userJobDto);
//return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", userJobDto),
//		HttpStatus.ACCEPTED);
//
//}

////Admin 3rd point
//// can see which candidate applied to which job
//@PreAuthorize("hasRole('candidatejobView')")
//@GetMapping("/candidatejobView")
//ResponseEntity<?> getallcandidateJob(@RequestParam(defaultValue = "") String Search,
//	@RequestParam(defaultValue = "1") String pageNumber, @RequestParam(defaultValue = "20") String pageSize) {
//Page<UserJobListDto> userjob = userJobIntf.getallcandidateJob(pageNumber, pageSize);
//if (userjob.getTotalElements() != 0) {
//
//	return new ResponseEntity<>(userjob.getContent(), HttpStatus.OK);
//
//}
//
//return new ResponseEntity<>(new ErrorResponseDto("", "Failed!!"), HttpStatus.BAD_REQUEST);
//
//}
////Important
////2nd point Admin filter
//
////ii. Can see a list of all registered candidates, recruiters and jobs. All the lists should be paginated. 
//@PreAuthorize("hasRole('candidaterecruiterView')")
//@GetMapping("/candidaterecruiterView")
//public ResponseEntity<?> getAllTasks(@RequestParam(required = false) String Candidate,
//	@RequestParam(required = false) String recruiter, String Search,
//	@RequestParam(defaultValue = "1") String pageNumber, @RequestParam(defaultValue = "20") String pageSize) {
//
//Page<IUserRoleListDto> allRoles = userJobIntf.getAlltasks11(Candidate, recruiter, Search, pageNumber, pageSize);
//
//if (allRoles.getTotalElements() != 0) {
//
//	return new ResponseEntity<>(allRoles.getContent(), HttpStatus.OK);
//}
//return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("not found", "not found", null),
//		HttpStatus.BAD_REQUEST);
//}
//
//}
