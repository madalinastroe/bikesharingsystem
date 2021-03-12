package mvc;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {

		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String rawPassword="alo";
		String encodedPass=encoder.encode(rawPassword);
		
		System.out.println(encodedPass);
	}

}
