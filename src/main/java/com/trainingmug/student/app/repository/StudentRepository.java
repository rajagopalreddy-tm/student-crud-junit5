package com.trainingmug.student.app.repository;


import com.trainingmug.student.app.exception.StudentDataNullException;
import com.trainingmug.student.app.exception.StudentNotFoundException;
import com.trainingmug.student.app.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private final List<Student> studentList = new ArrayList<>();

//    public StudentRepository() {
//        studentList.add(new Student(1, "Arjun Reddy", 20));
//        studentList.add(new Student(2, "Salman Khan", 22));
//        studentList.add(new Student(3, "Pavan Kalyan", 23));
//    }

    public void addStudent(Student student) throws StudentDataNullException {
        if (student == null) {
            throw new StudentDataNullException("Student cannot be null");
        }
        studentList.add(student);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentList);
    }

    public Optional<Student> getStudentById(int id) throws StudentNotFoundException {
        Optional<Student> studentData =  studentList.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
        if (studentData.isPresent()){
            return studentData;
        } else {
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }
    }

    public void updateStudentData(Student student, int id) throws StudentNotFoundException {
        Optional<Student> studentData = getStudentById(id);
        if (studentData.isPresent()) {
            int index = studentList.indexOf(studentData.get());
            studentList.set(index, student);
        } else {
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }
    }

    public void deleteStudentById(int id) throws StudentNotFoundException {
        boolean removed = studentList.removeIf(student -> student.getId() == id);
        if (!removed) {
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }
    }
}
