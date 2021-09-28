package com.example.demo.src.polygon;

import com.example.demo.config.BaseException;
import com.example.demo.src.polygon.model.*;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
@Transactional
public class PolygonService {

    @Autowired
    private final PolygonDao polygonDao;
    @Autowired
    private final PolygonProvider polygonProvider;
    @Autowired
    private final JwtService jwtService;

    public PostPolygonRes postPolygon(PostPolygonReq postPolygonReq) throws BaseException {
        try {
            int UserPolygonIdx = polygonDao.postPolygon(postPolygonReq);
            return new PostPolygonRes(UserPolygonIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
