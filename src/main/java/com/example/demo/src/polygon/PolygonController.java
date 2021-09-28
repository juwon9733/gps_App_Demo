package com.example.demo.src.polygon;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.location.model.GetLocationRes;
import com.example.demo.src.polygon.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.JwtService;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/polygons")
public class PolygonController {

    @Autowired
    private final PolygonProvider polygonProvider;
    @Autowired
    private final PolygonService polygonService;
    @Autowired
    private final JwtService jwtservice;

    /**
     * [14]. 특정 유저의 다각형 제한 구역 생성
     * @param postLocationReq
     * @return
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostPolygonRes> postPolygon(@RequestBody PostPolygonReq postLocationReq) {
        try {
            if(postLocationReq.getUserIdx() == null) {
                return new BaseResponse<>(EMPTY_USER_IDX);
            }
            if(postLocationReq.getPointX() == null) {
                return new BaseResponse<>(EMPTY_POINT_X);
            }
            if(postLocationReq.getPointY() == null) {
                return new BaseResponse<>(EMPTY_POINT_Y);
            }
            PostPolygonRes postPolygonRes = polygonService.postPolygon(postLocationReq);
            return new BaseResponse<>(postPolygonRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * [15]. 특정 유저의 다각형 제한 구역 조회
     * @param userIdx
     * @return
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<PolygonAllPoint>> getPolygonAllPointByUserIdx(@RequestParam(required = false) Integer userIdx) {
        try {
            if (userIdx == null) {
                return new BaseResponse<>(EMPTY_USER_IDX);
            }
            List<PolygonAllPoint> polygonAllPoints = polygonProvider.getPolygonAllPointByUserIdx(userIdx);
            return new BaseResponse<>(polygonAllPoints);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * [16]. 특정 유저의 다각형 제한 구역내에 존재하는지에 대한 여부 조회
     * @param userIdx
     * @return
     */
    @ResponseBody
    @GetMapping("/in-out")
    public BaseResponse<Boolean> getPolygonStatus(@RequestParam(required = false) Integer userIdx, @RequestParam(required = false) Double pointX, @RequestParam(required = false) Double pointY) {
        try {
            if (userIdx == null) {
                return new BaseResponse<>(EMPTY_USER_IDX);
            }
            if (pointX == null) {
                return new BaseResponse<>(EMPTY_POINT_X);
            }
            if (pointX == null) {
                return new BaseResponse<>(EMPTY_POINT_Y);
            }
            Boolean aBoolean = polygonProvider.getPolygonStatus(userIdx, pointX, pointY);
            return new BaseResponse<>(aBoolean);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


}
