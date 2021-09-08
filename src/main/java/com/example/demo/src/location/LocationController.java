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
     * [6]. 특정 유저에 대한 좌표 기록 조회
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
     * @param locationIdx
     * @param patchUserLocationReq
     * @return
     */
    @ResponseBody
    @PatchMapping("/status")
    public BaseResponse<PatchUserLocationRes> patchUserLocationStatus(@RequestParam(required = false) Integer locationIdx,
                                                            @RequestBody PatchUserLocationReq patchUserLocationReq) {
        try {
            if (locationIdx == null) {
                return new BaseResponse<>(EMPTY_LOCATION_IDX);
            }
            if (patchUserLocationReq.getStatus() == null) {
                return new BaseResponse<>(EMPTY_STATUS);
            }
            patchUserLocationReq.setLocationIdx(locationIdx);
            locationService.patchUserLocationStatus(patchUserLocationReq);
            PatchUserLocationRes patchUserLocationRes = new PatchUserLocationRes(locationIdx);
            return new BaseResponse<>(patchUserLocationRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
