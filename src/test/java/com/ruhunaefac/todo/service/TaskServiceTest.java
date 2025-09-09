package com.ruhunaefac.todo.service;

import com.ruhunaefac.todo.model.Task;
import com.ruhunaefac.todo.repo.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Test
    void addTask_setsCompletedFalse_andSaves() {
        TaskRepository repo = mock(TaskRepository.class);
        TaskService svc = new TaskService(repo);
        Task input = new Task("Buy milk");
        when(repo.save(any(Task.class))).thenAnswer(i -> i.getArgument(0));

        Task saved = svc.addTask(input);

        assertFalse(saved.isCompleted());
        ArgumentCaptor<Task> cap = ArgumentCaptor.forClass(Task.class);
        verify(repo).save(cap.capture());
        assertEquals("Buy milk", cap.getValue().getTitle());
    }

    @Test
    void toggle_flipsCompleted() {
        TaskRepository repo = mock(TaskRepository.class);
        TaskService svc = new TaskService(repo);
        Task existing = new Task("X"); existing.setCompleted(false);
        when(repo.findById(1L)).thenReturn(Optional.of(existing));
        when(repo.save(any(Task.class))).thenAnswer(i -> i.getArgument(0));

        Task after = svc.toggle(1L);
        assertTrue(after.isCompleted());
    }
}
