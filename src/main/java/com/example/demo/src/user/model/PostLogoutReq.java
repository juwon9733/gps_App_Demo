package com.example.demo.src.user.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostLogoutReq {
    private Integer userIdx;
    private String userJwtToken;        // can null
}
