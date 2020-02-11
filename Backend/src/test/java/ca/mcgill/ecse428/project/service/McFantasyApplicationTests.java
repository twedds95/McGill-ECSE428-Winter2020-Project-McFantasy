package ca.mcgill.ecse428.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.mcgill.ecse428.project.dao.AppUserRepository;
import ca.mcgill.ecse428.project.model.AppUser;



@RunWith(MockitoJUnitRunner.class)
public class McFantasyApplicationTests {

	@Mock
	private AppUserRepository userDao;
	
	@InjectMocks
	private McFantasyService service;
	
	private static String USER_NAME = "test_user";
	private static String USER_EMAIL = "test@mail.com";
	private static String USER_PASSWORD = "password123";
	private static byte[] USER_PICTURE = {'1'};
	private static String NON_EXISTING_EMAIL = "nonexist@mail.com";
	
	
	
	
	@Before
	public void setMockOutput() {
		when(userDao.findByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(USER_EMAIL)) {
				AppUser user = new AppUser();
				user.setName(USER_NAME);
				user.setEmail(USER_EMAIL);
				user.setPassword(USER_PASSWORD);
				user.setProfilePicture(USER_PICTURE);
				return user;
			} else {
				return null;
			}
		});
	}
	
	@Test
	public void testCreateUser() {
		AppUser user = new AppUser();
		try {
			user = service.createUser(USER_EMAIL, USER_NAME, USER_PASSWORD, USER_PICTURE);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(USER_EMAIL, user.getEmail());
	}
	
	@Test
	public void testCreateUserNullEmail() {
		String error = null;
		try {
			@SuppressWarnings("unused")
			AppUser user = service.createUser(null, USER_NAME, USER_PASSWORD, USER_PICTURE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error, "User email cannot be empty!");
	}
	
	@Test
	public void testGetUserByExistingName() {
		AppUser user = service.getUser(USER_EMAIL);
		assertEquals(USER_EMAIL, user.getEmail());
	}
	
	@Test
	public void testGetUserByNonExistingName() {
		AppUser user = service.getUser(NON_EXISTING_EMAIL);
		assertNull(user);
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
