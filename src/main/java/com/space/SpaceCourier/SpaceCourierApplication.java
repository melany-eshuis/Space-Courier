package com.space.SpaceCourier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.space.SpaceCourier.Model.TempData;

@SpringBootApplication
public class SpaceCourierApplication 
{
	public static void main(String[] args) 
	{
		new TempData();
		SpringApplication.run(SpaceCourierApplication.class, args);
		System.out.println("Done loading Spring!");
	}
}
