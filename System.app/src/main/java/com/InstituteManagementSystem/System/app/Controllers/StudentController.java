package com.InstituteManagementSystem.System.app.Controllers;

import com.InstituteManagementSystem.System.app.Models.Student;
import com.InstituteManagementSystem.System.app.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controller class for managing students.
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    /**
     * Constructor injection for StudentService.
     * @param studentService An instance of StudentService.
     */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Endpoint for creating a new student.
     * @param student An instance of Student to be created.
     * @return ResponseEntity with the created student and URI of the new resource.
     */
    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student createdStudent = studentService.create(student);
        return ResponseEntity.created(URI.create("/students/" + createdStudent.getId()))
                .body(createdStudent);
    }

    /**
     * Endpoint for updating an existing student.
     * @param id The ID of the student to be updated.
     * @param student An instance of Student with updated fields.
     * @return ResponseEntity with the updated student if it exists, or notFound() if it does not.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        Student existingStudent = studentService.findById(id);
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setEmail(student.getEmail());
            return ResponseEntity.ok(existingStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint for deleting a student.
     * @param id The ID of the student to be deleted.
     * @return ResponseEntity with no content if the student exists and was deleted, or notFound() if it does not.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Student student = studentService.delete(id);
        if (student != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint for retrieving a student by ID.
     * @param id The ID of the student to be retrieved.
     * @return ResponseEntity with the retrieved student if it exists, or notFound() if it does not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        Student student = studentService.findById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint for retrieving all students.
     * @return A List of all students in the database.
     */
    @GetMapping(produces = "application/json")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
