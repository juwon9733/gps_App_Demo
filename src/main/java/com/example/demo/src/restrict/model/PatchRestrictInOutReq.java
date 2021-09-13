package com.example.demo.src.restrict.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchRestrictInOutReq {
    private Integer userIdx;
    private String restrictStatus;
}
