package com.example.demo.src.restrict;

import com.example.demo.config.BaseException;
import com.example.demo.src.restrict.model.*;
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
    /*
    public GetRestrictBoolRes getRestrictBool(GetRestrictBoolReq getRestrictBoolReq) throws BaseException {
        try {
            boolean IsIn = false;

            List<GetRestrictRes> getRestrictRes = getRestrict(getRestrictBoolReq.getUserIdx());
            for (GetRestrictRes getRestrictRe : getRestrictRes) {
                long now_x = Long.parseLong(getRestrictBoolReq.getNowLatitude());
                long now_y = Long.parseLong(getRestrictBoolReq.getNowLongitude());
                long restrict_x = Long.parseLong(getRestrictRe.getLatitude());
                long restrict_y = Long.parseLong(getRestrictRe.getLatitude());
                long restrict_radius = Long.parseLong(getRestrictRe.getRadius());
                double len = Math.sqrt(Math.pow((now_x-restrict_x),2) + Math.pow((now_y-restrict_y),2));
                if(len < restrict_radius)  {
                    IsIn = true;
                    break;
                }
            }
            GetRestrictBoolRes getRestrictBoolRes = new GetRestrictBoolRes(IsIn);
            return getRestrictBoolRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

     */
}
