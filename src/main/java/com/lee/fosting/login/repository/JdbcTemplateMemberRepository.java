package com.lee.fosting.login.repository;

import com.lee.fosting.login.domain.MemberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

//@Repository
public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;
    private static long index = 0L;

//    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public MemberInfo SignUp(MemberInfo memberInfo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("memberinfo").usingGeneratedKeyColumns("memberindex");

        Map<String, Object> parameters = new HashMap<>();

        memberInfo.setMemberIndex(++index);
        parameters.put("memberindex", memberInfo.getMemberIndex());
        parameters.put("membername", memberInfo.getMemberName());
        parameters.put("memberpass", memberInfo.getMemberPass());
        parameters.put("memberid", memberInfo.getMemberId());

        memberInfo.setMemberRole("role");
        parameters.put("memberrole", memberInfo.getMemberRole());

        parameters.put("memberphone", memberInfo.getMemberPhone());
        parameters.put("memberemail", memberInfo.getMemberEmail());

        memberInfo.setDateCreated(LocalDate.now());
        parameters.put("datecreated", memberInfo.getDateCreated());

        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        memberInfo.setMemberIndex(key.longValue());
        return memberInfo;
    }

    @Override
    public Optional<MemberInfo> FindById(String id) {
        List<MemberInfo> result = jdbcTemplate.query("select * from MemberInfo where memberid =?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<MemberInfo> FindByName(String name) {
        List<MemberInfo> result = jdbcTemplate.query("select * from MemberInfo where membername =?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    public long FindByIndex(long memberIndex) {
        MemberInfo memberInfo = jdbcTemplate.queryForObject("select * from MemberInfo where memberindex =?", memberRowMapper(), memberIndex);
        return memberInfo.getMemberIndex();
    }


//    @Override
//    public MemberInfo Update(MemberInfo memberInfo) {
//        List<MemberInfo> result = jdbcTemplate.query("select * from MemberInfo where id =?", memberRowMapper(), memberInfo.getMemberName());
//        return result.stream().findAny();
//    }

    @Override
    public void Delete(MemberInfo memberInfo) {
        jdbcTemplate.query("delete from MemberInfo where memberid =?", memberRowMapper(), memberInfo.getMemberId());
    }

    private RowMapper<MemberInfo> memberRowMapper() {
        return (rs, rowNum) -> {
            MemberInfo member = new MemberInfo();
            member.setMemberId(rs.getString("memberid"));
            member.setMemberName(rs.getString("membername"));
            member.setMemberPass(rs.getString("memberpass"));
            member.setMemberIndex(rs.getLong("memberindex"));
            return member;
        };
    }
}
