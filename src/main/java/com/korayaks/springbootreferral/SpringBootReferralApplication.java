package com.korayaks.springbootreferral;

import com.korayaks.springbootreferral.model.User;
import com.korayaks.springbootreferral.repo.UserRepository;
import com.korayaks.springbootreferral.util.RandomStringGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootReferralApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootReferralApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    RandomStringGenerator randomStringGenerator;
    @Override
    public void run(String... args) throws Exception {
        User user = User.builder()
                .firstName("Burak Koray")
                .lastName("Aksoy")
                .username("korayaks")
                .referralCode(randomStringGenerator.generate())
                .build();
        userRepository.save(user);
    }
}
