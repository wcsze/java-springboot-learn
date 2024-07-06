package com.example.demo.service;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.User;


@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    
    private final UserRepository userRepository;

    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         // Retrieve user from repository based on username
         Optional<User> userOptional = userRepository.findByUsername(username);
         User user = userOptional.orElseThrow(() -> new NotFoundException("username not found"));
 
         // You may need to adapt this according to your User entity structure
         return org.springframework.security.core.userdetails.User
                 .withUsername(username)
                 .password(user.getPassword())
                 .authorities("ROLE_USER") // You can load authorities from your user entity
                 .accountExpired(false)
                 .accountLocked(false)
                 .credentialsExpired(false)
                 .disabled(false)
                 .build();
     }
}
