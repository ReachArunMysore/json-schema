package com.example.jsonschema.dto;

import lombok.Data;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Person {
    private int id;
    private String name;
    private int age;
    private boolean citizen;
    private LocalDate dob;
    private MaritalStatus maritalStatus;
    private List<String> skills;
    private List<Qualification> qualification;
}
