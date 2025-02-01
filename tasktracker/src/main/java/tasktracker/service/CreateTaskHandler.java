package tasktracker.service;

import java.util.List;

import tasktracker.model.Task;
import tasktracker.repository.TaskFileManager;

public class CreateTaskHandler {
	public static void saveTasks(List<Task> taskList) {
		TaskFileManager.updateTaskFile(taskList);
	}
}
