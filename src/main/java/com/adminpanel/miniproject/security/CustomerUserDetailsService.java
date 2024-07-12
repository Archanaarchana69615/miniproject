package com.adminpanel.miniproject.security;

import com.adminpanel.miniproject.entity.User;
import com.adminpanel.miniproject.repository.UserRepository;
import com.adminpanel.miniproject.security.CustomerUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;



    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);


        if (user != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));

            return new CustomerUserDetails(
                    user.getEmail(),
                    user.getPassword(),
                    authorities,
                    user.getGender(),
                    user.getName(),
                    user.getPhoneNumber()
            );

        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}
