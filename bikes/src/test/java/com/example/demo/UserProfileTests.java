package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.rmi.RemoteException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import mvc.model.User;
import mvc.model.UserProfile;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserProfileTests {
	
	@Autowired
	private UserProfile repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		User user=new User();
		user.setEmail("alex@yahoo.com");
		user.setFirstName("ale");
		user.setLastName("iuhu");
		user.setPhoneNumber("256");
		user.setPassword("pass");
		user.setPayment("credit card");
		
		User savedUser = repo.save(user);
		
		User existUser = entityManager.find(User.class, savedUser.getId());
		
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	@Test
	public void testFindUserByEmail() throws RemoteException
	{
		String email = "madalinastroe35@gmail.com";
		User user=repo.findbyEmail(email);
		
		assertThat(user).isNotNull();
	}
	

}
