package com.mvc.task_list.service.Task;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mvc.task_list.dto.TaskDto;
import com.mvc.task_list.mapper.TaskMapper;
import com.mvc.task_list.model.Task;
import com.mvc.task_list.model.User;
import com.mvc.task_list.repository.TaskRepository;
import com.mvc.task_list.repository.UserRepository;
import com.mvc.task_list.service.User.CustomUserDetailService;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private CustomUserDetailService userDetailService;

    @Override
    public List<Task> getUserTasks(String name) {
        User authenticatedUser = userDetailService.getAuthenticatedUser();
        return taskRepository.findTaskByUserId(authenticatedUser.getId(), name);
    }

    @Override
    public Task createTask(TaskDto taskDto) {
        User authenticatedUser = userDetailService.getAuthenticatedUser();

        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setCompleted(taskDto.isCompleted());
        task.setUser(authenticatedUser);

        return taskRepository.save(task);
    }

    @Override
    public Task updateTaskById(int id, TaskDto taskDto) {
        User authenticatedUser = userDetailService.getAuthenticatedUser();

        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setCompleted(taskDto.isCompleted());
            task.setUser(authenticatedUser);

            return taskRepository.save(task);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task with id " + id + " not found.");
        }
    }

    @Override
    public void deleteTaskById(int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task with id " + id + " not found.");
        }
    }
}