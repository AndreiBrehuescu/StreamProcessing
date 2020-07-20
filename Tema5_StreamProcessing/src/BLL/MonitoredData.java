package BLL;
import java.time.Duration;
import java.time.LocalDateTime;

public class MonitoredData {
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String activity;
	
	public MonitoredData(LocalDateTime startTime, LocalDateTime endTime, String activity) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.activity = activity;
	}

	public String getStartTime() {
		return startTime.getDayOfMonth() + "/" + startTime.getMonthValue() + "/" + startTime.getYear() + "-" + startTime.getHour() + ":" + startTime.getMinute() + ":" + startTime.getSecond();
	}
	
	public long getDuration() {
		return Duration.between(startTime, endTime).toMinutes();
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime.getDayOfMonth() + "/" + endTime.getMonthValue() + "/" + endTime.getYear() + "-" + endTime.getHour() + ":" + endTime.getMinute() + ":" + endTime.getSecond();
	}
	
	@Override
	public String toString() {
		return "Activity: " + this.activity + "\nStart time: " + this.getStartTime() + "\nEnd time: " + this.getEndTime() + "\nDuration: " + this.getDuration() + "(minutes)\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activity == null) ? 0 : activity.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
	
		if ( !(obj instanceof MonitoredData)) {
			return false;
		}
		
		MonitoredData monitoreData = (MonitoredData) obj;
		if( this.startTime != monitoreData.startTime || this.endTime != monitoreData.endTime || this.activity != monitoreData.activity)
			return false;
		
		return true;
	}
	
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public String getDateOfActivity() {
		return startTime.toLocalDate().toString();
	}
	
	public int getDistinctDay() {
		return startTime.getMonthValue()*31 + startTime.getDayOfMonth();
	}
	
}
