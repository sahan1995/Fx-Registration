/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.dao.custom.impl1;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import lk.ijse.reg.dao.custom1.RegDAO;
import lk.ijse.reg.dao1.CurdUtil;
import lk.ijse.reg.db.DbConnection;
import lk.ijse.reg.entity.Reg;
import lk.ijse.reg.entity.Reg_PK;

/**
 *
 * @author Sahan Rajakaruna
 */
public class RegistrationDAOImpl implements RegDAO {

    @Override
    public ArrayList<Reg> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(Reg reg) throws Exception {

        return CurdUtil.excuteUpdate("INSERT INTO reg VALUES (?,?,?)", reg.getReg_PK().getSid(), reg.getReg_PK().getCid(), reg.getFee());

    }

    @Override
    public boolean delete(Reg_PK id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reg findById(Reg_PK id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Reg entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
