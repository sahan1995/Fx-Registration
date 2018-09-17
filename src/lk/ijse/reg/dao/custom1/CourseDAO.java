/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.dao.custom1;

import java.util.ArrayList;
import lk.ijse.reg.dao1.CurdDAO;
import lk.ijse.reg.dao1.SuperDAO;
import lk.ijse.reg.entity.Course;

/**
 *
 * @author Sahan Rajakaruna
 */
public interface CourseDAO extends CurdDAO<Course, String> {

    public ArrayList<String> AllNames() throws Exception;

    public String courseid(String name) throws Exception;

}
