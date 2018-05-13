package com.lv;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

//减去 source-data 文件中 找到 属于 2009-03-09 的数据，放在 2009-03-09 文件夹中
public class FindData {

    public static void main(String[] args) throws Exception{
        String inFileFolderPath = "C:\\E\\dataSet\\2018-05-13\\source-data";
        File inFileFolder = new File(inFileFolderPath);
        File[] inFiles = inFileFolder.listFiles();

        for (File inFile : inFiles) {

            FileReader fileReader = new FileReader(inFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            File outFile = new File("C:\\E\\dataSet\\2018-05-13\\2009-03-09\\" + inFile.getName());
            FileWriter fileWriter = new FileWriter(outFile,true);
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                String date_string = data[4];
                if (date_string.equals("2009-03-09")) {
                    fileWriter.write(data[0] + "," + data[1] + "," +data[4] + ","+ data[5] + "," + data[6] + "," + data[7] +"\n");
                }
            }

            fileWriter.close();
        }
    }
}
