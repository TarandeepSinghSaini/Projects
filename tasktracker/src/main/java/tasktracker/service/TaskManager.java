package tasktracker.service;

import java.util.ArrayList;
import java.util.List;

import tasktracker.model.Task;

public class TaskManager {
	
	public List<Task> getAllTasks(){
		return new ArrayList<>();
	}
	
	public Integer createTask(Task task){
		Integer taskId=0;
		return taskId;
	}
	
	public Task getTask(Integer taskId){
		Task task = new Task();
		return task;
	}
	
	public Integer updateTask(Task task) {
		return 0;
	}
	
	public Integer deleteTask(Task task) {
		return 0;
	}
}
