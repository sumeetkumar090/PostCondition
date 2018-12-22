package com.examplepostconditions;

public class ProcessLogDto {

	String timeStamp;
	String eventID;
	String caseID;
	String taskID;
	String postConditions;

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
	 * @return the eventID
	 */
	public String getEventID() {
		return eventID;
	}

	/**
	 * @param eventID
	 *            the eventID to set
	 */
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	/**
	 * @return the caseID
	 */
	public String getCaseID() {
		return caseID;
	}

	/**
	 * @param caseID
	 *            the caseID to set
	 */
	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}

	/**
	 * @return the taskID
	 */
	public String getTaskID() {
		return taskID;
	}

	/**
	 * @param taskID
	 *            the taskID to set
	 */
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	/**
	 * @return the postConditions
	 */
	public String getPostConditions() {
		return postConditions;
	}

	/**
	 * @param postConditions
	 *            the postConditions to set
	 */
	public void setPostConditions(String postConditions) {
		this.postConditions = postConditions;
	}

	@Override
	public String toString() {
		return "ProcessLogDto [timeStamp=" + timeStamp + ", eventID=" + eventID + ", caseID=" + caseID + ", taskID="
				+ taskID + ", postConditions=" + postConditions + ", getTimeStamp()=" + getTimeStamp()
				+ ", getEventID()=" + getEventID() + ", getCaseID()=" + getCaseID() + ", getTaskID()=" + getTaskID()
				+ ", getPostConditions()=" + getPostConditions() + "]";
	}

}
