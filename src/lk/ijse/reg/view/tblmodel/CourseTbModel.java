/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.view.tblmodel;

/**
 *
 * @author Sahan Rajakaruna
 */
public class CourseTbModel {

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    
    private String cid;
    private String name;
    private String duration;

    public CourseTbModel() {
    }

    public CourseTbModel(String cid, String name, String duration) {
        this.cid = cid;
        this.name = name;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "CourseTbModel{" + "cid=" + cid + ", name=" + name + ", duration=" + duration + '}';
    }
    
}
