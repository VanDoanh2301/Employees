package com.example.managenment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto implements Serializable {
    private int departmentId;

    private String name;

    private  String shortName;

    private boolean edit=false;

}
