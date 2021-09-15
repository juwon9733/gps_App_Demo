package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserAllLocation {
    private int userIdx;
    private String id;
    private String passwd;
    private String restrictStatus;
    private String latitude;
    private String longitude;
    private String createdAt_location;
    private String updatedAt_location;
}
