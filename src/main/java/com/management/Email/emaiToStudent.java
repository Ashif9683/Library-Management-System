package com.management.Email;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.Dao.BookIssueRepository;
import com.management.Dao.RegisterRepository;
import com.management.entities.BookIssue;
import com.management.entities.Student;

@Controller
public class emaiToStudent {
	
	@Autowired
	BookIssueRepository bookissueRepository;
	
	@Autowired
	RegisterRepository registerRepository;
	
	@GetMapping("/sendMail")
	@ResponseBody
	public String sendMail() {
		
		List<BookIssue> duebook= this.bookissueRepository.findallbookissuewithDue("Book Issue" ,"Due Book");	
		
		for(BookIssue book : duebook) {
			Student st  =  this.registerRepository.getstudentrecord(book.getStudentfk());
			System.out.println("preparing to send message ...");
			String message = "Hello Dear, "+ st.getFullName() + " for testing purpose to email send the Nitra Library Management System " 
					+ " book name : " +book.getSelectbook() + " fine in library FINE : "+ book.getFine() + ".";
			String subject = "Nitra Library Management System";
			String to = st.getEmail();
			String from = "ak3839620@gmail.com";

			sendEmail(message, subject, to, from);
		}
		

		return "send mail success !! ";
	}

	private static void sendEmail(String message, String subject, String to, String from) {

		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES " + properties);

		// setting important information to properties object

		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step 1: to get the session object..
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ak3839620@gmail.com", "ibfd uzlv nqfn dgdg");
			}

		});

		session.setDebug(true);

		// Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);

		try {

			// from email
			m.setFrom(from);

			// adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// adding text to message
			m.setText(message);

			// send

			// Step 3 : send the message using Transport class
			Transport.send(m);

			System.out.println("Sent success...................");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
