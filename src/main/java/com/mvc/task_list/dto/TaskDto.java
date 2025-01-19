package com.mvc.task_list.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private int id;
    private String name;
    private String description;
    private boolean completed;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
