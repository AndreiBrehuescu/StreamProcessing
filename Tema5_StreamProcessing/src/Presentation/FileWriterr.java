package Presentation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import BLL.MonitoredData;

public class FileWriterr {
	private static String fileName = "Task_";
	
	public static void writeMonitoredData(List<MonitoredData> data) {
		File outputFile = new File(fileName + "1.txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			writer.write("Monitored Activities: ");
			writer.newLine();
			writer.newLine();
			
			for (MonitoredData monitoredData : data) {
				writer.write(monitoredData.toString());
				writer.newLine();
			}
			
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeDistinctDaysNumbers(int daysNumber) {
		File outputFile = new File(fileName + "2.txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			writer.write("The number of distinct days is: " + daysNumber);
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeActivitiesWithAppearanceNumber(Map<String, Integer> data) {
		File outputFile = new File(fileName + "3.txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			writer.write("Monitored Activities with their appearance number: ");
			writer.newLine();
			writer.newLine();
			
			for (String monitoredData : data.keySet()) {
				writer.write(monitoredData + " : " + data.get(monitoredData) );
				writer.newLine();
			}
			
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeActivitiesInEachDatWithAppearence(Map<Integer, Map<String, Integer>> data) {
		File outputFile = new File(fileName + "4.txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			writer.write("How many times each activity has appeared for each day over the monitoring period. ");
			writer.newLine();
			writer.newLine();
			
			for (Integer day : data.keySet()) {
				writer.write("Day" + " : " + day);
				writer.newLine();
				for (String activity : data.get(day).keySet()) {
					writer.write(activity + " : " + data.get(day).get(activity) + " times ");
					writer.newLine();
				}
				writer.newLine();
				writer.newLine();
			}
			
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeActivityWithEachTotalDuration(Map<String, Integer> data) {
		File outputFile = new File(fileName + "5.txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			writer.write("For each activity compute the entire duration over the monitoring period ");
			writer.newLine();
			writer.newLine();
			
			for (String activity : data.keySet()) {
				writer.write(activity + " : " + data.get(activity) + " minutes");
				writer.newLine();
			}
			
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeFilteredActivities(List<String> data) {
		File outputFile = new File(fileName + "6.txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			writer.write("Filtered Activities that have more than 90% of the monitoring records with duration less than 5 minutes");
			writer.newLine();
			writer.newLine();
			
			for (String activity : data) {
				writer.write(activity);
				writer.newLine();
			}
			
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
