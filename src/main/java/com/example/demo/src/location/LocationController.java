package com.example.demo.src.location;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.location.model.*;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private final LocationService locationService;
    @Autowired
    private final LocationProvider locationProvider;
    @Autowired
    private final JwtService jwtService;

    /**
     * [6]. 특정 유저에 대한 좌표 기록 생성
     * @param postLocationReq
     * @return
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostLocationRes> postLocation(@RequestBody PostLocationReq postLocationReq) {
        try {
            if(postLocationReq.getUserIdx() == null) {
                return new BaseResponse<>(EMPTY_USER_IDX);
            }
            if(postLocationReq.getLatitude() == null) {
                return new BaseResponse<>(EMPTY_LATITUDE);
            }
            if(postLocationReq.getLongitude() == null) {
                return new BaseResponse<>(EMPTY_LONGITUDE);
            }
            PostLocationRes postLocationRes = locationService.postLocation(postLocationReq);
            return new BaseResponse<>(postLocationRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * [7]. 특정 유저에 대한 좌표 기록 조회
     * @param userIdx
     * @return
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetLocationRes>> getLocation(@RequestParam(required = false) Integer userIdx) {
        try {
            if (userIdx == null) {
                return new BaseResponse<>(EMPTY_USER_IDX);
            }
            List<GetLocationRes> getLocationRes = locationProvider.getLocation(userIdx);
            return new BaseResponse<>(getLocationRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * [8]. 특정 좌표 기록 활성화 및 비활성화
     * @param patchUserLocationReq
     * @return
     */
    @ResponseBody
    @PatchMapping("/status")
    public BaseResponse<PatchUserLocationRes> patchUserLocationStatus(
            @RequestBody PatchUserLocationReq patchUserLocationReq) {
        try {
            if (patchUserLocationReq.getLocationIdx() == null) {
                return new BaseResponse<>(EMPTY_LOCATION_IDX);
            }
            if (patchUserLocationReq.getStatus() == null) {
                return new BaseResponse<>(EMPTY_STATUS);
            }
            locationService.patchUserLocationStatus(patchUserLocationReq);
            PatchUserLocationRes patchUserLocationRes = new PatchUserLocationRes(patchUserLocationReq.getLocationIdx());
            return new BaseResponse<>(patchUserLocationRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * [8_1]. 특정 유저의 전체 경로 삭제
     * @param patchUserLocationAllReq
     * @return
     */
    @ResponseBody
    @PatchMapping("/status/all")
    public BaseResponse<String> patchUserLocationStatusAll(
            @RequestBody PatchUserLocationAllReq patchUserLocationAllReq) {
        try {
            if (patchUserLocationAllReq.getUserIdx() == null) {
                return new BaseResponse<>(EMPTY_USER_IDX);
            }
            if (patchUserLocationAllReq.getStatus() == null) {
                return new BaseResponse<>(EMPTY_STATUS);
            }
            locationService.patchUserLocationStatusAll(patchUserLocationAllReq);
            String res = "user's all status patched";
            return new BaseResponse<>(res);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
