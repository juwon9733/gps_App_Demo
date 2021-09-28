package com.example.demo.src.polygon;

import com.example.demo.config.BaseException;
import com.example.demo.src.location.model.GetLocationRes;
import com.example.demo.src.polygon.model.PolygonAllPoint;
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
public class PolygonProvider {

    @Autowired
    private final PolygonDao polygonDao;
    @Autowired
    private final JwtService jwtService;


    public Boolean getPolygonStatus(int userIdx, double pointX, double pointY) throws BaseException {
        try {
            List<PolygonAllPoint> polygonAllPoints = getPolygonAllPointByUserIdx(userIdx);
            Polygon polygon = new Polygon();
            for (PolygonAllPoint point : polygonAllPoints) {
                polygon.addPoint(point.getPointX(), point.getPointY());
            }
//            List<GetLocationRes> getLocationRes = polygonDao.getLocation(userIdx);
//
//            double latitude = Double.parseDouble(getLocationRes.get(0).getLatitude());
//            double longitude = Double.parseDouble(getLocationRes.get(0).getLongitude());
            Boolean aBoolean = polygon.contains(pointX, pointY);

            return aBoolean;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public List<PolygonAllPoint> getPolygonAllPointByUserIdx(int userIdx) throws BaseException {
        try {
            List<PolygonAllPoint> polygonAllPoints = polygonDao.getPolygonAllPointByUserIdx(userIdx);
            return polygonAllPoints;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
