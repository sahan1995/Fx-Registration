/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.business;

import lk.ijse.reg.business.custom.impl.CourseBOCustomImpl;
import lk.ijse.reg.business.custom.impl.RegistationBOCustomImpl;
import lk.ijse.reg.business.custom.impl.StudentBOCustomImpl;

/**
 *
 * @author Sahan Rajakaruna
 */
public class FactorBO {

    private static FactorBO getFactorBO;

    private FactorBO() {

    }

    public enum BOtypes {
        course, student, regis;
    }

    public static FactorBO getinstace() {
        if (getFactorBO == null) {

            getFactorBO = new FactorBO();
        }
        return getFactorBO;
    }

    public SuperBO getBO(BOtypes bOtype) {

        switch (bOtype) {
            case course:
                return new CourseBOCustomImpl();
            case student:
                return new StudentBOCustomImpl();
            case regis:
                return new RegistationBOCustomImpl();
            default:
                return null;
        }

    }
}
