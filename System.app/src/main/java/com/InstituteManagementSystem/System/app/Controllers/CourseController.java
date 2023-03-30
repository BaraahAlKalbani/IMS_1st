package com.InstituteManagementSystem.System.app.Controllers;

import com.InstituteManagementSystem.System.app.Models.Course;
import com.InstituteManagementSystem.System.app.Services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing courses.
 */
@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CourseController {

    private final CourseService courseService;

    /**
     * Constructor for CourseController.
     * @param courseService CourseService object used to interact with the Course repository.
     */
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Adds a new course to the Course repository.
     * @param course Course object to be added.
     * @return ResponseEntity containing the added Course object.
     */
    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course newCourse = courseService.addCourse(course);
        return ResponseEntity.ok(newCourse);
    }

    /**
     * Deletes a course from the Course repository.
     * @param courseId ID of the course to be deleted.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing course in the Course repository.
     * @param courseId ID of the course to be updated.
     * @param course Updated Course object.
     * @return ResponseEntity containing the updated Course object.
     */
    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long courseId, @RequestBody Course course) {
        course.setId(courseId);
        Course updatedCourse = courseService.updateCourse(course);
        return ResponseEntity.ok(updatedCourse);
    }

    /**
     * Retrieves a course from the Course repository.
     * @param courseId ID of the course to be retrieved.
     * @return ResponseEntity containing the retrieved Course object.
     */
    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable Long courseId) {
        Course course = courseService.getCourse(courseId);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    /**
     * Retrieves all courses from the Course repository.
     * @return ResponseEntity containing a List of all Course objects.
     */
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
}
