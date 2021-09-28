package com.example.demo.src.polygon;


import com.example.demo.src.location.model.GetLocationRes;
import com.example.demo.src.polygon.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class PolygonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int postPolygon(PostPolygonReq postPolygonReq) {
        String postPolygonQuery = "insert into UserPolygon (userIdx, pointX, pointY) VALUES (?,?,?)";
        Object[] postPolygonParams = new Object[]{postPolygonReq.getUserIdx(),
                postPolygonReq.getPointX(),
                postPolygonReq.getPointY()};
        this.jdbcTemplate.update(postPolygonQuery, postPolygonParams);
        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class);
    }
    public List<PolygonAllPoint> getPolygonAllPointByUserIdx(int userIdx) {
        String getPolygonAllPointByUserIdxQuery = "select pointX, pointY\n" +
                "from UserPolygon\n" +
                "where userIdx = ? and status = 'Y'\n" +
                "order by updatedAt ASC;";
        int getPolygonAllPointByUserIdxParams = userIdx;
        return this.jdbcTemplate.query(getPolygonAllPointByUserIdxQuery,
                (rs, rowNum) -> new PolygonAllPoint(
                        rs.getDouble("pointX"),
                        rs.getDouble("pointY")),
                getPolygonAllPointByUserIdxParams);
    }
    public List<GetLocationRes> getLocation(int userIdx) {
        String getLocationQuery = "select Idx,\n" +
                "       userIdx,\n" +
                "       latitude,\n" +
                "       longitude,\n" +
                "       createdAt,\n" +
                "       updatedAt,\n" +
                "       status\n" +
                "from Location\n" +
                "where userIdx = ?\n" +
                "  and status = 'Y'\n" +
                "order by createdAt desc;";
        int getLocationParams = userIdx;
        return this.jdbcTemplate.query(getLocationQuery,
                (rs, rowNum) -> new GetLocationRes(
                        rs.getInt("Idx"),
                        rs.getInt("userIdx"),
                        rs.getString("latitude"),
                        rs.getString("longitude"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        rs.getString("status")),
                getLocationParams);
    }
}
