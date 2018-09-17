/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.business.custom;

import lk.ijse.reg.business.SuperBO;
import lk.ijse.reg.dto.RegDTO;
import lk.ijse.reg.dto.StudentDTO;

/**
 *
 * @author Sahan Rajakaruna
 */
public interface RegistrationBO extends SuperBO {

    public boolean RegsiterStudent(StudentDTO student, RegDTO reg) throws Exception;

    public boolean RegisterCourse(RegDTO regDTO) throws Exception;
}
