package Run;
import java.util.List;

import BLL.MonitoredData;
import BLL.TaskProcessing;
import Presentation.FileWriterr;

public class MainRun {
	public static void main(String[] args) {

		TaskProcessing taskProcessing = new TaskProcessing();

		List<MonitoredData> monitoredDataList = taskProcessing.generateActivitiesList();
		FileWriterr.writeMonitoredData(monitoredDataList);
		FileWriterr.writeDistinctDaysNumbers(taskProcessing.countDistinctDays(monitoredDataList));
		FileWriterr.writeActivitiesWithAppearanceNumber(taskProcessing.numberOfEachActivity(monitoredDataList));
		FileWriterr.writeActivitiesInEachDatWithAppearence(taskProcessing.getNumberOfEachActivityPerDay(monitoredDataList));
		FileWriterr.writeActivityWithEachTotalDuration(taskProcessing.getActivitiesDuration(monitoredDataList));
		FileWriterr.writeFilteredActivities(taskProcessing.filterActivities(monitoredDataList));
		
	}
}
