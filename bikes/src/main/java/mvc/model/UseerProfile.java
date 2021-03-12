package mvc.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.springframework.data.jpa.repository.Query;

public interface UseerProfile extends Remote{
	@Query("Select u from User u where u.email=?1") //?1 pentru primul parametru
	
	User findbyEmail(String email) throws RemoteException;
	
}
