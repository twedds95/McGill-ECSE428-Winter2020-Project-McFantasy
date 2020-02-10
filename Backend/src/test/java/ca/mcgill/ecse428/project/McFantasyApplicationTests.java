package ca.mcgill.ecse428.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class McFantasyApplicationTests {

	@Test
	void contextLoads() {
	}
	/*
	 * Limitations of testing include: not testing the REST API, and limited testing due to the nature of the update methods
	 * Additionally, this is commented out as it seems that the testing class doesn't have visibility to the other classes
	 @Test
	    public void TestUpdatesAppUser() {// MyClass is tested
	        byte[] bytes;
	        AppUser tester1 = service.createUser("testemail","testname","testpassword",bytes);
			
			try {
				AppUser tester = service.updateUser("","testname2","testpassword2",bytes);
			} catch (IllegalArgumentException e) {
				error = e.getMessage();
				}
	        assertEquals("User email cannot be empty!",error);
	        
	        try {
				AppUser tester = service.updateUser("testemail2","testname2","testpassword2",bytes);
			} catch (IllegalArgumentException e) {
				error1 = e.getMessage();
				}
	        assertEquals("No user with this email exists!",error1);
	    }*/
}
