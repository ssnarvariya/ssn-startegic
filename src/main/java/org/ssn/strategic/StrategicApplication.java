package org.ssn.strategic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories("org.ssn.startegic.persistence.repository") 
//@EntityScan("org.ssn.startegic.persistence.model")
public class StrategicApplication {
	public static void main(String[] args) {
		SpringApplication.run(StrategicApplication.class, args);
	}
}
