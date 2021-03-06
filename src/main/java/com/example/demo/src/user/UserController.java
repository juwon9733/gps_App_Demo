package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.config.BaseResponseStatus.EMPTY_USER_IDX;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtService;

    /**
     * [1]. 유저 회원 가입
     * @param postUserSignReq
     * @return
     */
    @ResponseBody
    @PostMapping("/sign-in")
    public BaseResponse<PostUserSignRes> postUserSign(@RequestBody PostUserSignReq postUserSignReq) {
        try {
            if(postUserSignReq.getId() == null) {
                return new BaseResponse<>(EMPTY_USER_ID);
            }
            if(postUserSignReq.getPasswd() == null) {
                return new BaseResponse<>(EMPTY_USER_PASSWD);
            }
            PostUserSignRes postUserSignRes = userService.postUserSign(postUserSignReq);
            return new BaseResponse<>(postUserSignRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * [2]. 유저 로그인
     * @param postLoginReq
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse<PostLoginRes> postLogin(@RequestBody PostLoginReq postLoginReq) {
        try {
            if(postLoginReq.getId() == null) {
                return new BaseResponse<>(EMPTY_USER_ID);
            }
            if(postLoginReq.getPasswd() == null) {
                return new BaseResponse<>(EMPTY_USER_PASSWD);
            }
            PostLoginRes postLoginRes = userProvider.postLogin(postLoginReq);
            return new BaseResponse<>(postLoginRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * [3]. 유저 로그아웃
     * @param postLogoutReq
     * @return
     */
    @ResponseBody
    @PostMapping("/logout")
    public BaseResponse<PostLogoutRes> postLogout(@RequestBody PostLogoutReq postLogoutReq) {
        try {
            if(postLogoutReq.getUserIdx() == null) {
                return new BaseResponse<>(EMPTY_USER_IDX);
            }
            if(userProvider.checkDeletedToken(jwtService.getJwt()) == true) {
                return new BaseResponse<>(DELETED_TOKEN);
            }
            /**
             * jwtService 관련
             */
            int userIdxByJwt = jwtService.getUserIdx();
            if (postLogoutReq.getUserIdx() != userIdxByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            String userJwtToken = jwtService.getJwt();

            postLogoutReq = new PostLogoutReq(userIdxByJwt, userJwtToken);
            PostLogoutRes postLogoutRes = userService.postLogout(postLogoutReq.getUserJwtToken());
            return new BaseResponse<>(postLogoutRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * [4]. 유저 정보 조회
     * @param userIdx
     * @return
     */
    @ResponseBody
    @GetMapping("/info")
    public BaseResponse<User> postLogout(@RequestParam(required = false) Integer userIdx) {
        try {
            if (userIdx == null) {
                return new BaseResponse<>(EMPTY_USER_IDX);
            }
            User user = userProvider.getUserByUserIdx(userIdx);
            return new BaseResponse<>(user);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
    /**
     * [5]. 유저 활성화 및 비활성화
     * @param patchUserStatusReq
     * @return
     */
    @ResponseBody
    @PatchMapping("/status")
    public BaseResponse<PatchUserStatusRes> patchUserStatus(@RequestBody(required = false) PatchUserStatusReq patchUserStatusReq) {
        try {
            if (patchUserStatusReq.getUserIdx() == null) {
                return new BaseResponse<>(EMPTY_USER_IDX);
            }
            if (patchUserStatusReq.getStatus() == null) {
                return new BaseResponse<>(EMPTY_STATUS);
            }
            userService.patchUserStatus(patchUserStatusReq);
            PatchUserStatusRes patchUserStatusRes = new PatchUserStatusRes(patchUserStatusReq.getUserIdx());
            return new BaseResponse<>(patchUserStatusRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
    /**
     * [5_1]. 모든 유저 아이디 조회
     * @return
     */
    @ResponseBody
    @GetMapping("/id")
    public BaseResponse<List<User>> getUserId() {
        try {
            List<User> users = userProvider.getUserId();
            return new BaseResponse<>(users);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * [5_2]. jwt값으로 유저 인덱스 조회
     * @return
     */
    @ResponseBody
    @GetMapping("/userIdx")
    public BaseResponse<GetUserIdxByJwtRes> getUserIdxByJwt() {
        try {
            int userIdxByJwt = jwtService.getUserIdx();

            GetUserIdxByJwtRes getUserIdxByJwtRes = new GetUserIdxByJwtRes(userIdxByJwt);

            return new BaseResponse<>(getUserIdxByJwtRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * [13]. 위치가 존재하는 유저의, 위치 정보와 유저 정보 조회
     * @return
     */
    @ResponseBody
    @GetMapping("/all-location")
    public BaseResponse<List<GetUserAllLocation>> getUserAllLocation() {
        try {
            List<GetUserAllLocation> getUserAllLocations = userProvider.getUserAllLocation();
            return new BaseResponse<>(getUserAllLocations);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }



}
