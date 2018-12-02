package com.maxpro.configuration.security;

import com.maxpro.model.User;
import com.maxpro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Transactional
public class WebUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    public WebUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {

            User user = userRepository.findByUsername(username);
            if (user == null) {
                User newuser = new User();
                newuser.setEnabled(true);
                newuser.setUsername(username);
                newuser.setPassword(passwordEncoder().encode("123456"));

                Set<String> set = new HashSet<>();

                set.add(username.contains("admin") ? "ROLE_ADMIN" : "ROLE_USER");
                newuser.setRoles(set);
                userRepository.save(newuser);
                user = userRepository.findByUsername(username);

            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    getAuthorities(user)
            );
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private Set<GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (String role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
            authorities.add(grantedAuthority);
        }
        return authorities;
    }

}
