package com.mvc.task_list.mapper;

import org.springframework.stereotype.Component;

import com.mvc.task_list.dto.TaskDto;
import com.mvc.task_list.model.Task;

@Component
public class TaskMapper {
    // Convert Task to TaskDto
    public TaskDto toTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setCompleted(task.isCompleted());
        taskDto.setCreatedDate(task.getCreatedDate());
        taskDto.setUpdatedDate(task.getUpdatedDate());

        return taskDto;
    }

    // Convert TaskDto to Task
    public Task toEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setCompleted(taskDto.isCompleted());
        return task;
    }
}
