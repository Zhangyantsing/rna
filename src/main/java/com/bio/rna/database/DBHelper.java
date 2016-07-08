package com.bio.rna.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mysql DBHelper
 * 
 * @author zhangyq
 *
 */
public class DBHelper {
	private String user = "zhangyq";
	private String password = "Pi31415938";
	private String db_name = "rna";
	private String url = "jdbc:mysql://101.6.97.186:3306/" + db_name + "?useSSL=false";
	
	public Connection conn = null;
	public Map<String,PreparedStatement> psts = new HashMap<String, PreparedStatement>();
	public Map<String,ResultSet> rsts = new HashMap<String, ResultSet>();
	
	/****************************
	 * Connection
	 */
	public DBHelper(){
		try{
			conn = getConnInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public DBHelper(String db){
		db_name = db;
		url = "jdbc:mysql://101.6.97.186:3306/" + db_name + "?useSSL=false";
		try{
			conn = getConnInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public DBHelper(String u, String p, String db){
		user = u;
		password = p;
		db_name = db;
		try{
			conn = getConnInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	protected void finalize(){
		this.close();
    }

    private Connection getConnInstance() {
        if(conn == null){
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("DataBase Connected!");
        }
        return conn;
    }


    /*****************************
     * close
     */
    public void close() {
        try {
            if (rsts != null){
            	for(Map.Entry<String, ResultSet> rst: rsts.entrySet()){
            		rst.getValue().close();
            		rsts.remove(rst.getKey());
            	}
            }
            if (psts != null){
            	for(Map.Entry<String, PreparedStatement> pst: psts.entrySet()){
            		pst.getValue().close();
            		psts.remove(pst.getKey());
            	}
            }
            if (conn != null){
                conn.close();
                conn = null;
            }
            System.out.println("Connection Closed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPST(String label, String sql){
    	try{
    		psts.put(label, conn.prepareStatement(sql));
    	}catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String label, String sql, List<String> sqlValues) {
        try {
            createPST(label,sql);
            if (sqlValues != null && sqlValues.size() > 0) {
                setSqlValues(psts.get(label), sqlValues);
            }
            rsts.put(label,psts.get(label).executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsts.get(label);
    }

    /**
     * update
     */
    public int executeUpdate(String label, String sql, List<String> sqlValues) {
        int result = -1;
        try {
        	createPST(label,sql);
            if (sqlValues != null && sqlValues.size() > 0) {
            	setSqlValues(psts.get(label), sqlValues);
            }
            result = psts.get(label).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean execute(String label, String sql, List<String> sqlValues) {
    	boolean result = false;
        try {
        	createPST(label,sql);
            if (sqlValues != null && sqlValues.size() > 0) {
            	setSqlValues(psts.get(label), sqlValues);
            }
            result = psts.get(label).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * sql set value
     * 
     * @param pst
     * @param sqlValues
     */
    private void setSqlValues(PreparedStatement pst, List<String> sqlValues) {
        for (int i = 0; i < sqlValues.size(); i++) {
            try {
                pst.setObject(i + 1, sqlValues.get(i));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
