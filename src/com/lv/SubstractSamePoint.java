package com.lv;

import java.awt.geom.Arc2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubstractSamePoint {

    public static void main(String[] args) throws Exception{
        File fileFolder = new File("C:\\E\\dataSet\\2018-05-27\\2009-03-09(最后数据)");
        File[] files = fileFolder.listFiles();
        for (File file : files) {
            String first_line;
            String second_line;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            second_line = bufferedReader.readLine();
            first_line = second_line;

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            String[] first_data = first_line.split(",");
            Date first_date = simpleDateFormat.parse(first_data[2]);

            File outFile = new File("C:\\E\\dataSet\\2018-05-27\\2009-03-09(最后数据去掉重复点)\\" + file.getName());
            FileWriter fileWriter = new FileWriter(outFile, true);

            while ((second_line = bufferedReader.readLine())!= null) {
                String[] second_data = second_line.split(",");
                Date second_date = simpleDateFormat.parse(second_data[2]);
                if (first_date.getTime() != second_date.getTime()) {
                    fileWriter.write(first_line + "\n");
                    first_line = second_line;
                }
            }
            fileWriter.close();
            System.out.println("..........");
        }
    }
}
