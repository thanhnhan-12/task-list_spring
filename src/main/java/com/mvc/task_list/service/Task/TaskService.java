package com.mvc.task_list.service.Task;

import java.util.List;

import com.mvc.task_list.dto.TaskDto;
import com.mvc.task_list.model.Task;

public interface TaskService {
    List<Task> getUserTasks(String name);

    Task createTask(TaskDto taskDto);

    Task updateTaskById(int id, TaskDto taskDto);

    void deleteTaskById(int id);
}