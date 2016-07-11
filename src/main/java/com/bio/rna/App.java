package com.bio.rna;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.omg.IOP.RMICustomMaxStreamFormat;

import com.bio.rna.database.DBHelper;
import com.bio.rna.database.TBHelper;
import com.bio.rna.preprocessing.FileTransformer;
import com.bio.rna.query.Annotation;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	String file_name = "D:\\\\Java\\\\WorkSpace\\\\Bio\\\\rna\\\\data\\\\hg38.basic.annotation.tb";
    	DBHelper dh = new DBHelper("rnamod_annotation");
    	TBHelper.createAnnTB(dh, "hg38");
    	List<String> list = new ArrayList<String>();

		list.add("chr");
		list.add("strand");
		list.add("start");
		list.add("end");
		list.add("shortType");
		list.add("description");
		list.add("type");
		list.add("gene");
		list.add("gene_des");
		list.add("gene_rank");
		list.add("gene_total");
    	TBHelper.FileIntoTB(dh, file_name, "hg38", list);
/*    	ResultSet rs = dh.executeQuery("test", "SELECT * FROM testann", null);

    	while(rs.next()){
    		String s = rs.getString("id");
    		System.out.println(s);
    	}
    */
    	new Annotation(dh,"hg38").show();
    }
}
