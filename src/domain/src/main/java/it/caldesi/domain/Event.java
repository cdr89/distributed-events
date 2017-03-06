package it.caldesi.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

	@SerializedName("attributes")
	@Expose
	private Attributes attributes;

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

}
