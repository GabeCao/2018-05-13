package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//(补充7点到出现第⼀个坐标点之间的数据)
public class AddData {

    public static void main(String[] args) throws Exception{
        String inFileFolderPath = "C:\\E\\dataSet\\2018-05-13\\2009-03-09(7点-22点)";
        File inFileFolder = new File(inFileFolderPath);
        File[] inFiles = inFileFolder.listFiles();

        for (File inFile : inFiles) {

            FileReader fileReader = new FileReader(inFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            File outFile = new File("C:\\E\\dataSet\\2018-05-13\\2009-03-09(补充轨迹点)\\" + inFile.getName());
            FileWriter fileWriter = new FileWriter(outFile,true);

            line = bufferedReader.readLine();
            String[] data = line.split(",");

            String x = data[0];
            String y = data[1];

            String data_string = data[2] + " " +data[3];
            String start_string = "2009-03-09 07:00:00";
            String end_string = "2009-03-09 22:00:00";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");


            Date start = simpleDateFormat.parse(start_string);
            Date data_date = simpleDateFormat.parse(data_string);
            Date end = simpleDateFormat.parse(end_string);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(start);
            while (start.before(data_date)) {
                String outDate = dateFormat.format(start);
                String outTime = timeFormat.format(start);
                String outStirng = x + "," + y + "," +outDate + "," + outTime + "," +data[4] + "," +data[5] + "\n";
                fileWriter.write(outStirng);
                calendar.add(Calendar.SECOND,1);
                start = calendar.getTime();
            }

            //获取最后一行
            String end_line = null;
            fileWriter.write(line + "\n");
            while ((line = bufferedReader.readLine()) != null) {
                fileWriter.write(line + "\n");
                end_line = line;
            }

            Calendar calendar2 = Calendar.getInstance();
            String[] data2 = end_line.split(",");
            data_string = data2[2] + " " +data2[3];
            data_date = simpleDateFormat.parse(data_string);
            calendar2.setTime(data_date);
            while (data_date.before(end)) {
                calendar2.add(Calendar.SECOND,1);
                data_date = calendar2.getTime();
                String outDate = dateFormat.format(data_date);
                String outTime = timeFormat.format(data_date);
                String outString = data2[0] + "," +data2[1] + "," + outDate + "," + outTime + "," +data2[4] + "," +data2[5] + "\n";
                fileWriter.write(outString);

            }

            fileWriter.close();
        }
    }
}
