package com.example.prj1be.mapper;


import com.example.prj1be.domain.Board;
import com.example.prj1be.domain.BoardList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    @Insert("""
            INSERT INTO board (title, content, writer)
            VALUES (#{title}, #{content}, #{writer})
            """)
    int insert(Board board);

    @Insert("""
            INSERT INTO board
            """)
    String list(BoardList boardList);
}
