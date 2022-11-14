package com.example.springmongo.configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.springmongo.domain.Post;
import com.example.springmongo.domain.User;
import com.example.springmongo.dto.AuthorDTO;
import com.example.springmongo.repositories.PostRepository;
import com.example.springmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository PostRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		PostRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Gray", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post p1 = new Post(null, sdf.parse("20/12/2022"), "Viagem !", "Viagem para São Paulo", new AuthorDTO(maria));
		Post p2 = new Post(null, sdf.parse("27/12/2022"), "Boa Noite !", ".-.", new AuthorDTO(maria));

		PostRepository.saveAll(Arrays.asList(p1, p2));
	}

}
