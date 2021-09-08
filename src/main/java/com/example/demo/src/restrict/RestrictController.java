package com.example.demo.src.restrict;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.restrict.model.*;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/restricts")
public class RestrictController {

    @Autowired
    private final RestrictProvider restrictProvider;
    @Autowired
    private final RestrictService restrictService;
    @Autowired
    private final JwtService jwtService;

    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostRestrictRes> postRestrict(@RequestBody PostRestrictReq postRestrictReq) {
        try {
            if(postRestrictReq.getUserIdx() == null) {
                return new BaseResponse<>(EMPTY_USER_IDX);
            }
            if(postRestrictReq.getLatitude() == null) {
                return new BaseResponse<>(EMPTY_LATITUDE);
            }
            if(postRestrictReq.getLongitude() == null) {
                return new BaseResponse<>(EMPTY_LONGITUDE);
            }
            if(postRestrictReq.getRadius() == null) {
                return new BaseResponse<>(EMPTY_RADIUS);
            }
            PostRestrictRes postRestrictRes = restrictService.postRestrict(postRestrictReq);
            return new BaseResponse<>(postRestrictRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
