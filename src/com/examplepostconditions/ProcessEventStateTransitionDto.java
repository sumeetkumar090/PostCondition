package com.examplepostconditions;

public class ProcessEventStateTransitionDto {

	String taskId;
	String priorEffect;
	String postEffect;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getPriorEffect() {
		return priorEffect;
	}

	public void setPriorEffect(String priorEffect) {
		this.priorEffect = priorEffect;
	}

	public String getPostEffect() {
		return postEffect;
	}

	public void setPostEffect(String postEffect) {
		this.postEffect = postEffect;
	}

	@Override
	public String toString() {
		return "ProcessEventStateTransitionDto [taskId=" + taskId + ", priorEffect=" + priorEffect + ", postEffect="
				+ postEffect + "]";
	}
}
