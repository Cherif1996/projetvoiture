package com.jeeProjectGb;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jeeProjectGb.controller.VoitureController;


@SpringBootApplication
public class JeeProjectGbApplication {

	public static void main(String[] args) {
		new File(VoitureController.uploadDirectory).mkdir();
		SpringApplication.run(JeeProjectGbApplication.class, args);
	}

}
