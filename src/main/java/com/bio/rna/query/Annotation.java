package com.bio.rna.query;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bio.rna.database.DBHelper;

public class Annotation {
	private List<Integer> sList = new ArrayList<Integer>();
	public Annotation(){}
	public Annotation(DBHelper dh, String tb_name) throws Exception{
    	ResultSet rs = dh.executeQuery("test", "SELECT * FROM hg38", null);
    	while(rs.next()){
    		sList.add(Integer.parseInt(rs.getString("start")));
    	}
	}
	public void show(){
		for(int i: sList){
			System.out.println(i);
		}
	}
	public static int anno(int location) throws Exception{
		int i=0;
		DBHelper dh = new DBHelper("rnamod_annotation");
    	ResultSet rs = dh.executeQuery("test", "SELECT * FROM testann", null);
    	while(rs.next()){
    		String s = rs.getString("id");
    		System.out.println(s);
    	}
		return i;
	}
}
