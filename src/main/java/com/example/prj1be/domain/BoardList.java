package com.example.prj1be.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BoardList {
    private Integer id;
    private String title;
    private String by;
    private LocalDateTime at;
}
