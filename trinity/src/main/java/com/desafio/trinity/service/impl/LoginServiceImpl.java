package com.desafio.trinity.service.impl;

import com.desafio.trinity.entity.User;
import com.desafio.trinity.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public LoginServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repository.findByLogin(login);
        if (user == null)
            return null;

        String[] rolesUser = {"USER"};

        List<GrantedAuthority> auth = AuthorityUtils.createAuthorityList(rolesUser);

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), auth);
    }
}
