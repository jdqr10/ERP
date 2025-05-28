package com.erp.ERP.security;


import com.erp.ERP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.erp.ERP.models.User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return UserPrincipal.create(user);
    }
}

