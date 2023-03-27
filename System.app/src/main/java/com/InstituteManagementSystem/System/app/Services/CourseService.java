package com.InstituteManagementSystem.System.app.Services;

import com.InstituteManagementSystem.System.app.Models.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * CourseService class manages the CRUD operations of Course objects in the Institute Management System.
 */
@Service
public class CourseService {
    // Creates a Logger instance for the CourseService class using the LoggerFactory.getLogger() method.
    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);
    // HashMap to store Course objects
    private final HashMap<Long, Course> courses = new HashMap<>();


    /**
     * Adds a new Course to the system.
     *
     * @param course the Course object to add
     * @return the added Course object
     */
    public Course addCourse(Course course) {
        long id = courses.size() + 1; // auto-generate ID
        course.setId(id);
        courses.put(id, course);
        return course;
    }

    /**
     * Deletes a Course from the system.
     *
     * @param courseId the ID of the Course object to delete
     */
    public void deleteCourse(Long courseId) {
        courses.remove(courseId);
    }

    /**
     * Updates an existing Course in the system.
     *
     * @param course the Course object to update
     * @return the updated Course object
     */
    public Course updateCourse(Course course) {
        courses.put(course.getId(), course);
        return course;
    }

    /**
     * Gets a Course from the system by its ID.
     *
     * @param courseId the ID of the Course object to get
     * @return the Course object with the given ID, or null if not found
     */
    public Course getCourse(Long courseId) {
        return courses.get(courseId);
    }

    /**
     * Gets a list of all Courses in the system.
     *
     * @return a List of all Course objects
     */
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }
}