package com.example.demo.src.restrict;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.restrict.model.*;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * [9]. 특정 유저에 대한 제한 구역 생성
     * @param postRestrictReq
     * @return
     */
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

    /**
     * [10]. 특정 유저에 대한 제한 구역 조회
     * @param userIdx
     * @return
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetRestrictRes>> getRestrict(@RequestParam Integer userIdx) {
        try {
            if(userIdx == null) {
                return new BaseResponse<>(EMPTY_USER_IDX);
            }
            List<GetRestrictRes> getRestrictRes = restrictProvider.getRestrict(userIdx);
            return new BaseResponse<>(getRestrictRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * [11]. 특정 유저에 대한 제한 구역 활성화 및 비활성화
     * @param patchRestrictStatusReq
     * @return
     */
    @ResponseBody
    @PatchMapping("/status")
    public BaseResponse<PatchRestrictStatusRes> patchRestrictStatus(
            @RequestBody PatchRestrictStatusReq patchRestrictStatusReq) {
        try {
            if (patchRestrictStatusReq.getRestrictIdx() == null) {
                return new BaseResponse<>(EMPTY_RESTRICT_IDX);
            }
            if (patchRestrictStatusReq.getStatus() == null) {
                return new BaseResponse<>(EMPTY_STATUS);
            }
            restrictService.patchRestrictStatus(patchRestrictStatusReq);
            PatchRestrictStatusRes patchRestrictStatusRes =
                    new PatchRestrictStatusRes(patchRestrictStatusReq.getRestrictIdx());
            return new BaseResponse<>(patchRestrictStatusRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
    @ResponseBody
    @PatchMapping("/in-out")
    public BaseResponse<PatchRestrictInOutRes> patchRestrictInOut(
            @RequestBody PatchRestrictInOutReq patchRestrictInOutReq) {
        try {
            if (patchRestrictInOutReq.getUserIdx() == null) {
                return new BaseResponse<>(EMPTY_USER_IDX);
            }
            if (patchRestrictInOutReq.getRestrictStatus() == null) {
                return new BaseResponse<>(EMPTY_STATUS);
            }
            restrictService.patchRestrictInOut(patchRestrictInOutReq);
            PatchRestrictInOutRes patchRestrictInOutRes =
                    new PatchRestrictInOutRes(patchRestrictInOutReq.getUserIdx());
            return new BaseResponse<>(patchRestrictInOutRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /*
    @ResponseBody
    @GetMapping("")
    public BaseResponse<GetRestrictBoolRes> getRestrictBool(@RequestBody GetRestrictBoolReq getRestrictBoolReq) {
        try {
            if (getRestrictBoolReq.getUserIdx() == null) {
                return new BaseResponse<>(EMPTY_USER_IDX);
            }
            if (getRestrictBoolReq.getNowLatitude() == null) {
                return new BaseResponse<>(EMPTY_LATITUDE);
            }
            if (getRestrictBoolReq.getNowLongitude() == null) {
                return new BaseResponse<>(EMPTY_LONGITUDE);
            }
            GetRestrictBoolRes getRestrictRes = restrictProvider.getRestrictBool(getRestrictBoolReq);
            return new BaseResponse<>(getRestrictRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
     */

}
