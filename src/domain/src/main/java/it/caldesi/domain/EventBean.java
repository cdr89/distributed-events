package it.caldesi.domain;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventBean {

	@SerializedName("events")
	@Expose
	private List<Event> events = null;

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public static EventBean buildFromJsonString(String jsonString) {
		Gson gson = new Gson();

		return gson.fromJson(jsonString, EventBean.class);
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
