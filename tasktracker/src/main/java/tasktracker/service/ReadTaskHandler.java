package tasktracker.service;

import java.util.List;

import tasktracker.model.Task;
import tasktracker.repository.TaskFileManager;

public class ReadTaskHandler {
	public static List<Task> getTaskList() {
		return TaskFileManager.getAllTasks();
	}
}
