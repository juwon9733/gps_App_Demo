package com.example.demo.src.location;

import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.location.model.PatchUserLocationReq;
import com.example.demo.src.location.model.PostLocationReq;
import com.example.demo.src.location.model.PostLocationRes;
import com.example.demo.src.user.model.PatchUserStatusReq;
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
public class LocationService {

    @Autowired
    private final LocationDao locationDao;
    @Autowired
    private final LocationProvider locationProvider;
    @Autowired
    private final JwtService jwtService;


    public PostLocationRes postLocation(PostLocationReq postLocationReq) throws BaseException {
        try {
            int locationIdx = locationDao.postLocation(postLocationReq);
            return new PostLocationRes(locationIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public void patchUserLocationStatus(PatchUserLocationReq patchUserLocationReq) throws BaseException {
        try {
            locationDao.patchUserLocationStatus(patchUserLocationReq);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
