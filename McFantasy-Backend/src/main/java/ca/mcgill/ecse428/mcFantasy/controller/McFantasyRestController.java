package ca.mcgill.ecse428.mcfantasy.controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ca.mcgill.ecse428.mcfantasy.model.AppUser;
import ca.mcgill.ecse428.mcfantasy.service.McFantasyService;

@CrossOrigin(origins = "*")
@RestController
public class McFantasyRestController {
	
	@Autowired
	private McFantasyService service;
	
	@PostMapping(value = { "/user/{email}", "/user/{email}/" })
	public AppUser createUser(@PathVariable("email") String email, 
			@RequestParam(name = "name") String name, 
			@RequestParam(name = "password") String password,
			@RequestParam(name = "picture") MultipartFile picfile) throws IllegalArgumentException, SerialException, SQLException, IOException {
		
		byte[] bytes;
		if (picfile.getSize() > 0) {
			bytes = picfile.getBytes();
		} else {
			bytes = null;
		}
	
		
		AppUser user = service.createUser(email, name, password, bytes);
		return user;
	}
	
//	@GetMapping(value = { "/user/{email}", "/user/{email}/" })
//	public AppUser getUser(@PathVariable("email") String email) {
//		ByteArrayInputStream bis = new ByteArrayInputStream();
//	    BufferedImage bImage2 = ImageIO.read(bis);
//	    ImageIO.write(bImage2, "jpg", new File("output.jpg") );
//	
//	}
	
	
}
