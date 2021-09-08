package com.example.demo.src.restrict.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchRestrictStatusReq {
    private Integer restrictIdx;
    private String status;
}
