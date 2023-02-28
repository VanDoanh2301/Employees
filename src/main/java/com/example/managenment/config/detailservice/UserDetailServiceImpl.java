package com.example.managenment.config.detailservice;

import com.example.managenment.domain.Employee;
import com.example.managenment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeRepository repo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = repo.findByEmail(email);
        if(employee == null) {
            throw  new UsernameNotFoundException("User not found");
        }

        return new UserDetailImpl(employee);
    }
}