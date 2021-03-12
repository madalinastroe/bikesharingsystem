package mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mvc.NotificationService;
import mvc.model.Bike;
import mvc.model.BikeProfile;
import mvc.model.User;
import mvc.model.UserProfile;





@Controller
public class AppController {
	
	private Logger logger=LoggerFactory.getLogger(AppController.class);
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private UserProfile repo;
	
	@Autowired
	private BikeProfile repoBike;
	
	@GetMapping("/index")
	public String viewHomePage()
	{
		return "index";
	}
	
	

	@GetMapping("/register")
	public String showSignUpForm(Model model)
	{
		model.addAttribute("user",new User());
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user)
	{
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String encodedPass=encoder.encode(user.getPassword());
		user.setPassword(encodedPass);
		repo.save(user);
		
		//User user= new User();
		user.setEmail(user.getEmail());
		user.setFirstName(user.getFirstName());
		user.setLastName(user.getLastName());
		
		try
		{
			notificationService.sendNotification(user);
			
		}catch(MailException e)
		{
			logger.info("Error sending message: "+e.getMessage());
		}
		
		return "register_succes";
	}
	
	@GetMapping("/list_users")
	public String viewUsersList(Model model)
	{
		List<User> listUsers=repo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
	
	@GetMapping("/list_bikes")
	public String viewBikesList(Model model)
	{
		List<Bike> listBikes=repoBike.findAll();
		model.addAttribute("listBikes", listBikes);
		return "bikes";
	}
	
}
