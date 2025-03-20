package com.todo.app;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class TodoList {
    private List<Todo> todos;
    private int nextId;
    private final String saveFile;
    private final Gson gson;

    public TodoList() {
        this("todos.json");
    }

    public TodoList(String saveFile) {
        this.gson = new Gson();
        this.todos = new ArrayList<>();
        this.nextId = 1;
        this.saveFile = saveFile;
        loadTodos();
    }

    private void loadTodos() {
        File file = new File(saveFile);
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type todoListType = new TypeToken<ArrayList<Todo>>(){}.getType();
                List<Todo> loadedTodos = gson.fromJson(reader, todoListType);
                if (loadedTodos != null) {
                    this.todos = loadedTodos;
                    // En yüksek ID'yi bul
                    this.nextId = todos.stream()
                            .mapToInt(Todo::getId)
                            .max()
                            .orElse(0) + 1;
                }
            } catch (IOException e) {
                System.err.println("Görevler yüklenirken hata oluştu: " + e.getMessage());
            }
        }
    }

    private void saveTodos() {
        try (Writer writer = new FileWriter(saveFile)) {
            gson.toJson(todos, writer);
        } catch (IOException e) {
            System.err.println("Görevler kaydedilirken hata oluştu: " + e.getMessage());
        }
    }

    public void addTodo(String title, String description) {
        Todo todo = new Todo(nextId++, title, description);
        todos.add(todo);
        saveTodos();
    }

    public void removeTodo(int id) {
        todos.removeIf(todo -> todo.getId() == id);
        saveTodos();
    }

    public void toggleTodo(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                todo.setCompleted(!todo.isCompleted());
                saveTodos();
                break;
            }
        }
    }

    public List<Todo> getAllTodos() {
        return new ArrayList<>(todos);
    }

    public List<Todo> getCompletedTodos() {
        return todos.stream()
                .filter(Todo::isCompleted)
                .toList();
    }

    public List<Todo> getActiveTodos() {
        return todos.stream()
                .filter(todo -> !todo.isCompleted())
                .toList();
    }
} 