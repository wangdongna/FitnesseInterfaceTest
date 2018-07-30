package gat.common.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ReadTestCases {
	
	public static List<String> traverseFolder(String path)
	{
		int fileNum = 0;
		int folderNum = 0;
        File file = new File(path);
        List<String> fileNames = new ArrayList<String>();
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    //System.out.println("�ļ���:" + file2.getAbsolutePath());                         
                    list.add(file2);
                    folderNum++;
                    
                } else {
                    //System.out.println("�ļ�:" + file2.getAbsolutePath());
                    //System.out.println("�ļ�:" + file2.getName());
                    fileNames.add(file2.getName());
                    fileNum++;
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        //System.out.println("�ļ���:" + file2.getAbsolutePath());
                        list.add(file2);
                        folderNum++;                  
                    } else {
                        //System.out.println("�ļ�:" + file2.getAbsolutePath());
                        //System.out.println("�ļ�:" + file2.getName()); 
                        fileNames.add(file2.getName());
                        fileNum++;
                    }
                }
            }
        } else {
            //System.out.println("�ļ�������!");
        }
        //System.out.println("�ļ��й���:" + folderNum + ",�ļ�����:" + fileNum);
        
        	
        return fileNames;
	}
}
