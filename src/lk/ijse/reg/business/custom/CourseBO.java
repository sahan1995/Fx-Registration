/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.business.custom;

import java.util.ArrayList;
import lk.ijse.reg.business.SuperBO;
import lk.ijse.reg.dto.CourseDTO;

/**
 *
 * @author Sahan Rajakaruna
 */
public interface CourseBO extends SuperBO {

    public boolean addCourse(CourseDTO course) throws Exception;

    public ArrayList<CourseDTO> allCourses() throws Exception;

    public CourseDTO findbyID(String id) throws Exception;

    public boolean UpdateCourse(CourseDTO course) throws Exception;

    public boolean DeleteCourse(String id) throws Exception;
}
