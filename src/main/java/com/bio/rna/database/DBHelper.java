package com.bio.rna.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Mysql DBHelper
 * 
 * @author zhangyq
 *
 */
public class DBHelper {
	private static String driver_class = "com.mysql.jdbc.Driver";
	private static String user = "zhangyq";
	private static String password = "Pi31415938";
	
	private String db_name = null;
	private String driver_url = "jdbc:mysql://101.6.97.186:3306/" + db_name + "?useSSL=false";
	
	private static Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rst = null;
	
	/*
	 * Connection
	 */
	public DBHelper(){
		try{
			conn = DBHelper.getInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
