package com.bio.rna;

import com.bio.rna.preprocessing.FileTransformer;

public class FileTrans {
	public static void main(String[] args){
		String in,out;
		in = "D:\\Java\\WorkSpace\\Bio\\rna\\data\\hg38.basic.annotation";
		out = "D:\\Java\\WorkSpace\\Bio\\rna\\data\\hg38.basic.annotation.tb";
		FileTransformer.HomerToTB(in, out);
	}
}
