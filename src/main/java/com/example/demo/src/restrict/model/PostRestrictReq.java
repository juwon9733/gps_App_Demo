package com.example.demo.src.restrict.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostRestrictReq {
    Integer userIdx;
    String latitude;
    String longitude;
    Integer radius;
}
