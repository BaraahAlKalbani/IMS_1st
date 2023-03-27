package com.InstituteManagementSystem.System.app.Services;

import com.InstituteManagementSystem.System.app.Models.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service class that provides operations for managing teachers.
 */
@Service
public class TeacherService {

    private final HashMap<Long, Teacher> teachers = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    // Creates a Logger instance for the TeacherService class using the LoggerFactory.getLogger() method.
    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    /**
     * Creates a new teacher in the system.
     * @param teacher The Teacher object to be created.
     * @return The created Teacher object with the updated ID.
     */
    public Teacher create(Teacher teacher) {
        Long id = nextId.getAndIncrement();
        teacher.setId(Math.toIntExact(id));
        teachers.put(id, teacher);
        logger.info("Created teacher : " + teacher.getId()+" : "+teacher.getName()+" : "+teacher.getSalary()+" : "+teacher.getEmail());
        return teacher;
    }

    /**
     * Deletes the teacher with the given ID from the system.
     * @param id The ID of the teacher to be deleted.
     * @return The deleted Teacher object or null if it does not exist.
     */
    public Teacher delete(Long id) {
        return teachers.remove(id);
    }

    /**
     * Finds and returns the teacher with the given ID from the system.
     * @param id The ID of the teacher to be found.
     * @return The Teacher object with the given ID or null if it does not exist.
     */
    public Teacher findById(Long id) {
        return teachers.get(id);
    }

    /**
     * Returns a list of all the teachers in the system.
     * @return A list of Teacher objects in the system.
     */
    public List<Teacher> getAllTeachers() {
        return new ArrayList<>(teachers.values());
    }

}
