package it.caldesi.consumer.util;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import it.caldesi.domain.EventBean;

public class Utils {

	public static DBObject buildFromEventBean(EventBean eventBean) {
		String jsonString = eventBean.toString();
		return (DBObject) JSON.parse(jsonString);
	}

}
