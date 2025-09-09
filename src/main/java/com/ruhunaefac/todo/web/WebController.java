package com.ruhunaefac.todo.web;

import com.ruhunaefac.todo.model.Task;
import com.ruhunaefac.todo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {
    private final TaskService service;
    public WebController(TaskService service) { this.service = service; }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", service.all());
        model.addAttribute("task", new Task());
        return "index";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("task") Task task, BindingResult br, Model model) {
        if (br.hasErrors()) { model.addAttribute("tasks", service.all()); return "index"; }
        service.addTask(task); return "redirect:/";
    }
}
