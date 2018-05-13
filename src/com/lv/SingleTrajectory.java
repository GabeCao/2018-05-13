package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SingleTrajectory {

    public static void main(String[] args) throws Exception{
        File file = new File("C:\\E\\dataSet\\2018-05-13\\2009-03-09(补充轨迹点)\\179.txt");

        ArrayList<Trajectory> trajectories = new ArrayList<>();

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        line = bufferedReader.readLine();
        String[] data_first = line.split(",");
        double x_first = Double.parseDouble(data_first[4]);
        double y_first = Double.parseDouble(data_first[5]);
        SimpleDateFormat dateFormat_first = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_first = dateFormat_first.parse(data_first[2] + " " + data_first[3]);
        Point point_first = new Point(x_first,y_first,date_first);
        Trajectory trajectory_first = new Trajectory();
        trajectories.add(trajectory_first);
        trajectory_first.getPoints().add(point_first);

        Trajectory preTrajectory = trajectory_first;
        Point prePoint = point_first;
        //获得所有的Trajectory
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");

            double x = Double.parseDouble(data[4]);
            double y = Double.parseDouble(data[5]);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = dateFormat.parse(data[2] + " " + data[3]);
            Point point = new Point(x,y,date);

            long timeDiff = point.getDate().getTime() - prePoint.getDate().getTime();

            if (timeDiff < 300000) {
                preTrajectory.getPoints().add(point);

                prePoint = point;
            } else {
                Trajectory newTrajectory = new Trajectory();
                trajectories.add(newTrajectory);
                newTrajectory.getPoints().add(point);

                prePoint = point;
                preTrajectory = newTrajectory;
            }
        }
        //一个 Trajectory 内部
        ArrayList<Trajectory> trajectories1 = new ArrayList<>();
        for (Trajectory trajectory : trajectories) {
            ArrayList<Point> points = trajectory.getPoints();
            Point pre_point = points.get(0);

            Trajectory trajectory1 = new Trajectory();
            trajectories1.add(trajectory1);
            for (Point point : points) {
                while (point.getDate().getTime() - pre_point.getDate().getTime() > 1000) {
                    Point newPoint = new Point(pre_point.getX(), pre_point.getY(), new Date(pre_point.getDate().getTime() + 1000));
                    pre_point = newPoint;
                    trajectory1.getPoints().add(newPoint);
                }

                pre_point = point;
                trajectory1.getPoints().add(point);
            }
        }
        //两个Trajectory 之间
        Trajectory first_trajectory = trajectories1.get(0);
        Point pre_last_point = first_trajectory.getPoints().get(first_trajectory.getPoints().size() - 1);
        for (Trajectory trajectory : trajectories1) {
            Point last_point = trajectory.getPoints().get(trajectory.getPoints().size() - 1);
            while (last_point.getDate().getTime() - pre_last_point.getDate().getTime() > 1000) {
                Point newPoint = new Point(pre_last_point.getX(), pre_last_point.getY(), new Date(pre_last_point.getDate().getTime() + 1000));
                trajectory.getPoints().add(newPoint);
                pre_last_point = newPoint;
            }

            pre_last_point = last_point;
        }

        File outFile = new File("C:\\E\\dataSet\\2018-05-13\\2009-03-09(最后数据)\\000.txt");

        FileWriter fileWriter = new FileWriter(outFile,true);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        for (Trajectory trajectory : trajectories1) {
            ArrayList<Point> points = trajectory.getPoints();
            for (Point point : points) {
                String outString = point.getX() + "," + point.getY() + ","
                        + dateFormat.format(point.getDate()) + "," + timeFormat.format(point.getDate()) + "\n";
                fileWriter.write(outString);
            }
        }
        fileWriter.close();

        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        int i = 1;
        for (Trajectory trajectory : trajectories1) {
            File outFile = new File("C:\\E\\dataSet\\2018-05-13\\Trajectory\\179\\"+ i +".txt");
            i++;
            FileWriter fileWriter = new FileWriter(outFile,true);

            ArrayList<Point> points = trajectory.getPoints();
            for (Point point : points) {
                String outString = point.getX() + "," + point.getY() + ","
                        + dateFormat.format(point.getDate()) + "," + timeFormat.format(point.getDate()) + "\n";
                fileWriter.write(outString);
            }
            fileWriter.close();
        }*/

    }
}