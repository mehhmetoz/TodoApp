package com.todo.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.List;

public class TodoListTest {
    private TodoList todoList;
    private static final String TEST_FILE = "test_todos.json";

    @BeforeEach
    void setUp() {
        // Her test öncesi yeni bir TodoList oluştur ve test dosyasını kullan
        todoList = new TodoList(TEST_FILE);
        // Test dosyasını temizle
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testAddTodo() {
        todoList.addTodo("Test Görevi", "Test Açıklaması");
        List<Todo> todos = todoList.getAllTodos();
        
        assertEquals(1, todos.size());
        assertEquals("Test Görevi", todos.get(0).getTitle());
        assertEquals("Test Açıklaması", todos.get(0).getDescription());
        assertFalse(todos.get(0).isCompleted());
    }

    @Test
    void testToggleTodo() {
        todoList.addTodo("Test Görevi", "Test Açıklaması");
        List<Todo> todos = todoList.getAllTodos();
        int id = todos.get(0).getId();

        todoList.toggleTodo(id);
        assertTrue(todoList.getAllTodos().get(0).isCompleted());

        todoList.toggleTodo(id);
        assertFalse(todoList.getAllTodos().get(0).isCompleted());
    }

    @Test
    void testRemoveTodo() {
        todoList.addTodo("Silinecek Görev", "Test");
        List<Todo> todos = todoList.getAllTodos();
        int id = todos.get(0).getId();

        assertEquals(1, todos.size());
        todoList.removeTodo(id);
        assertEquals(0, todoList.getAllTodos().size());
    }

    @Test
    void testGetCompletedAndActiveTodos() {
        todoList.addTodo("Görev 1", "Test");
        todoList.addTodo("Görev 2", "Test");
        
        List<Todo> todos = todoList.getAllTodos();
        todoList.toggleTodo(todos.get(0).getId());

        assertEquals(1, todoList.getCompletedTodos().size());
        assertEquals(1, todoList.getActiveTodos().size());
    }

    @AfterEach
    void tearDown() {
        // Her test sonrası test dosyasını temizle
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }
} 