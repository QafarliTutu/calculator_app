package com.example.calculator_app.security;


import com.example.calculator_app.entity.XUserDetails;
import com.example.calculator_app.repo.XUserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@AllArgsConstructor
@Log4j2
public class UserDetailsServiceJPA implements UserDetailsService {
    private final XUserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findXUsersByUsername(username)
                .map(user -> new XUserDetails(user.getId(), user.getPassword(), user.getUsername(),user.getFullname(), user.getRoles()))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }
}
