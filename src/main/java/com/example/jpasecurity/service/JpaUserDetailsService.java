package com.example.jpasecurity.service;

import com.example.jpasecurity.model.SecurityUser;
import com.example.jpasecurity.entity.User;
import com.example.jpasecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .map((User user) -> new SecurityUser(user))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }
}
