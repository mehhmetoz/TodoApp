package com.todo.app.web.controller;

import com.todo.app.core.TodoList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.File;

@Controller
@RequestMapping("/")
public class TodoController {
    
    private final TodoList todoList;
    
    public TodoController() {
        // Dosya yolunu kullanıcının çalışma dizinine göre ayarlıyoruz
        File currentDir = new File(".");
        String absolutePath = currentDir.getAbsolutePath();
        System.out.println("Çalışma dizini: " + absolutePath);
        
        this.todoList = new TodoList("web-todos.json");
    }
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("todos", todoList.getAllTodos());
        return "index";
    }
    
    @PostMapping("/add")
    public String addTodo(@RequestParam String title, @RequestParam String description) {
        System.out.println("Görev ekleniyor: " + title + " - " + description);
        todoList.addTodo(title, description);
        return "redirect:/";
    }
    
    @GetMapping("/toggle/{id}")
    public String toggleTodo(@PathVariable int id) {
        todoList.toggleTodo(id);
        return "redirect:/";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable int id) {
        todoList.removeTodo(id);
        return "redirect:/";
    }
} 