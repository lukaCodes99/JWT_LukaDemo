package com.lukam.demosJWT;

// import java.util.List;
// import java.util.Random;

// import org.springframework.context.annotation.Bean;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import com.lukam.demosJWT.post.Post;
// import com.lukam.demosJWT.repo.post.PostRepository;
// import com.lukam.demosJWT.repo.user.UserRepository;
// import com.lukam.demosJWT.user.User;
// import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemosJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemosJwtApplication.class, args);
	}

// 	@Bean
// 	CommandLineRunner commandLineRunner(PostRepository posts, UserRepository users, PasswordEncoder encoder){
// 		return args ->{
// 																	//Arrays.asList is immutable
// 			users.save(new User(null, "Luka", "Maletic", "luka@mail.com", encoder.encode("pass"), Set.of(Role.ADMIN), Set.of(Group.SKLADISTE)));
// 			users.save(new User(null, "John", "Doe", "john@mail.com", encoder.encode("pass"), Set.of(Role.USER), Set.of(Group.PISARNICA)));
// 			users.save(new User(null, "Jane", "Smith", "jane@mail.com", encoder.encode("pass"), Set.of(Role.USER), Set.of(Group.SKLADISTE, Group.URED_DIREKTORA)));
// /*
// 			List<User> userList = (List<User>) users.findAll(); // Retrieve all users
			
// 			Random random = new Random();
// 			for (int i = 0; i < 10; i++) {
// 				//User randomUser = userList.get(random.nextInt(userList.size())); // Select a random user
// 				Post post = new Post(null, "Title " + i, "Content " + i, "Description " + i, null, null, random.nextInt(3));
// 				posts.save(post);
// 			}
// */

// 		};
// 	}

}
