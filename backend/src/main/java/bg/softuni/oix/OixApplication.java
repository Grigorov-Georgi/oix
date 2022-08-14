package bg.softuni.oix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OixApplication {

	public static void main(String[] args) {
		SpringApplication.run(OixApplication.class, args);
	}

}
