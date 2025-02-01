package tasktracker.service;

import java.util.List;

import tasktracker.model.Task;
import tasktracker.repository.TaskFileManager;

public class CreateTaskHandler {
	public static Boolean saveTasks(List<Task> taskList) {
		return TaskFileManager.updateTaskFile(taskList);
	}
}
