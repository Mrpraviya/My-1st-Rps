package com.ruhunaefac.todo.service;

import com.ruhunaefac.todo.model.Task;
import com.ruhunaefac.todo.repo.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repo;
    public TaskService(TaskRepository repo) { this.repo = repo; }

    @Transactional
    public Task addTask(@Valid Task task) { task.setCompleted(false); return repo.save(task); }

    public List<Task> all() { return repo.findAll(); }

    @Transactional
    public Task toggle(Long id) {
        Task t = repo.findById(id).orElseThrow();
        t.setCompleted(!t.isCompleted());
        return repo.save(t);
    }
}