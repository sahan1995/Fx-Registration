/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.dao.custom.impl1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.reg.dao.custom1.StudentDAO;
import lk.ijse.reg.dao1.CurdUtil;
import lk.ijse.reg.db.DbConnection;
import lk.ijse.reg.entity.Student;

/**
 *
 * @author Sahan Rajakaruna
 */
public class StudentDAOImpl implements StudentDAO {

    @Override
    public ArrayList<Student> getAll() throws Exception {

        ResultSet rs = CurdUtil.exeResultSet("SELECT * FROM student");
        ArrayList<Student> allsstudent = new ArrayList<>();

        while (rs.next()) {
            Student student = new Student(rs.getString(1), rs.getString(2), rs.getString(3));
            allsstudent.add(student);

        }
        return allsstudent;

    }

    @Override
    public boolean save(Student student) throws Exception {

        return CurdUtil.excuteUpdate("INSERT INTO Student VALUES (?,?,?)", student.getId(),student.getName(),student.getAddress());

    }

    @Override
    public boolean delete(String id) throws Exception {

        return CurdUtil.excuteUpdate("DELETE FROM Student WHERE sid = ?", id);

    }

    @Override
    public Student findById(String id) throws Exception {

        ResultSet rs = CurdUtil.exeResultSet("SELECT * FROM Student WHERE sid = ?", id);

        if (rs.next()) {
            return new Student(rs.getString(1), rs.getString(2), rs.getString(3));

        } else {
            return null;
        }
    }

    @Override
    public boolean update(Student student) throws Exception {

        return CurdUtil.excuteUpdate("UPDATE Student SET name = ?, address = ? WHERE sid = ?", student.getName(), student.getAddress(), student.getId());

    }

    @Override
    public ArrayList<String> getCourses(String id) throws Exception {

        ResultSet rs = CurdUtil.exeResultSet("SELECT c.name from Course c INNER JOIN reg r on r.cid = c.cid INNER JOIN Student s on s.sid = r.sid WHERE s.sid =?", id);

        ArrayList<String> course = new ArrayList<>();

        while (rs.next()) {

            course.add(rs.getString(1));
        }
        
        return course;
    }

}
