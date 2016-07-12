package com.bio.rna;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.directory.SearchControls;
import javax.print.attribute.standard.PrinterLocation;

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
    	DBHelper dh = new DBHelper("rnamod_annotation");
    	Annotation anno = new Annotation(dh, "hg38");
    	System.out.println(anno.anno("chr20", 10000000));
    }
}
