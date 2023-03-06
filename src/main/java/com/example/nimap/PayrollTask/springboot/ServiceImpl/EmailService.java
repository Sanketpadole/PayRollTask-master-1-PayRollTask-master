package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.nimap.PayrollTask.springboot.Entities.Users;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public String sendMail(String emailTo, String subject, String text, Users users) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("sanketpadole84@gmail.com");
		simpleMailMessage.setTo(users.getEmail());
		simpleMailMessage.setSubject("Apply sucessfully");
		simpleMailMessage.setText("Text demo");
		javaMailSender.send(simpleMailMessage);
		return "Email Send";
	}

	public void sendSimpleMessage(String emailTo, String subject, String text) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("sanketpadole84@gmail.com");

		message.setTo(emailTo);
		message.setSubject(subject);
		message.setText(text);

		javaMailSender.send(message);

	}

	public int generateOTP() {

		int min = 1000;
		int max = 9999;

		int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
		return random_int;

	}
}
