package com.example.prj1be.controller;

import com.example.prj1be.domain.Comment;
import com.example.prj1be.domain.Member;
import com.example.prj1be.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService service;

    @PostMapping("add")
    //로그인 정보 알아야되니까 @SessionAttribute이용
    public ResponseEntity add(@RequestBody Comment comment,
                              @SessionAttribute(value = "login", required = false) Member login) {

        if (login == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (service.validate(comment)) {
            if (service.add(comment, login)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("list")
    public List<Comment> list(@RequestParam("id") Integer boardId) {
        return service.list(boardId);
        /*읽는거라 권한은 필요없음*/
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable Integer id,
                                 @SessionAttribute(value = "login", required = false) Member login) {
        if (login == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (service.hasAccess(id, login)) {
            if (service.remove(id)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


}
