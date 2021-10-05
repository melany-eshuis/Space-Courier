package com.space.SpaceCourier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.space.SpaceCourier.Model.TempData;

@SpringBootApplication
public class SpaceCourierApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpaceCourierApplication.class, args);
		new TempData();
	}

}
