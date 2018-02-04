package pl.lukaszwilk.demo.repo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.Entity;

//dzieki jsr310 jpa jest wstanie przekonwertowac czas podany w bazie na nowoczesna clase LocalTime
@EntityScan(basePackageClasses = {DemoRepoApplication.class, Jsr310JpaConverters.class})
@SpringBootApplication
public class DemoRepoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRepoApplication.class, args);
	}
}
