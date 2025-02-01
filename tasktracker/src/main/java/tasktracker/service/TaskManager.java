package tasktracker.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import tasktracker.model.Task;
import tasktracker.model.TaskStatus;

public class TaskManager {
	static List<Task> taskList;
	
	public List<Task> getTaskByStatus(String taskStatus){
		return getAllTasks().stream()
				.filter(task -> task.getStatus().equalsIgnoreCase(taskStatus))
				.collect(Collectors.toList());
		
	}
	
	public List<Task> getAllTasks(){
		if(taskList == null) {
			taskList = ReadTaskHandler.getTaskList();
		}
		return taskList;
	}
	
	public Integer createTask(String taskName){
		List<Task> taskList =  getAllTasks();
		Integer taskId = taskList.size()+1;
		Task newTask = new Task();
		newTask.setId(taskId);
		newTask.setDescription(taskName);
		newTask.setCreatedAt(Date.from(Instant.now()));
		newTask.setStatus(TaskStatus.TODO.toString());
		taskList.add(newTask);
		CreateTaskHandler.saveTasks(taskList);
		return taskId;
	}
	
	public Task getTask(Integer taskId){
		Task task = new Task();
		return task;
	}
	
	public Integer updateTaskName(Integer taskId, String taskName) {
		return 0;
	}
	
	public Integer updateTaskStatus(Integer taskId, String status) {
		return 0;
	}
	
	public Integer deleteTask(Integer taskId) {
		return 0;
	}
	
	public boolean storeTask(List<Task> taskList) {
		return false;
	}
}
