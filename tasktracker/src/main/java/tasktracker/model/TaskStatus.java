package tasktracker.model;

public enum TaskStatus {
	DONE("done"), TODO("todo"), INPROGRESS("in-progress");

	private String status;
	private TaskStatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		return this.status;
	}
}
