/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.business.custom;

import java.util.ArrayList;
import lk.ijse.reg.business.SuperBO;
import lk.ijse.reg.dto.StudentDTO;

/**
 *
 * @author Sahan Rajakaruna
 */
public interface StudentBO extends SuperBO {

    public boolean addStudent(StudentDTO studentDTO) throws Exception;

    public boolean updateStudent(StudentDTO studentDTO) throws Exception;

    public boolean deleteStudent(String id) throws Exception;

    public StudentDTO findByid(String id) throws Exception;

    public ArrayList<StudentDTO> allStudents() throws Exception;

    public ArrayList<String> allcNames() throws Exception;

    public String getCourseID(String name) throws Exception;

    public ArrayList<String> getCourses(String id) throws Exception;

}
