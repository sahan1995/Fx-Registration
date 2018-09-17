/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.entity;

import java.math.BigDecimal;

/**
 *
 * @author Sahan Rajakaruna
 */
public class Reg {

    /**
     * @return the reg_PK
     */
    public Reg_PK getReg_PK() {
        return reg_PK;
    }

    /**
     * @param reg_PK the reg_PK to set
     */
    public void setReg_PK(Reg_PK reg_PK) {
        this.reg_PK = reg_PK;
    }

    /**
     * @return the fee
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    private Reg_PK reg_PK;
    private BigDecimal fee;

    public Reg() {
    }

    public Reg(Reg_PK reg_PK, BigDecimal fee) {
        this.reg_PK = reg_PK;
        this.fee = fee;
    }

    public Reg(String sid, String cid, BigDecimal fee) {
        this.reg_PK = new Reg_PK(sid, cid);
        this.fee = fee;
    }

}
