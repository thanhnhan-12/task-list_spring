package com.mvc.task_list.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.task_list.dto.TaskDto;
import com.mvc.task_list.model.Task;
import com.mvc.task_list.service.Task.TaskService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/get-task")
    public ResponseEntity<List<Task>> getUserTasks(@RequestParam(required = false) String name) {
        List<Task> tasks = taskService.getUserTasks(name);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/create-task")
    public ResponseEntity<?> createTask(@RequestBody @Valid TaskDto taskDto) {
        Task task = taskService.createTask(taskDto);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/update-task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody TaskDto taskDto) {
        Task task = taskService.updateTaskById(id, taskDto);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.ok("Task deleted successfully.");
    }
}