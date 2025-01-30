package com.example.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoRepository.findAll());
        model.addAttribute("newTodo", new Todo());
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute Todo newTodo) {
        todoRepository.save(newTodo);
        return "redirect:/";
    }

    @PostMapping("/complete/{id}")
    public String completeTodo(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid todo ID"));
        todo.setCompleted(true);
        todoRepository.save(todo);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }
}