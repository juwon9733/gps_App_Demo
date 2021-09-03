package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.user.model.PatchUserStatusReq;
import com.example.demo.src.user.model.PostLogoutRes;
import com.example.demo.src.user.model.PostUserSignReq;
import com.example.demo.src.user.model.PostUserSignRes;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    @Autowired
    private final UserDao userDao;
    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final JwtService jwtService;

    public PostUserSignRes postUserSign(PostUserSignReq postUserSignReq) throws BaseException {
        if(userProvider.checkId(postUserSignReq.getId()) == true) {
            throw new BaseException(DUPLICATED_USER_ID);
        }
        try {
            String pwd;
            pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(postUserSignReq.getPasswd());
            postUserSignReq.setPasswd(pwd);
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try {
            int userIdx = userDao.postUserSign(postUserSignReq);
            String jwt = jwtService.createJwt(userIdx);
            return new PostUserSignRes(userIdx, jwt);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public PostLogoutRes postLogout(String userJwtToken) throws BaseException {
        try {
            int deletedJwtIdx = userDao.postLogout(userJwtToken);
            return new PostLogoutRes(deletedJwtIdx, userJwtToken);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public void patchUserStatus(PatchUserStatusReq patchUserStatusReq) throws BaseException {
        try {
            userDao.patchUserStatus(patchUserStatusReq);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
