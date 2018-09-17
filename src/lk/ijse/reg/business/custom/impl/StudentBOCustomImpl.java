/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.reg.business.custom.StudentBO;
import lk.ijse.reg.dao.custom.impl1.CourseDAOImpl;
import lk.ijse.reg.dao.custom.impl1.StudentDAOImpl;
import lk.ijse.reg.dao.custom1.CourseDAO;
import lk.ijse.reg.dao.custom1.StudentDAO;
import lk.ijse.reg.dto.StudentDTO;
import lk.ijse.reg.entity.Student;

/**
 *
 * @author Sahan Rajakaruna
 */
public class StudentBOCustomImpl implements StudentBO{

    private StudentDAO student = new StudentDAOImpl();
    private CourseDAO course = new CourseDAOImpl();

    public boolean addStudent(StudentDTO studentDTO) throws Exception {
        Student studentEntity = new Student(studentDTO.getSid(), studentDTO.getName(), studentDTO.getAddress());
        return student.save(studentEntity);

    }

    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
        Student studentEntity = new Student(studentDTO.getSid(), studentDTO.getName(), studentDTO.getAddress());
        return student.update(studentEntity);
    }

    public boolean deleteStudent(String id) throws Exception {

        return student.delete(id);

    }

    public StudentDTO findByid(String id) throws Exception {
        Student findById = student.findById(id);
        return new StudentDTO(findById.getId(), findById.getName(), findById.getAddress());

    }

    public ArrayList<StudentDTO> allStudents() throws Exception {

        ArrayList<Student> allstu = student.getAll();
        ArrayList<StudentDTO> returnAll = new ArrayList<>();
        for (Student student1 : allstu) {
            StudentDTO studentDTO = new StudentDTO(student1.getId(), student1.getName(), student1.getAddress());
            returnAll.add(studentDTO);

        }
        return returnAll;

    }

    public ArrayList<String> allcNames() throws Exception {
        return course.AllNames();
    }

    public String getCourseID(String name) throws Exception {

        return course.courseid(name);
    }

    public ArrayList<String> getCourses(String id) throws Exception {

        return student.getCourses(id);
    }
}
