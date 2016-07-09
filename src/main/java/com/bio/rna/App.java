package com.bio.rna;

import com.bio.rna.database.TBHelper;
import com.bio.rna.preprocessing.FileTransformer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	TBHelper.createAnnTB("testann");
    }
}
