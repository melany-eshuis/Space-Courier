package com.space.SpaceCourier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.space.SpaceCourier.Model.InitializeData;

@SpringBootApplication
public class SpaceCourierApplication 
{
	public static void main(String[] args) 
	{
		new InitializeData();
		System.out.println("Done loading the dummy Data!");
		//SpringApplication.run(SpaceCourierApplication.class, args);
		System.out.println("Done loading Spring!");
	}
}
