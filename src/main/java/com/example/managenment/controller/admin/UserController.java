package com.example.managenment.controller.admin;

import com.example.managenment.authority.AuthorRequest;
import com.example.managenment.authority.AuthorResponse;
import com.example.managenment.config.detailservice.UserDetailImpl;
import com.example.managenment.config.jwt.JwtProvider;
import com.example.managenment.domain.Employee;
import com.example.managenment.repository.EmployeeRepository;
import com.example.managenment.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @GetMapping("/signin")
    @PreAuthorize("hasAuthority('EDIT_EMPLOYEE')")
    public ResponseEntity<?> login(@RequestBody AuthorRequest authorRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authorRequest.getUsername(),
                        authorRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String tokenn = jwtProvider.generateJwtToken(authentication);
        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
        List<String> roles=userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        return  ResponseEntity.ok(new AuthorResponse(tokenn,userDetails.getUsername(),roles));
    }
    @Transactional
    @GetMapping("/deleteId")
    @PreAuthorize("hasAuthority('ADMIN_ALL')")
    public  ResponseEntity<?> deleteRoleId(@RequestParam(value = "roleId") Integer roleId,@RequestParam(value = "name") String name) {
        roleRepo.deleteRoleId(roleId,name);
        return ResponseEntity.ok("Delete access");
    }
    @GetMapping("/token")
    @PreAuthorize("hasAuthority('ADMIN_ALL')")
    public ResponseEntity<?> getAllUser(@RequestParam("token") String token) {
        String email = jwtProvider.getUserNameFromJwtToken(token);
        Employee userDetails = employeeRepo.findByEmail(email);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails.getEmail(),
                        userDetails.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("Oke");
    }
}
