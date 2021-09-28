package com.example.demo.src.polygon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostPolygonReq {
    private Integer userIdx;
    private String pointX;
    private String pointY;
}
