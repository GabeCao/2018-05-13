package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SubstractSamePoint {

    public static void main(String[] args) throws Exception{
        File fileFolder = new File("C:\\E\\dataSet\\2018-05-27\\218-05-27(补充Trajectory 的轨迹点)");
        File[] files = fileFolder.listFiles();
        for (File file : files) {

            String line;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            File outFile = new File("C:\\E\\dataSet\\2018-05-27\\2018-05-27(去掉相同的时间点)\\" + file.getName());
            FileWriter fileWriter = new FileWriter(outFile, true);
            int count = 0;

            String point_string = "08:00:00";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            Date point = simpleDateFormat.parse(point_string);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(point);
            while ((line = bufferedReader.readLine())!= null) {
                Date point_ = calendar.getTime();

                String[] second_data = line.split(",");
                Date second_date = simpleDateFormat.parse(second_data[2]);
                if (point_.getTime() == second_date.getTime()) {
                    count++;
                    calendar.add(Calendar.SECOND, 1);
                    fileWriter.write(line + "\n");
                }


            }
            System.out.println(file.getName() + "         " +count + "           " );
            fileWriter.close();
        }
    }
}
