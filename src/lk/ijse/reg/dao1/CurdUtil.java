/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.dao1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import lk.ijse.reg.db.DbConnection;

/**
 *
 * @author Sahan Rajakaruna
 */
public class CurdUtil {

    private static PreparedStatement getPreparedStatement(String query, Object... args) throws Exception {

        PreparedStatement pre = DbConnection.getInstance().getConnection().prepareStatement(query);
        for (int i = 0; i < args.length; i++) {
            pre.setObject(i + 1, args[i]);

        }
        return pre;
    }

    public static boolean excuteUpdate(String query, Object... args) throws Exception {

        return getPreparedStatement(query, args).executeUpdate() > 0;

    }

    public static ResultSet exeResultSet(String query, Object... args) throws Exception {
        return getPreparedStatement(query, args).executeQuery();
    }

}
