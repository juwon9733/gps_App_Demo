package com.example.demo.src.restrict.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetRestrictRes {
    int restrictIdx;
    int userIdx;
    String latitude;
    String longitude;
    String radius;
    String createdAt;
    String updatedAt;
    String status;
}
