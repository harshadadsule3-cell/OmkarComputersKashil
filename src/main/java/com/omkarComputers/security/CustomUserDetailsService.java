package com.omkarComputers.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.omkarComputers.Entity.Student;
import com.omkarComputers.repository.StudentRepository;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Student student = studentRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Student Not Found"));

        return new User(
                student.getEmail(),
                student.getPassword(),
                Collections.singleton(
                        new SimpleGrantedAuthority(
                                student.getRole()))
        );
    }
}