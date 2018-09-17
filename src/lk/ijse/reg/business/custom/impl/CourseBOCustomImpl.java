/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.business.custom.impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import lk.ijse.reg.business.custom.CourseBO;
import lk.ijse.reg.dao.custom.impl1.CourseDAOImpl;
import lk.ijse.reg.dao.custom1.CourseDAO;
import lk.ijse.reg.dao1.DAOFactory;
import lk.ijse.reg.db.DbConnection;
import lk.ijse.reg.dto.CourseDTO;
import lk.ijse.reg.entity.Course;

/**
 *
 * @author Sahan Rajakaruna
 */
public class CourseBOCustomImpl implements CourseBO {

    private CourseDAO course1 = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CourseDAO);

    public boolean addCourse(CourseDTO course) throws Exception {

        return course1.save(new Course(course.getCid(), course.getName(), course.getDuration()));
    }

    public ArrayList<CourseDTO> allCourses() throws Exception {

        ArrayList<Course> all = course1.getAll();

        ArrayList<CourseDTO> returnCourseDTOs = new ArrayList<>();
        for (Course course1 : all) {

            returnCourseDTOs.add(new CourseDTO(course1.getId(), course1.getName(), course1.getDuration()));

        }
        return returnCourseDTOs;
    }

    public CourseDTO findbyID(String id) throws Exception {

        Course findById = course1.findById(id);
        return new CourseDTO(findById.getId(), findById.getName(), findById.getDuration());

    }

    public boolean UpdateCourse(CourseDTO course) throws Exception {

        return course1.update(new Course(course.getCid(), course.getName(), course.getDuration()));
    }

    public boolean DeleteCourse(String id) throws Exception {

        return course1.delete(id);
    }

}
