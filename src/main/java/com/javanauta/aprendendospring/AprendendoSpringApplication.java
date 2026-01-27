package com.javanauta.aprendendospring;

import com.javanauta.aprendendospring.infrastructure.entity.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AprendendoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AprendendoSpringApplication.class, args);

//		Usuario joao = new Usuario("Joao", "joaodasilva@gmail.com", "123456");
//		System.out.println(joao.getNome());
//		System.out.println(joao.getEmail());
//		joao.setNome("Joao da Silva");
//		System.out.println(joao.getNome());
	}

}
