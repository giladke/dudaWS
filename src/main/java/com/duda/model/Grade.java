package com.duda.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Grade{
    @Id
    private String courseName;
    private int grade;
}