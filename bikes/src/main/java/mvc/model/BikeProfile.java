package mvc.model;

import java.rmi.Remote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BikeProfile extends JpaRepository<Bike, Long>, Remote {
		
		@Query("Select b from Bike b where b.brand=?1") //?1 pentru primul parametru
		
		Bike findbyBrand(String brand);
	}
