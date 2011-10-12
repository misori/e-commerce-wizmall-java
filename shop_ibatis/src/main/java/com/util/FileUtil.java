package com.util;

import java.io.File;

public class FileUtil {
	public static void ReadDir(String Path){
		System.out.println("redadir");
		try
		{
			File f = new File(Path);
			String[] entries = f.list();

		for (int i = 0; i < entries.length; i++)
			System.out.println("There is a file " + entries[i] +" in this diretory");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		//return entries;
	}
}
