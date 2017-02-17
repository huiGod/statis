package cn._51app.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ParamEntity {

	private int draw;
	
	private int length;
	
	private int start;

	private String startTime;
	
	private String endTime;
	
	private String channel;
	
	private String appId;
	
	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public static void main(String[] args) {
		String a="2017-01-01T16:00:00.000Z";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		try {
			System.out.println(df2.format(df.parse(a)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
