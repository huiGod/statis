package cn._51app.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ResultEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7558510586276879507L;

	private int draw;
	
	private int recordsTotal;
	
	private int recordsFiltered;
	
	private List<Map<String, Object>> data;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}
	
	
}
