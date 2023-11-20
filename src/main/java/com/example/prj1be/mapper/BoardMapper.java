package com.example.prj1be.mapper;

import com.example.prj1be.domain.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("""
        INSERT INTO board (title, content, writer)
        VALUES (#{title}, #{content}, #{writer})
        """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
        //새로 생성된 키를 어디에 넣을건지 명시해줘야함
        // file이 어떤 게시물의 파일인지 알아야함
    int insert(Board board);

    @Select("""
        SELECT b.id,
               b.title,
               b.writer,
               m.nickName,
               b.inserted,
               COUNT(DISTINCT c.id) countComment,
               COUNT(DISTINCT l.id) countLike
        FROM board b JOIN member m ON b.writer = m.id
                     LEFT JOIN comment c ON b.id = c.boardId
                     LEFT JOIN boardLike l ON b.id = l.boardId
        WHERE b.content LIKE #{keyword}
            OR b.title LIKE  #{keyword}
        GROUP BY b.id
        ORDER BY b.id DESC
        LIMIT #{from}, 10
        """)
    List<Board> selectAll(Integer from, String keyword);

    @Select("""
        SELECT b.id,
               b.title, 
               b.content, 
               b.writer, 
               m.nickName,
               b.inserted
        FROM board b JOIN member m ON b.writer = m.id
        WHERE b.id = #{id}
        """)
    Board selectById(Integer id);

    @Delete("""
        DELETE FROM board
        WHERE id = #{id}
        """)
    int deleteById(Integer id);

    @Update("""
        UPDATE board
        SET title = #{title},
            content = #{content},
            writer = #{writer}
        WHERE id = #{id}
        """)
    int update(Board board);


    @Delete("""
        DELETE FROM board
        WHERE writer = #{writer}
        """)

    int deleteByWriter(String writer);

    @Select("""
        SELECT id
        FROM board
        WHERE writer = #{id}
        """)
    List<Integer> selectIdListByMemberId(String writer);

    @Select("""
        SELECT COUNT(*) FROM board
        WHERE title LIKE #{keyword}
            OR content LIKE #{keyword}
        """)
    int countAll(String keyword);
}
