package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.user.model.PostLoginReq;
import com.example.demo.src.user.model.PostLoginRes;
import com.example.demo.src.user.model.User;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.config.BaseResponseStatus.FAILED_TO_LOGIN_BY_PASSWD;

@RequiredArgsConstructor
@Service
@Transactional
public class UserProvider {
    @Autowired
    private final UserDao userDao;
    @Autowired
    private final JwtService jwtService;

    public PostLoginRes postLogin(PostLoginReq postLoginReq) throws BaseException{
        if(checkId(postLoginReq.getId()) != true){
            throw new BaseException(USER_ID_NOT_EXIST);
        }
        User user = getUserById(postLoginReq.getId());
        String password;
        try {
            password = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(user.getPasswd());
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }
        int userIdx = getUserById(postLoginReq.getId()).getUserIdx();
        if(postLoginReq.getPasswd().equals(password)){
            String jwt = jwtService.createJwt(userIdx);
            return new PostLoginRes(userIdx,jwt);
        }
        else{
            throw new BaseException(FAILED_TO_LOGIN_BY_PASSWD);
        }
    }
    public User getUserById(String id) throws BaseException {
        try {
            User user = userDao.getUserById(id);
            return user;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * check 관련 함수 모음
     */
    public boolean checkId(String id) throws BaseException {
        try{
            return userDao.checkId(id);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public boolean checkDeletedToken(String JwtToken) throws BaseException {
        try{
            return userDao.checkDeletedToken(JwtToken);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
