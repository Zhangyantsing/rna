package com.bio.rna.query;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bio.rna.database.DBHelper;

public class Annotation {
	private Map<String, List<Integer>> chrList = new HashMap<String, List<Integer>>();

		
//binary search		
	public int search(List<Integer> number, int des) {
	    	if(des < 1) return -1;
	        int low = 0;
	        int upper = number.size() - 1;
	        while (low <= upper) {
	            int mid = (low + upper) / 2;  
	            if(number.get(mid) > des){
	            	upper = mid - 1;
	            }
	            else if(number.get(mid) < des){
	            	low = mid + 1;
	            }
	            else {
					return mid;
				}
	        }  
	        return upper;
	    }

//annotation function	    
	public int anno(String chr, int location){
		return chrList.get(chr).get(0) + search(chrList.get(chr), location) - 1;
	}

	public Annotation(){}
	public Annotation(DBHelper dh, String tb_name) throws Exception{
    	ResultSet rs = dh.executeQuery("anno", "SELECT id,chr,start FROM hg38", null);
    	String chr = null;
    	int start = 0;
    	
    	while(rs.next()){
    		chr = rs.getString("chr");
    		start = rs.getInt("start");
    		if(!chrList.containsKey(chr)){
    			chrList.put(rs.getString("chr"), new ArrayList<>());
    			chrList.get(chr).add(rs.getInt("id"));
    		}
    		chrList.get(chr).add(start);
    	}
    	dh.close("anno");
	}

}
