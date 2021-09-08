package com.example.demo.src.restrict;

import com.example.demo.src.location.model.GetLocationRes;
import com.example.demo.src.location.model.PostLocationReq;
import com.example.demo.src.restrict.model.GetRestrictRes;
import com.example.demo.src.restrict.model.PostRestrictReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class RestrictDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int postRestrict(PostRestrictReq postRestrictReq) {
        String postRestrictQuery = "insert into Restricted\n" +
                "(userIdx, latitude, longitude, radius)\n" +
                "VALUES (?,?,?,?);";
        Object[] postRestrictParams = new Object[]{postRestrictReq.getUserIdx(),
                postRestrictReq.getLatitude(), postRestrictReq.getLongitude(),
                postRestrictReq.getRadius()};
        this.jdbcTemplate.update(postRestrictQuery, postRestrictParams);
        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class);
    }

    public List<GetRestrictRes> getRestrict(int userIdx) {
        String getRestrictQuery = "select Idx, userIdx, latitude, longitude, radius, createdAt, updatedAt, status\n" +
                "from Restricted\n" +
                "where userIdx = ? and status = 'Y';";
        int getRestrictParams = userIdx;
        return this.jdbcTemplate.query(getRestrictQuery,
                (rs, rowNum) -> new GetRestrictRes(
                        rs.getInt("Idx"),
                        rs.getInt("userIdx"),
                        rs.getString("latitude"),
                        rs.getString("longitude"),
                        rs.getString("radius"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        rs.getString("status")),
                getRestrictParams);
    }
}
