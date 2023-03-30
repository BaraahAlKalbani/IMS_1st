package com.InstituteManagementSystem.System.app.Services;

import com.InstituteManagementSystem.System.app.Models.Student;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service class that provides operations for managing students.
 */
@Service
public class StudentService {

    // A HashMap to store the students using their ID as key
    private final HashMap<Long, Student> students = new HashMap<>();

    // An AtomicLong to generate unique IDs for each new student
    private final AtomicLong nextId = new AtomicLong(1);

    // Creates a Logger instance for the StudentService class using the LoggerFactory.getLogger() method.
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    /**
     * Create a new student and add it to the HashMap
     *
     * @param student The student object to be created
     * @return The created student object
     */
    public Student create(Student student) {
        Long id = nextId.getAndIncrement();
        student.setId(Math.toIntExact(id));
        students.put(id, student);
        logger.info("Created student : " + student.getId()+" : "+student.getName()+" : "+student.getEmail());
        return student;
    }

    /**
     * Remove the student with the given ID from the HashMap
     *
     * @param id The ID of the student to be deleted
     * @return The deleted student object, or null if no student with the given ID was found
     */
    public Student delete(Long id) {
        return students.remove(id);
    }

    /**
     * Find and return the student with the given ID from the HashMap
     *
     * @param id The ID of the student to be found
     * @return The student object with the given ID, or null if no student with the given ID was found
     */
    public Student findById(Long id) {
        return students.get(id);
    }

    /**
     * Return a List of all students in the HashMap
     *
     * @return A List of all student objects in the HashMap
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }
}
