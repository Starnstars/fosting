package com.lee.fosting.Restaurant.repository;

import com.lee.fosting.Restaurant.domain.RestaurantComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class JdbcTemplateRestaurantCommentRepository implements RestaurantCommentRepository {

    private final JdbcTemplate jdbcTemplate;
    private long sequence = 0L;

//    @Autowired
    public JdbcTemplateRestaurantCommentRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public RestaurantComment comSave(RestaurantComment restaurantComment) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("rescomment").usingGeneratedKeyColumns("commentindex");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("commentindex", ++sequence);
        parameters.put("commentMember", restaurantComment.getCommentMember());
        parameters.put("comment", restaurantComment.getComment());

        restaurantComment.setCommentTime(new Timestamp(System.currentTimeMillis()));
        parameters.put("commentTime", restaurantComment.getCommentTime());
        parameters.put("resindex", restaurantComment.getResindex());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        restaurantComment.setCommentindex(key.longValue());
        return restaurantComment;
    }

    @Override
    public RestaurantComment comFindByCommentIdx(long commentIndex) {
        List<RestaurantComment> result = jdbcTemplate.query("select * from rescomment where resindex =?", restaurantCommentRowMapper(), commentIndex);
        return result.stream().findAny().get();
    }

    @Override
    public List<RestaurantComment> comFindByResIdxList(long commentIndex) {
        List<RestaurantComment> result = jdbcTemplate.query("select * from rescomment where resindex=?", restaurantCommentRowMapper(), commentIndex);
        return result;
    }

    @Override
    public List<RestaurantComment> comFindAll() {
        List<RestaurantComment> result = jdbcTemplate.query("select * from rescomment", restaurantCommentRowMapper());
        return result;
    }

    @Override
    public RestaurantComment comUpdate(RestaurantComment restaurantComment) {
        return null;
    }

    @Override
    public void comDelete(long commentIndex) {
        jdbcTemplate.query("delete from rescomment where redindex=?",restaurantCommentRowMapper(),commentIndex);
    }

    private RowMapper<RestaurantComment> restaurantCommentRowMapper() {
        return (rs, rowNum) -> {
            RestaurantComment restaurantComment = new RestaurantComment();
            restaurantComment.setResindex(rs.getInt("resindex"));
            restaurantComment.setCommentMember(rs.getString("commentMember"));
            restaurantComment.setCommentTime(rs.getTimestamp("commentTime"));
            restaurantComment.setComment(rs.getString("comment"));
            restaurantComment.setCommentindex(rs.getInt("commentindex"));
            return restaurantComment;
        };
    }
}
