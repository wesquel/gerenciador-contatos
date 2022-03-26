package br.com.ifpb.gerenciadorendereco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GerenciadorEnderecoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorEnderecoApplication.class, args);
	}

}
