package com.examplepostconditions;

public class EventLogDto {

	String timeStamp;
	String eventId;

	/**
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp
	 *            the timeStamp to set
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@Override
	public String toString() {
		return "EventLogDto [timeStamp=" + timeStamp + ", eventId=" + eventId + ", getTimeStamp()=" + getTimeStamp()
				+ ", getEventId()=" + getEventId() + "]";
	}

}
