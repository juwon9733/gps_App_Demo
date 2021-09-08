package com.example.demo.src.restrict;

import com.example.demo.config.BaseException;
import com.example.demo.src.location.model.PatchUserLocationReq;
import com.example.demo.src.restrict.model.*;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
@Transactional
public class RestrictService {

    @Autowired
    private final RestrictDao restrictDao;
    @Autowired
    private final RestrictProvider restrictProvider;
    @Autowired
    private final JwtService jwtService;

    public PostRestrictRes postRestrict(PostRestrictReq PostRestrictReq) throws BaseException {
        try {
            int restrictIdx = restrictDao.postRestrict(PostRestrictReq);
            return new PostRestrictRes(restrictIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public void patchRestrictStatus(PatchRestrictStatusReq patchRestrictStatusReq) throws BaseException {
        try {
            restrictDao.patchRestrictStatus(patchRestrictStatusReq);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
