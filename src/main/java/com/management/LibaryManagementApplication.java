package com.management;

import java.awt.Desktop;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.URI;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.sound.sampled.Port;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.management.Email.emaiToStudent;
import com.management.controller.BookIssueController;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@SpringBootApplication
public class LibaryManagementApplication extends SpringBootServletInitializer {

	@Autowired
	private ServerProperties serverproperties;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	emaiToStudent emaitostudent;
	
	@Autowired
	BookIssueController bookissuecontroller;
	
	public static void main(String[] args) {
		SpringApplication.run(LibaryManagementApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		// update fine book charges all book issue 
		this.bookissuecontroller.findAllBookIssue();
		this.emaitostudent.sendMail();
	}

	@Component
	class BrowserOpener implements ApplicationListener<ContextRefreshedEvent> {
		@Override
		public void onApplicationEvent(ContextRefreshedEvent event) {
			// Open the default web browser when the application context is refreshed
			try {
				InetAddress localHost = InetAddress.getLocalHost();
				int port = serverproperties.getPort();
				String hostname = localHost.getHostName();
				String ip = localHost.getHostAddress();
				System.out.println("Hostname: " + hostname + "--ip---" + ip);
				openWebpage(new URI("http://" + hostname + ":" + port + "/userhome"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void openWebpage(URI uri) throws Exception {
		Runtime rt = Runtime.getRuntime();
		rt.exec("rundll32 url.dll,FileProtocolHandler " + uri.toString());
	}
	
	
	
}
