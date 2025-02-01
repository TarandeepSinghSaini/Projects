package tasktracker.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import tasktracker.model.Task;
import tasktracker.model.TaskStatus;

public class TaskManager {
	static List<Task> taskList;

	public List<Task> getTaskByStatus(String taskStatus) {
		return getAllTasks()
				.stream()
				.filter(task -> task.getStatus().equalsIgnoreCase(taskStatus))
				.collect(Collectors.toList());
	}

	public List<Task> getAllTasks() {
		if (taskList == null) {
			taskList = ReadTaskHandler.getTaskList();
		}
		return taskList;
	}

	public Integer createTask(String taskName) {
		Integer createdTaskId = null;
		List<Task> taskList = getAllTasks();
		Integer taskId = getNewTaskId(taskList);
		Task newTask = new Task();
		newTask.setId(taskId);
		newTask.setDescription(taskName);
		newTask.setCreatedAt(Date.from(Instant.now()));
		newTask.setStatus(TaskStatus.TODO.toString());
		taskList.add(newTask);
		if (CreateTaskHandler.saveTasks(taskList)) {
			createdTaskId = taskId;
			refreshTaskList();
		}
		return createdTaskId;
	}

	public Integer updateTaskName(Integer taskId, String taskName) {
		List<Task> taskList = getAllTasks();
		Integer updatedTaskId = null;
		for (Task task : taskList) {
			if (task.getId() == taskId) {
				task.setDescription(taskName);
				task.setUpdatedAt(Date.from(Instant.now()));
			}
		}
		if (CreateTaskHandler.saveTasks(taskList)) {
			updatedTaskId = taskId;
			refreshTaskList();
		}
		return updatedTaskId;
	}

	public Integer updateTaskStatus(Integer taskId, String status) {
		List<Task> taskList = getAllTasks();
		Integer updatedTaskId = null;
		for (Task task : taskList) {
			if (task.getId() == taskId) {
				task.setStatus(status);
				task.setUpdatedAt(Date.from(Instant.now()));
			}
		}
		if (CreateTaskHandler.saveTasks(taskList)) {
			updatedTaskId = taskId;
			refreshTaskList();
		}
		return updatedTaskId;
	}

	public Integer deleteTask(Integer taskId) {
		List<Task> taskList = getAllTasks();
		List<Task> newTaskList = new ArrayList<>();
		Integer deletedTaskId = null;
		for (Task task : taskList) {
			if (task.getId() == taskId) {
				continue;
			}
			newTaskList.add(task);
		}
		if (CreateTaskHandler.saveTasks(newTaskList)) {
			deletedTaskId = taskId;
			refreshTaskList();
		}
		return deletedTaskId;
	}

	public static void refreshTaskList() {
		taskList = ReadTaskHandler.getTaskList();
	}
	
	private static Integer getNewTaskId(List<Task> allTask) {
		int maxTaskId = 0;
		for (Task task : allTask) {
			if (task.getId() > maxTaskId) {
				maxTaskId = task.getId();
			}
			
		}		
		return maxTaskId+1;
	}
}
