package com.example.prj1be.mapper;

import com.example.prj1be.domain.Auth;
import com.example.prj1be.domain.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {


    @Insert("""
            INSERT INTO member( id, password, email, nickName)
            VALUES ( #{id}, #{password}, #{email}, #{nickName})
            """)
    int insert(Member member);

    @Select("""
            SELECT id FROM member
            WHERE id = #{id}
            """)
    String selectId(String id);

    @Select("""
            SELECT email from member
            WHERE email = #{email}
            """)
    String selectEmail(String email);

    @Select("""
            SELECT id, password, email, nickName, inserted
            FROM member
            ORDER BY inserted DESC
            """)
    List<Member> selectAll();


    @Select("""
            SELECT *
            FROM member
            WHERE id = #{id}
            """)
    Member selectById(String id);


    @Delete("""
            DELETE FROM member
            WHERE ID = #{id}
            """)
    int deleteById(String id);


    // dynamic sql이용
    @Update("""
            <script>
            UPDATE member
            SET 
                <if test="password != ''">
                password = #{password},
                </if>
                email = #{email},
                nickName = #{nickName}
            WHERE id = #{id}
            </script>
            """)
    int update(Member member);


    @Select("""
            SELECT nickName
            FROM member
            WHERE nickName = #{nickName}
            """)
    String selectNickName(String nickName);

    @Select("""
            SELECT * FROM auth
            WHERE memberId = #{id}
            """)
    List<Auth> selectAuthById(String id);
}
