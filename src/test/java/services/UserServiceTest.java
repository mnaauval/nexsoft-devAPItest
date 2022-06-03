package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import model.User;

public class UserServiceTest {
	
	/**
	 * test
	 * clean
	 * install
	 * surefire-report:report
	 * clean install test surefire-report:report
	 */
	
	User user;
	UserService userService;
	String firstName, lastName;
	String email;
	String password, confirmPassword;
	String expectedExceptionMessage;
	
	@BeforeEach
	public void init() {
		userService = new UserServiceImplementation();
		firstName = "Eka"; 
		lastName = "Nauval";
		email = "mnaauval20@gmail.com";
		password = "kepodeh"; 
		confirmPassword = "kepodeh";
		expectedExceptionMessage = "";
	}
	
	@DisplayName("Create user object")
	@Test
//	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testCreateUser_WhenDetailIsProvide_ReturnUserObject() {
		// arrange
		
		// act
		user = userService.createUser(firstName, lastName, email, password, confirmPassword);
		
		
		// assert
		assertNotNull(user, "if create user success should not return null");
		assertEquals(firstName, user.getFirstName(), "User first name is incorrect");
		assertEquals(lastName, user.getLastName(), "User last name is incorrect");
		assertEquals(email, user.getEmail(), "User emaill is incorrect");
		assertNotNull(user.getId(), "User id cannot null");
		/*assertTimeout(Duration.ofMillis(500), () -> {			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});*/
	}
	
	@DisplayName("Empty first name cause exception")
	@Test
	public void testCreateUser_WhenFirstNameIsEmpty_ThrowsIllegalException() {
		// arrange
		firstName = "";
		expectedExceptionMessage = "User first name is empty";
		
		// act
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			userService.createUser(firstName, lastName, email, password, confirmPassword);
		}, "Empty first name cause Illegal Argument Exception");
		
		// assert
		assertEquals(expectedExceptionMessage, thrown.getMessage());
	}
	
	@DisplayName("Empty last name cause exception")
	@Test
	public void testCreateUser_WhenLastNameIsEmpty_ThrowsIllegalException() {
		// arrange
		lastName = "";
		expectedExceptionMessage = "User last name is empty";
		
		// act
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			userService.createUser(firstName, lastName, email, password, confirmPassword);
		}, "Empty last name cause Illegal Argument Exception");
		
		// assert
		assertEquals(expectedExceptionMessage, thrown.getMessage());
	}
}
