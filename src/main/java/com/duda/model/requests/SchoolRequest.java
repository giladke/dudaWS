package com.duda.model.requests;

import lombok.Data;

@Data
public class SchoolRequest {
    private double lat;
    private double lon;
    private int minimumGpa;
    private int maxNumberOfPupils;
}
