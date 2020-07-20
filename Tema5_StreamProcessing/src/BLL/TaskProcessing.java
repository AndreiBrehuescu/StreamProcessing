package BLL;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TaskProcessing {

	private static final String inputFile = "Activities.txt";
	private static final Path inputPath = Paths.get(inputFile);
	private static final DateTimeFormatter dateTimeForm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public List<MonitoredData> generateActivitiesList(){
		
		try {
			return Files.lines(inputPath)
					.map( line -> line.split("\\t\\t"))
					.map( obj ->{
						MonitoredData newMonitoredData = new MonitoredData(LocalDateTime.parse(obj[0], dateTimeForm), LocalDateTime.parse(obj[1], dateTimeForm), obj[2].split("\\t")[0]);
						return newMonitoredData;
					})
					.collect(Collectors.toList());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int countDistinctDays(List<MonitoredData> monitoredData) {
		return monitoredData.stream()
				.map(MonitoredData::getDateOfActivity)
				.distinct()
				.collect(Collectors.toList())
				.size();
	}
	
	public Map<String, Integer> numberOfEachActivity(List<MonitoredData> monitoredData){
		return monitoredData.stream()
				.collect(
						Collectors.groupingBy(
								MonitoredData::getActivity,
								Collectors.collectingAndThen(
										Collectors.mapping(MonitoredData::getActivity, Collectors.counting()), 
										Long::intValue
								)
						)
				);
	}
	
	public Map<Integer, Map<String, Integer>> getNumberOfEachActivityPerDay(List<MonitoredData> monitoredData){
		return monitoredData.stream()
                .collect(
                        Collectors.groupingBy(
                                MonitoredData::getDistinctDay,
                                Collectors.groupingBy(
                                        MonitoredData::getActivity,
                                        Collectors.collectingAndThen(
                                                Collectors.mapping(MonitoredData::getActivity, Collectors.counting()), 
                                                Long::intValue
                                        )
                                )
                        )
                );
	}
	
	public Map<String, Integer> getActivitiesDuration(List<MonitoredData> monitoredData){
		return monitoredData.stream()
				.collect(
						Collectors.groupingBy(
								MonitoredData::getActivity,
								Collectors.collectingAndThen(
										Collectors.mapping(MonitoredData::getDuration, Collectors.summingInt(Long::intValue)),
										Integer::intValue
								)
								
						)
				);
	}
	
	public List<String> filterActivities(List<MonitoredData> monitoredData){
		return monitoredData.stream()
				.collect(
						Collectors.groupingBy(
						MonitoredData::getActivity,
						Collectors.toSet()
						)
				)
				.entrySet()
				.stream()
				.map( e -> {
					int countingActivities = 0;   // numara activitatile cu durata < 5 minute
					for(MonitoredData activityData : e.getValue()) {
						if( activityData.getDuration() < 5) {
							countingActivities++;
						}
					}
					
					if ( countingActivities >= (float)e.getValue().size() * 90 / 100.0 ) {
						return e.getKey();
					}
					
					return null;
				})
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}
}
