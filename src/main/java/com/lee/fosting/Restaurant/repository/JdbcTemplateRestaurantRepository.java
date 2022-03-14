package com.lee.fosting.Restaurant.repository;

import com.lee.fosting.Restaurant.domain.RestaurantInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.*;

//@Repository
public class JdbcTemplateRestaurantRepository implements RestaurantRepository {

    private final JdbcTemplate jdbcTemplate;
    private static long sequence = 0L;

//    @Autowired
    public JdbcTemplateRestaurantRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public RestaurantInfo resSave(RestaurantInfo restaurantInfo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("restaurantinfo").usingGeneratedKeyColumns("resindex");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("resindex", restaurantInfo.getResindex());
        parameters.put("rescategory", restaurantInfo.getResCategory());
        parameters.put("resname", restaurantInfo.getResName());
        parameters.put("resmember", restaurantInfo.getResMember());

        restaurantInfo.setResPostDate(new Timestamp(System.currentTimeMillis()));
        parameters.put("respostdate", restaurantInfo.getResPostDate());

        parameters.put("reslocate", restaurantInfo.getResLocate());
        parameters.put("respostname", restaurantInfo.getResPostName());
        parameters.put("resimglocate", restaurantInfo.getResImgLocate());
        parameters.put("rescomment", restaurantInfo.getResComment());
        parameters.put("resmember", restaurantInfo.getResMember());
        parameters.put("resrecommend", restaurantInfo.getResRecommend());
        parameters.put("reslatlng", restaurantInfo.getResLatLng());
        parameters.put("resrecommendcookie", '.');


        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        restaurantInfo.setResindex(key.longValue());
        return restaurantInfo;
    }

    @Override
    public Optional<RestaurantInfo> resFindByResIndex(long index) {
        List<RestaurantInfo> result = jdbcTemplate.query("select * from restaurantinfo where resindex = ?", restaurantRowMapper(), index);
        return result.stream().findAny();
    }

    @Override
    public Optional<RestaurantInfo> resFindByResName(RestaurantInfo restaurantInfo) {
        List<RestaurantInfo> result = jdbcTemplate.query("select * from restaurantinfo where name = ?", restaurantRowMapper(), restaurantInfo.getResName());
        return result.stream().findAny();
    }

    @Override
    public List<RestaurantInfo> findResAll() {
        List<RestaurantInfo> list = jdbcTemplate.query("select * from RESTAURANTINFO", restaurantRowMapper());
        return list;
    }

    @Override
    public List<RestaurantInfo> resDescList() {
        List<RestaurantInfo> list = jdbcTemplate.query("SELECT * FROM RESTAURANTINFO order by RESPOSTDATE desc", restaurantRowMapper());
        return list;
    }

    @Override
    public List<RestaurantInfo> resRecommendAscList() {
        List<RestaurantInfo> list = jdbcTemplate.query("select * from RESTAURANTINFO order by  RESRECOMMEND desc", restaurantRowMapper());
        return list;
    }

    @Override
    public List<RestaurantInfo> resRandomList() {
        List<RestaurantInfo> list = jdbcTemplate.query("select * from RESTAURANTINFO order by  rand()", restaurantRowMapper());
        return list;
    }

    @Override
    public List<RestaurantInfo> findResSearch(String search) {
        String sql = "SELECT * FROM RESTAURANTINFO where rescomment || respostname || rescategory || reslocate like ? order by resrecommend desc";
        String wrappedSearch = "%" + search + "%";
        List<RestaurantInfo> result = jdbcTemplate.query(sql, restaurantRowMapper(), wrappedSearch);
        return result;
    }

    @Override
    public void resResUpdate(RestaurantInfo restaurantInfo, int residx) {
        jdbcTemplate.update("update restaurantinfo set rescategory = ? ,respostname = ?,resname = ?,resComment = ?,reslocate = ?,resImgLocate = ?,resLatLng = ?  where resindex = ?",
                restaurantInfo.getResCategory(),
                restaurantInfo.getResPostName(),
                restaurantInfo.getResName(),
                restaurantInfo.getResComment(),
                restaurantInfo.getResLocate(),
                restaurantInfo.getResImgLocate(),
                restaurantInfo.getResLatLng(),
                residx);
    }

    @Override
    public void resRecUpdate(RestaurantInfo restaurantInfo, String memberIdx) {
        jdbcTemplate.update("update restaurantinfo set resrecommend = resrecommend + ? where resindex = ?",
                restaurantInfo.getResRecommend(), restaurantInfo.getResindex());
        jdbcTemplate.update("update restaurantinfo set RESRECOMMENDCOOKIE = concat(RESRECOMMENDCOOKIE , ?) where resindex = ?",
                memberIdx, restaurantInfo.getResindex());
    }

    @Override
    public void resDelete(int residx) {
        jdbcTemplate.update("delete from restaurantinfo where resindex = ?", residx);
    }

    private RowMapper<RestaurantInfo> restaurantRowMapper() {
        //ResultSet rs를 받아 restaurantInfo객체에 set하여 반환한다.
        //결과적으로 query문에서 객체를 반환받을 수 있다.
        return (rs, rowNum) -> {
            RestaurantInfo restaurantInfo = new RestaurantInfo();
            restaurantInfo.setResindex(rs.getInt("resindex"));
            restaurantInfo.setResCategory(rs.getString("rescategory"));
            restaurantInfo.setResPostName(rs.getString("respostname"));
            restaurantInfo.setResName(rs.getString("resname"));
            restaurantInfo.setResLocate(rs.getString("reslocate"));
            restaurantInfo.setResComment(rs.getString("rescomment"));
            restaurantInfo.setResMember(rs.getString("resmember"));
            restaurantInfo.setResRecommend(rs.getDouble("resrecommend"));
            restaurantInfo.setResPostDate(rs.getTimestamp("respostdate"));
            restaurantInfo.setResImgLocate(rs.getString("resimglocate"));
            restaurantInfo.setResLatLng(rs.getString("reslatlng"));
            restaurantInfo.setResRecommendCookie(rs.getString("resrecommendcookie"));
            return restaurantInfo;
        };
    }
}