package com.InstituteManagementSystem.System.app.Controllers;

import com.InstituteManagementSystem.System.app.Models.Teacher;
import com.InstituteManagementSystem.System.app.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controller class for managing teachers.
 */
@RestController
@RequestMapping(path = "/teachers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * Creates a new teacher.
     *
     * @param teacher the teacher object to create
     * @return the created teacher object with a 201 Created status code
     */
    @PostMapping
    public ResponseEntity<Teacher> create(@RequestBody Teacher teacher) {
        Teacher createdTeacher = teacherService.create(teacher);
        return ResponseEntity.created(URI.create("/teachers/" + createdTeacher.getId()))
                .body(createdTeacher);
    }

    /**
     * Updates an existing teacher by ID.
     *
     * @param id the ID of the teacher to update
     * @param teacher the updated teacher object
     * @return the updated teacher object with a 200 OK status code if the teacher exists,
     * or a 404 Not Found status code if the teacher does not exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> update(@PathVariable Long id, @RequestBody Teacher teacher) {
        Teacher existingTeacher = teacherService.findById(id);
        if (existingTeacher != null) {
            existingTeacher.setName(teacher.getName());
            existingTeacher.setEmail(teacher.getEmail());
            existingTeacher.setSalary(teacher.getSalary());
            return ResponseEntity.ok(existingTeacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a teacher by ID.
     *
     * @param id the ID of the teacher to delete
     * @return a 204 No Content status code if the teacher was successfully deleted,
     * or a 404 Not Found status code if the teacher does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Teacher teacher = teacherService.delete(id);
        if (teacher != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves a teacher by ID.
     *
     * @param id the ID of the teacher to retrieve
     * @return the retrieved teacher object with a 200 OK status code if the teacher exists,
     * or a 404 Not Found status code if the teacher does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> findById(@PathVariable Long id) {
        Teacher teacher = teacherService.findById(id);
        if (teacher != null) {
            return ResponseEntity.ok(teacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves a list of all teachers.
     *
     * @return a list of all teacher objects with a 200 OK status code
     */
    @GetMapping(produces = "application/json")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

}
