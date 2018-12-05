package com.example;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cognizant.idw.nosqldb.DBEngine;

@Controller
public class SignupController {
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request) {
		System.out.println("Entring login controller");
		String email = request.getParameter("email");
		String[] emailtrim =email.split("@"); 
		String emailid=emailtrim[0];
		String nickname = request.getParameter("nickname");
		String buddyname = request.getParameter("buddyname");
		String mobileno = request.getParameter("mobileno");
		
		
		//connect to a db
		//System.out.println(emailid);
		
		//user sign up
		User user = new User();
		user.setEmail(emailid);
		user.setBuddyname(buddyname);
		user.setNickname(nickname);
		user.setMobileno(mobileno);
		
		try {
			DBEngine.insertData("User", emailid, user, "E:/Sid/NoSQLDBDataStore");
			
			User user1 = (User) DBEngine.getData("User", emailid , "E:/Sid/NoSQLDBDataStore");
			System.out.println("Email Id: "+user1.getEmail());
			System.out.println("Nick Name: "+user1.getNickname());
			System.out.println("Buddy Name: "+user1.getBuddyname());
			System.out.println("Mobile No: "+user1.getMobileno());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "landing";
	}
}
