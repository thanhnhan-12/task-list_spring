package com.mvc.task_list.service.Task;

import com.mvc.task_list.dto.TaskDto;
import com.mvc.task_list.model.Task;

public interface TaskService {
    Task createTask(TaskDto taskDto);
}
