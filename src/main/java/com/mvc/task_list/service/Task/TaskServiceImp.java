package com.mvc.task_list.service.Task;

import org.springframework.stereotype.Service;

import com.mvc.task_list.dto.TaskDto;
import com.mvc.task_list.model.Task;

@Service
public class TaskServiceImp implements TaskService {

    @Override
    public Task createTask(TaskDto taskDto) {
        throw new UnsupportedOperationException("Unimplemented method 'createTask'");
    }

}
