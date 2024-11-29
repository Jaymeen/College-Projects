package com.example.demo.auxpackage;

import com.example.demo.Entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuxService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private CoursesRepository courseRepository;

    public void addStudent(AuxStudentDTO studentDTO){
        Student student = new Student();
        student.setStudentName(studentDTO.getName());
        student.setGender(studentDTO.getGender());
        student.setLoginId(studentDTO.getLoginId());
        student.setPassword(studentDTO.getPassword());
        student.setStudentRollNo(studentDTO.getRollNo());


        studentRepository.save(student);

    }

    public void addFaculty(AuxFacultyDTO facultyDTO){
        Faculty faculty = new Faculty();
        faculty.setName(facultyDTO.getName());
        faculty.setEmail(facultyDTO.getEmail());
        faculty.setPassword(facultyDTO.getPassword());
        faculty.setFacCode(facultyDTO.getFacCode());

        facultyRepository.save(faculty);
    }

    public void addCourse(AuxCourseDTO courseDTO){
        Courses course = new Courses();
        course.setCourseCode(courseDTO.getCourseCode());
        course.setCourseName(courseDTO.getCourseName());
        course.setCredit(courseDTO.getCredit());
        switch (courseDTO.getSpecialization()) {
            case 1:
                course.setSpecialization(Specialization.THEORY_AND_SYSTEMS);
                break;
            case 2:
                course.setSpecialization(Specialization.MACHINE_LEARNING);
                break;
            case 3:
                course.setSpecialization(Specialization.NETWORKING);
            default:
                break;
        }
        course.setSpecialization(Specialization.MACHINE_LEARNING);
        course.setMaxStudents(courseDTO.getMaxStudents());
        course.setMaxTA(courseDTO.getMaxTa());
        Optional<Faculty> facOption = facultyRepository.findByFacCode(courseDTO.getFacId());
        if(facOption.isPresent()){
            course.setFaculty(facOption.get());
        }else{
            course.setFaculty(null);
        }

        courseRepository.save(course);
    }

}