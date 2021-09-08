package com.example.demo.src.restrict;

import com.example.demo.src.location.model.PostLocationReq;
import com.example.demo.src.restrict.model.PostRestrictReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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
}
