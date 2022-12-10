package com.cjufi.bookmanagement.security;

import com.cjufi.bookmanagement.model.User;
import com.cjufi.bookmanagement.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User with " + username + " not found"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), getAuthorities(user));
    }
    private Collection<? extends GrantedAuthority> getAuthorities(User user){

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }
}
