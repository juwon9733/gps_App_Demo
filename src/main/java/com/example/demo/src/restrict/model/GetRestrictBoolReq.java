package com.example.demo.src.restrict.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GetRestrictBoolReq {
    private Integer userIdx;
    private String NowLatitude;
    private String NowLongitude;
}
