package com.example.demo.src.location.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetLocationRes {
    private int locationIdx;
    private int userIdx;
    private String latitude;
    private String longitude;
    private String createdAt;
    private String updatedAt;
    private String status;
}
