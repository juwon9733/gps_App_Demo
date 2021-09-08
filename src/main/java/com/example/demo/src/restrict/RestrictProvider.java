package com.example.demo.src.restrict;

import com.example.demo.config.BaseException;
import com.example.demo.src.location.model.GetLocationRes;
import com.example.demo.src.restrict.model.GetRestrictRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
@Transactional
public class RestrictProvider {

    @Autowired
    private final RestrictDao restrictDao;
    @Autowired
    private final JwtService jwtService;

    public List<GetRestrictRes> getRestrict(int userIdx) throws BaseException {
        try {
            List<GetRestrictRes> getRestrictRes = restrictDao.getRestrict(userIdx);
            return getRestrictRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
