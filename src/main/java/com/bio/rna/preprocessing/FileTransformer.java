package com.bio.rna.preprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class FileTransformer {
	public static void HomerToTB(String in, String out){
		File inFile = null;
		File outFile = null;
		String tempString = null;
		String[] splitedS = null;
		String[] subsplitedS = null;
		
		String type,gene,shortType,strand,chr,description,gene_des = null;
		int start,end,gene_rank,gene_total = 0;
		String tab = "\t";
		
		try{
	    	inFile = new File(in);
	    	outFile = new File(out);
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
			while((tempString = reader.readLine()) != null){
				splitedS = tempString.split("\t");
				chr = splitedS[1];
				start = Integer.parseInt(splitedS[2]);
				end = Integer.parseInt(splitedS[3]);
				strand = splitedS[4];
				shortType = splitedS[5];
				description = splitedS[0];
				
//				type,gene.rank.total;
				subsplitedS = splitedS[0].split("\\(|\\)|,");
				if(subsplitedS.length == 1){
					subsplitedS = splitedS[0].split("--");
					type = subsplitedS[0];
					gene_rank = 0;
					gene = null;
					gene_total = 0;
					gene_des = null;
				}
				else{
					type = subsplitedS[0].trim();
					gene = subsplitedS[1].trim();
					
					if(tempString.contains(",")){
//						System.out.println(subsplitedS[2]);
						subsplitedS = subsplitedS[2].trim().split(" ");
						gene_des = subsplitedS[0];
						gene_rank = Integer.parseInt(subsplitedS[1]);
						gene_total = Integer.parseInt(subsplitedS[3]);
					}
					else{
						gene_des = null;
						gene_rank = 0;
						gene_total = 0;
					}
				}
				writer.write(chr + tab + strand + tab + start + tab + end + tab + shortType + tab + description + tab + type + tab
						+ gene + tab + gene_des + tab + gene_rank + tab + gene_total + "\n");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
