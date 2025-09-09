package com.ruhunaefac.todo.web;

import com.ruhunaefac.todo.model.Task;
import com.ruhunaefac.todo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {
    private final TaskService service;
    public TaskRestController(TaskService service) { this.service = service; }

    @GetMapping public List<Task> list() { return service.all(); }

    @PostMapping
    public ResponseEntity<Task> create(@Valid @RequestBody Task t) {
        return new ResponseEntity<>(service.addTask(t), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/toggle")
    public Task toggle(@PathVariable Long id) { return service.toggle(id); }
}
