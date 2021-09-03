package com.example.demo.src.location;

import com.example.demo.src.location.model.GetLocationRes;
import com.example.demo.src.location.model.PostLocationReq;
import com.example.demo.src.user.model.PostUserSignReq;
import com.example.demo.src.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class LocationDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int postLocation(PostLocationReq postLocationReq) {
        String postLocationQuery = "insert into Location (userIdx, latitude, longitude) VALUES (?,?,?)";
        Object[] postLocationParams = new Object[]{postLocationReq.getUserIdx(),
                postLocationReq.getLatitude(),
                postLocationReq.getLongitude()};
        this.jdbcTemplate.update(postLocationQuery, postLocationParams);
        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class);
    }

    public List<GetLocationRes> getLocation(int userIdx) {
        String getLocationQuery = "select Idx, userIdx, latitude, longitude," +
                " createdAt, updatedAt, status\n" +
                "from Location\n" +
                "where userIdx = ?\n";
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
