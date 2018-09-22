package com.etf.ppis.lambda.telenash.service.impl;

import com.etf.ppis.lambda.telenash.model.User;
import com.etf.ppis.lambda.telenash.model.UserDto;
import com.etf.ppis.lambda.telenash.repository.UserRepository;
import com.etf.ppis.lambda.telenash.repository.UserRoleRepository;
import com.etf.ppis.lambda.telenash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service (value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService
{
    private final UserRepository userDao;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userDao, BCryptPasswordEncoder bcryptEncoder, UserRoleRepository userRoleRepository)
    {
        this.userDao = userDao;
        this.bcryptEncoder = bcryptEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    private static List<SimpleGrantedAuthority> getAuthority(User user)
    {
        return Arrays.asList(new SimpleGrantedAuthority(user.getUserRole().getName()));
//        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userDao
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("No user found with username '%s'.", username)));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                getAuthority(user)
        );

    }

    /*private static List<GrantedAuthority> mapToGrantedAuthorities(List<UserRole> authorities)
    {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
    }*/

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User getByUserName(String userName)
    {
        User user = userDao
                .findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username."));

        return user;
    }

    @Override
    public User save(UserDto user)
    {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setUserRole(userRoleRepository.findByName("ROLE_USER").get());
        return userDao.save(newUser);
    }
}
