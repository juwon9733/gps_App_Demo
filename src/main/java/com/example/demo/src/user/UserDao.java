package com.example.demo.src.user;

import com.example.demo.src.user.model.PatchUserStatusReq;
import com.example.demo.src.user.model.PostUserSignReq;
import com.example.demo.src.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int postUserSign(PostUserSignReq postUserSignReq) {
        String postUserSignQuery = "insert into User (id, passwd) VALUES (?, ?)";
        Object[] postUserSignParams = new Object[]{postUserSignReq.getId(), postUserSignReq.getPasswd()};
        this.jdbcTemplate.update(postUserSignQuery, postUserSignParams);
        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class);
    }

    public User getUserById(String id) {
        String getUserByIdQuery = "select Idx, id, passwd, status\n" +
                "from User\n" +
                "where id = ? and status = 'Y'";
        String getUserByIdParams = id;
        return this.jdbcTemplate.queryForObject(getUserByIdQuery,
                (rs, rowNum) -> new User(
                        rs.getInt("Idx"),
                        rs.getString("id"),
                        rs.getString("passwd"),
                        rs.getString("status"))
                , getUserByIdParams);
    }

    public int postLogout(String userJwtToken) {
        String postLogoutQuery = "insert into DeletedToken (deletedToken) VALUES (?);";
        Object[] postLogoutParams = new Object[]{userJwtToken};
        this.jdbcTemplate.update(postLogoutQuery, postLogoutParams);
        String lastInserIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdxQuery, int.class);
    }

    public User getUserByUserIdx(int userIdx) {
        String getUsersByUserIdxQuery = "select * from User where Idx = ?";
        int getUsersByUserIdxParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUsersByUserIdxQuery,
                (rs, rowNum) -> new User(
                        rs.getInt("Idx"),
                        rs.getString("id"),
                        rs.getString("passwd"),
                        rs.getString("status")),
                getUsersByUserIdxParams);
    }

    /**
     * [5]. 유저 비활성화
     * @param patchUserStatusReq
     */
    public void patchUserStatus(PatchUserStatusReq patchUserStatusReq) {
        String patchUserStatusQuery = "update User\n" +
                "set status = ?\n" +
                "where Idx = ?";
        Object[] patchUserStatusParams = new Object[]{patchUserStatusReq.getStatus(),
                patchUserStatusReq.getUserIdx()};
        this.jdbcTemplate.update(patchUserStatusQuery, patchUserStatusParams);
        String lastInserIdxQuery = "select last_insert_id()";
    }

    /**
     * check 관련 함수 모음
     */
    public boolean checkId(String id) {
        String checkIdQuery = "select exists(select * from User where id = ?)";
        String checkIdParams = id;
        if (this.jdbcTemplate.queryForObject(checkIdQuery, int.class, checkIdParams) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkDeletedToken(String JwtToken) {
        String checkTokenQuery = "select exists(select Idx from DeletedToken where deletedToken = ?)";
        String checkTokenParams = JwtToken;
        if (this.jdbcTemplate.queryForObject(checkTokenQuery,
                int.class,
                checkTokenParams) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUserIdx(int userIdx) {
        String checkUserIdxQuery = "select exists(select * from User where Idx = ?)";
        int checkUserIdxParams = userIdx;
        if (this.jdbcTemplate.queryForObject(checkUserIdxQuery, int.class, checkUserIdxParams) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
