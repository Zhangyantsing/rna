package com.bio.rna.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TBHelper {
	public static void createAnnTB(){
		
	}
	public static void createModTB(){
		
	}
	public static void createSeqTB(){
		
	}
	public static void createFunTB(){
		
	}
	public static void createModTb (String tb_name) {
		try{ 
			DBHelper dh = new DBHelper("rnamod_modification");
			String sql = "CREATE TABLE `" + tb_name + "` (\n"
					+ "`id`		INT (11) NOT NULL AUTO_INCREMENT,\n"
					+ "`chr`	VARCHAR(10) DEFAULT NULL,\n"
					+ "`start`	INT(11) DEFAULT NULL,\n"
					+ "`end`	INT(11) DEFAULT NULL,\n"
					+ "`strand`	VARCHAR(1) DEFAULT NULL,\n"
					+ "`score`	FLOAT DEFAULT NULL,\n"
					+ "PRIMARY KEY (`id`)\n"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8\n";

			dh.executeUpdate(tb_name, sql, null);
			dh.close();
		} 
		catch(Exception e) {
         e.printStackTrace();
		} 
		System.out.println("Table \"" + tb_name + "\" created!");
	}
}
