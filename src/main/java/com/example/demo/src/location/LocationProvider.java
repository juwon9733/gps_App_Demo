package com.example.demo.src.location;

import com.example.demo.config.BaseException;
import com.example.demo.src.location.model.GetLocationRes;
import com.example.demo.src.user.model.User;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.USER_IDX_NOT_EXISTS;

@RequiredArgsConstructor
@Service
@Transactional
public class LocationProvider {

    @Autowired
    private final LocationDao locationDao;
    @Autowired
    private final JwtService jwtService;

    public List<GetLocationRes> getLocation(int userIdx) throws BaseException {
        try {
            List<GetLocationRes> getLocationRes = locationDao.getLocation(userIdx);
            return getLocationRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
