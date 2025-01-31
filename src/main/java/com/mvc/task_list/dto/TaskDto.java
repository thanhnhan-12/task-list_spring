package com.mvc.task_list.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private int id;

    @Size(min = 8, message = "Name must be at least 10 characters")
    private String name;

    @Size(min = 10, message = "Description must be at least 10 characters")
    private String description;

    private boolean completed;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
