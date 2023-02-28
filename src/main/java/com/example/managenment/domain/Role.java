package com.example.managenment.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(length = 60)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<Employee> employees;

    public Role(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "privilege_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_Id")
    )
    private List<Privilege> privileges;

    @Override
    public String toString() {
        return  name;
    }
}
