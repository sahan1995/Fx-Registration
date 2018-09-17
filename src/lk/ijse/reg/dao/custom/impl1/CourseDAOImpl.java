/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.dao.custom.impl1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.reg.dao.custom1.CourseDAO;
import lk.ijse.reg.dao1.CurdUtil;
import lk.ijse.reg.db.DbConnection;
import lk.ijse.reg.entity.Course;

/**
 *
 * @author Sahan Rajakaruna
 */
public class CourseDAOImpl implements CourseDAO {

    @Override
    public ArrayList<Course> getAll() throws Exception {
        ResultSet rs = CurdUtil.exeResultSet("SELECT * FROM Course");

        ArrayList<Course> course = new ArrayList<>();
        while (rs.next()) {

            Course course1 = new Course(rs.getString(1), rs.getString(2), rs.getString(3));
            course.add(course1);

        }
        return course;

    }

    @Override
    public boolean save(Course entity) throws Exception {

        return CurdUtil.excuteUpdate("INSERT INTO Course VALUES (?,?,?)", entity.getId(), entity.getName(), entity.getDuration());

    }

    @Override
    public boolean delete(String id) throws Exception {

        return CurdUtil.excuteUpdate("DELETE FROM Course WHERE cid = ?", id);

    }

    @Override
    public Course findById(String id) throws Exception {

        ResultSet rs = CurdUtil.exeResultSet("SELECT * FROM Course WHERE cid = ?", id);

        if (rs.next()) {
            return new Course(rs.getString(1), rs.getString(2), rs.getString(3));
        } else {
            return null;
        }

    }

    @Override
    public boolean update(Course entity) throws Exception {
        return CurdUtil.excuteUpdate("UPDATE Course SET name = ?, duration = ? WHERE cid = ?", entity.getName(), entity.getDuration(), entity.getId());

    }

    @Override
    public ArrayList<String> AllNames() throws Exception {

        ResultSet rs = CurdUtil.exeResultSet("SELECT name FROM Course");

        ArrayList<String> cnames = new ArrayList<>();
        while (rs.next()) {
            cnames.add(rs.getString(1));
        }
        return cnames;
    }

    @Override
    public String courseid(String name) throws Exception {
        ResultSet rs = CurdUtil.exeResultSet("SELECT cid FROM Course WHERE name = ?", name);
       
        if (rs.next()) {
            return rs.getString(1);
        } else {
            return null;

        }
    }

}
