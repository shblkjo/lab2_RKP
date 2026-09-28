package ru.mygroup.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mygroup.lab2.Student;
import ru.mygroup.lab2.repository.StudentRepository;

import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //Просмотр данных студена
    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable("id") Long id) {
        Optional<Student> optionalStudent =
                studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            return "redirect:/students";
        }
        model.addAttribute("student", optionalStudent.get());
        return "details";
    }

    //Редактирование
    @GetMapping("/update/{id}")
    public String editStudent(Model model, @PathVariable("id") Long id) {
        Optional<Student> optionalStudent =
                studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            return "redirect:/students";
        }
        model.addAttribute("student", optionalStudent.get());
        return "student-form";
    }

    //Сохранение изменений
    @PostMapping("/update")
    public String editStudent(@ModelAttribute Student student, Model model) {
        if (student.getId() == null || !studentRepository.existsById(student.getId())) {
            return "redirect:/students";
        }
        studentRepository.save(student);
        return  "redirect:/students";
    }

    //Удаление студента
    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        }
        return  "redirect:/students";
    }

    //Список всех студентов
    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "students";
    }

    //Создание студента
    @GetMapping("/new")
    public String createStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    //Сохранение нового студента
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

}
