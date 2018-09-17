/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.business.custom.impl;

import java.sql.Connection;
import lk.ijse.reg.business.custom.RegistrationBO;
import lk.ijse.reg.dao.custom.impl1.RegistrationDAOImpl;
import lk.ijse.reg.dao.custom.impl1.StudentDAOImpl;
import lk.ijse.reg.dao.custom1.RegDAO;
import lk.ijse.reg.dao.custom1.StudentDAO;
import lk.ijse.reg.dao1.DAOFactory;
import lk.ijse.reg.db.DbConnection;
import lk.ijse.reg.dto.RegDTO;
import lk.ijse.reg.dto.StudentDTO;
import lk.ijse.reg.entity.Reg;
import lk.ijse.reg.entity.Student;

/**
 *
 * @author Sahan Rajakaruna
 */
public class RegistationBOCustomImpl implements RegistrationBO {

    private StudentDAO stu = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.StudentDAO);

    private RegDAO regis = (RegDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RegDAO);

    public boolean RegsiterStudent(StudentDTO student, RegDTO reg) throws Exception {

//        Student studentEntity = new Student(student.getSid(), student.getName(), student.getAddress());
//        Reg regEntity = new Reg(reg.getSid(), reg.getCid(), reg.getFee());
//        Connection conn = DbConnection.getInstance().getConnection();
//        conn.setAutoCommit(false);
//        try {
//            boolean result1 = stu.save(studentEntity);
//            if (result1 == true) {
//                boolean result2 = regis.save(regEntity);
//                if (result2 == true) {
//                    conn.commit();
//                    return true;
//                } else {
//                    conn.rollback();
//                    return false;
//                }
//
//            } else {
//                conn.rollback();
//                return false;
//            }
//
//        } catch (Exception e) {
//
//            conn.rollback();
//
//            throw e;
//
//        } finally {
//            conn.setAutoCommit(true);
//        }
        Connection conn = DbConnection.getInstance().getConnection();
        conn.setAutoCommit(false);
        try {

            Student studentEntity = new Student(student.getSid(), student.getName(), student.getAddress());
            Reg regEntity = new Reg(reg.getSid(), reg.getCid(), reg.getFee());

            boolean result = stu.save(studentEntity);
            if (result == true) {
                boolean resulr2 = regis.save(regEntity);
                if (resulr2 == true) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            } else {
                conn.rollback();
                return false;
            }
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);

        }

    }

    public boolean RegisterCourse(RegDTO regDTO) throws Exception {

        Reg reg = new Reg(regDTO.getSid(), regDTO.getCid(), regDTO.getFee());

        return regis.save(reg);
    }

}
