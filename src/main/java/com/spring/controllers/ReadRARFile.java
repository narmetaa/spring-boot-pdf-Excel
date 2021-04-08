package com.spring.controllers;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ReadRARFile {

	private static void read(String zipFilePath) {
        try {
            ZipFile zipFile = new ZipFile(zipFilePath);
 
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
 
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String name = entry.getName();
                long compressedSize = entry.getCompressedSize();
                long normalSize = entry.getSize();
                String type = entry.isDirectory() ? "DIR" : "FILE";
 
                System.out.println(name);
                System.out.format("\t %s - %d - %d\n", type, compressedSize, normalSize);
            }
 
            zipFile.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
 
	
	
 
    public static void main(String[] args) {
        String zipFilePath = "F:\\\\test.ZIP";
        read(zipFilePath);
    }
}
