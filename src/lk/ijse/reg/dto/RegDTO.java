/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.dto;

import java.math.BigDecimal;

/**
 *
 * @author Sahan Rajakaruna
 */
public class RegDTO {
    
    /**
     * @return the sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * @param sid the sid to set
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * @return the cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * @param cid the cid to set
     */
    public void setCid(String cid) {
        this.cid = cid;
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
    private String sid;
    private String cid;
    private BigDecimal fee;

    public RegDTO() {
    }

    public RegDTO(String sid, String cid, BigDecimal fee) {
        this.sid = sid;
        this.cid = cid;
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "RegDTO{" + "sid=" + sid + ", cid=" + cid + ", fee=" + fee + '}';
    }
    
}
