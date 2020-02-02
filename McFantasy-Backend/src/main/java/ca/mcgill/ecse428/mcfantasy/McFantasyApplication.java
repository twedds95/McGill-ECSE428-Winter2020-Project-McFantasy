package ca.mcgill.ecse428.mcfantasy;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class McFantasyApplication {

	public static void main(String[] args) {
		SpringApplication.run(McFantasyApplication.class, args);
	}

	@RequestMapping("/")
  	public String greeting(){
    		return "Hello world!";
  }

}