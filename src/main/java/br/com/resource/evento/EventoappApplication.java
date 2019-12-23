package br.com.resource.evento;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.resource.evento.controllers.FileUploadController;

@SpringBootApplication
public class EventoappApplication {

	public static void main(String[] args) {
		new File(FileUploadController.uploadDirectory).mkdir();
		SpringApplication.run(EventoappApplication.class, args);
		
//		System.out.println(new BCryptPasswordEncoder().encode("ana"));
	}

}
