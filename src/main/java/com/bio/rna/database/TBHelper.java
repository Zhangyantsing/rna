package com.bio.rna.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TBHelper {
	public static void createTB(DBHelper dh, String tb_name, List<String> cols){
		StringBuilder sql = new StringBuilder(100);
		sql.append("CREATE TABLE `").append(tb_name).append("` (\n");

		for(String s: cols){
			sql.append(s);
			sql.append("\n");
		}
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n");
//		System.out.println(sql);
		try{
			dh.executeUpdate(tb_name, sql.toString(), null);
			dh.close(tb_name);
//			System.out.println("Table \"" + tb_name + "\" created!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void createAnnTB(DBHelper dh, String tb_name) {
		try{
			List<String> sql_values = new ArrayList<String>();
			sql_values.add("`id`			INT (11) NOT NULL AUTO_INCREMENT,");
			sql_values.add("`chr`			VARCHAR(25) DEFAULT NULL,");
			sql_values.add("`strand`		VARCHAR(1) DEFAULT NULL,");
			sql_values.add("`start`			INT(11) DEFAULT NULL,");
			sql_values.add("`end`			INT(11) DEFAULT NULL,");
			sql_values.add("`shortType`		VARCHAR(10) DEFAULT NULL,");
			sql_values.add("`description`	VARCHAR(100) DEFAULT NULL,");
			sql_values.add("`type`			VARCHAR(20) DEFAULT NULL,");
			sql_values.add("`gene`			VARCHAR(20) DEFAULT NULL,");
			sql_values.add("`gene_des`			VARCHAR(20) DEFAULT NULL,");
			sql_values.add("`gene_rank`		INT(10) DEFAULT NULL,");
			sql_values.add("`gene_total`	INT(10) DEFAULT NULL,");
			sql_values.add("PRIMARY KEY (`id`)");

			createTB(dh, tb_name, sql_values);
		} 
		catch(Exception e) {
			e.printStackTrace();
		} 

	}
	public static void createModTB(){
		
	}
	public static void createSeqTB(){
		
	}
	public static void createFunTB(){
		
	}
	public static void createModTb (DBHelper dh, String tb_name) {
		try{
			List<String> sql_values = new ArrayList<String>();
			sql_values.add("`id`			INT (11) NOT NULL AUTO_INCREMENT,");
			sql_values.add("`chr`			VARCHAR(10) DEFAULT NULL,");
			sql_values.add("`strand`		VARCHAR(1) DEFAULT NULL,");
			sql_values.add("`start`			INT(11) DEFAULT NULL,");
			sql_values.add("`end`			INT(11) DEFAULT NULL,");
			sql_values.add("`score`			FLOAT DEFAULT NULL,");
			sql_values.add("`annotation`	VARCHAR(255) DEFAULT NULL,");
			sql_values.add("PRIMARY KEY (`id`)");

			createTB(dh, tb_name, sql_values);
		} 
		catch(Exception e) {
			e.printStackTrace();
		} 

	}

	public static void FileIntoTB(DBHelper dh, String file_name, String tb_name, List<String> cols){
		StringBuilder sql = new StringBuilder(100); 
		sql.append("LOAD DATA INFILE '")
			.append(file_name)
			.append("' INTO TABLE ")
			.append(tb_name);
		if(cols != null){
			sql.append("(");
			for(String s: cols){
				sql.append(s);
				sql.append(",");
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(");");
		}
		dh.executeUpdate(tb_name, sql.toString(), null);
		dh.close(tb_name);
	}
	
	public static ResultSet select(DBHelper dh, String tb_name, String label, List<String> cols, String conditions, List<String> vars, int start, int length){
		StringBuilder sb = new StringBuilder(100);
//		SELECT * FROM rnamod_annotation.testann;
		sb.append("SELECT ");
		if(cols != null){
			for(String s: cols){
				sb.append(s);
				sb.append(",");
			}
			sb.deleteCharAt(sb.length()-1);
		}
		else
			sb.append("*");
		
		sb.append(" FROM ").append(tb_name);
		if(conditions != null) sb.append(" WHERE ").append(conditions);
		if(length != 0) sb.append(" LIMIT ").append(start).append(",").append(length).append(";");
		return dh.executeQuery(label, sb.toString(), vars);
	}
}
