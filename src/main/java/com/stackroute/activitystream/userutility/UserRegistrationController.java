package com.stackroute.activitystream.userutility;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserRegistrationController 
{
	@Autowired
	UserRegistrationDAO userRegistrationDAO;
	@PostMapping(value="/userRegister")
	public ResponseEntity<UserRegistration> userRegister(@RequestBody UserRegistration userRegistration)
	{
		if(userRegistrationDAO.addUser(userRegistration))
		{
			userRegistration.statusCode="1031";
			userRegistration.statusDesc="Successfully Registered";
		}
		else
		{
			userRegistration.statusCode="1032";
			userRegistration.statusDesc="Error happened while Registering.";
		}
		
		return new ResponseEntity<UserRegistration>(userRegistration,HttpStatus.OK);
	}
	
	@PostMapping(value="/checkLogin")
	public ResponseEntity<UserRegistration> checkLoginCredential(@RequestBody UserRegistration userRegistration)
	{
		
		if(userRegistrationDAO.validateUser(userRegistration))
		{
			userRegistration.statusCode="1033";
			userRegistration.statusDesc="Successfully Login";
		}
		else
		{
			userRegistration.statusCode="1034";
			userRegistration.statusDesc="Invalid Login ID and Password";
		}
		return new ResponseEntity<UserRegistration>(userRegistration,HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value="/updateUser")
	public ResponseEntity<UserRegistration> updateUser(@RequestBody UserRegistration userRegistration)
	{
		
		if(userRegistrationDAO.updateUser(userRegistration))
		{
			userRegistration.statusCode="1035";
			userRegistration.statusDesc="Successfully Updated";
		}
		else
		{
			userRegistration.statusCode="1036";
			userRegistration.statusDesc="Error happened While Updated";
		}
		return new ResponseEntity<UserRegistration>(userRegistration,HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value="/deleteUser")
	public ResponseEntity<UserRegistration> deleteUser(@RequestBody UserRegistration userRegistration)
	{
		
		if(userRegistrationDAO.deleteUser(userRegistration))
		{
			userRegistration.statusCode="1037";
			userRegistration.statusDesc="Successfully User Deleted";
		}
		else
		{
			userRegistration.statusCode="1038";
			userRegistration.statusDesc="Error Happened While Deleting";
		}
		return new ResponseEntity<UserRegistration>(userRegistration,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="/getAllUsers")
	public ResponseEntity<List<UserRegistration>> getAllUsers()
	{
		
		List<UserRegistration> listUsers=userRegistrationDAO.getAllUser();
		return new ResponseEntity<List<UserRegistration>>(listUsers,HttpStatus.ACCEPTED);
	}
}
