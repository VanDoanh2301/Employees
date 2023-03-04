package com.example.managenment.authority;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorRequest {

    private String username;
    private  String password;

}