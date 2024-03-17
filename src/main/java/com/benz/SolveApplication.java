package com.benz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class SolveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolveApplication.class, args);
	}
//	public void onApplicationEvent(final ApplicationReadyEvent event) {
//
//	    // here your code ...
//	    
//	  }
}
