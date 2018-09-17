/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.dao1;

import lk.ijse.reg.dao.custom.impl1.CourseDAOImpl;
import lk.ijse.reg.dao.custom.impl1.RegistrationDAOImpl;
import lk.ijse.reg.dao.custom.impl1.StudentDAOImpl;
import lk.ijse.reg.dao.custom1.CourseDAO;
import lk.ijse.reg.dao.custom1.RegDAO;
import lk.ijse.reg.dao.custom1.StudentDAO;

/**
 *
 * @author Sahan Rajakaruna
 */
public class DAOFactory {

    private static DAOFactory getDAOFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        if (getDAOFactory == null) {
            getDAOFactory = new DAOFactory();
        }
        return getDAOFactory;
    }

    public enum DAOTypes {

        CourseDAO, StudentDAO, RegDAO;
    }

    public SuperDAO getDAO(DAOTypes dAOTypes) {

        switch (dAOTypes) {
            case CourseDAO:
                return new CourseDAOImpl();
            case RegDAO:
                return new RegistrationDAOImpl();
            case StudentDAO:
                return new StudentDAOImpl();
            default:
                return null;
        }
    }

}
