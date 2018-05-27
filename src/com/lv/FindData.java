package com.lv;


import javafx.scene.input.DataFormat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.DataFormatException;

//减去 source-data 文件中 找到 属于 2009-03-09 的数据，放在 2009-03-09 文件夹中
public class FindData {

    public static void main(String[] args) throws Exception{
        String inFileFolderPath = "C:\\E\\dataSet\\2018-05-27\\2018-05-27加能量数据（2改）\\2018-05-27加能量数据（2改）\\2009-03-09补轨迹点";
        File inFileFolder = new File(inFileFolderPath);
        File[] inFiles = inFileFolder.listFiles();

        for (File inFile : inFiles) {

            FileReader fileReader = new FileReader(inFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            File outFile = new File("C:\\E\\dataSet\\2018-05-27\\2018-05-27(8点~22点)\\" + inFile.getName());
            FileWriter fileWriter = new FileWriter(outFile,true);

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                String now_string = data[5];
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                Date now = simpleDateFormat.parse(now_string);

                String start_string = "08:00:00";
                String end_string = "22:00:00";
                Date start = simpleDateFormat.parse(start_string);
                Date end = simpleDateFormat.parse(end_string);

                if (now.getTime() >= start.getTime() && now.getTime() <= end.getTime()) {
                    fileWriter.write(data[6] + "," + data[7] + "," +  data[5] +"\n");
                }
            }

            fileWriter.close();
            System.out.println(inFile.getName());
        }
    }
}
