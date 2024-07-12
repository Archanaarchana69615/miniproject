package com.adminpanel.miniproject.security;


import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


import java.util.Collection;

@Getter
@Setter
public class CustomerUserDetails extends org.springframework.security.core.userdetails.User
{

    private String name;
    private String gender;
    private String phoneNumber;

    public CustomerUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                             String gender, String name, String phoneNumber) {
        super(username, password, authorities);
        this.gender = gender;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }


}
