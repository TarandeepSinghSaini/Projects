package tasktracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import tasktracker.model.Task;
import tasktracker.service.TaskManager;

public class Boot {
	static BufferedReader reader;
	static TaskManager manager = new TaskManager();

	public static void main(String[] args) {
		while(true) {
			String userCommand = getUserInput();
			String[] userCommands = userCommand.split(" ");
			Integer taskId = null;
			switch(userCommands[0]) {
			case "add":
				String description = userCommands[1].replace("\"", "");
				for(int i=2;i<userCommands.length;i++) {
					if(userCommands[i].endsWith("\"")){
						description += " "+userCommands[i].replace("\"", "");
					}else {
						description += " "+userCommands[i];
					}	
				}
				taskId = manager.createTask(description);
				if(taskId == null) {
					System.out.println("Task creation failed miserably");
				}else {
					System.out.println(String.format("Task added successfully (ID: %d)",taskId));
				}
			break;
			case "update":
				taskId = manager.updateTaskName(Integer.parseInt(userCommands[1]), userCommands[2]);
				if(taskId == null) {
					System.out.println("Task update failed miserably");
				}else {
					System.out.println(String.format("Task updated successfully (ID: %d)",taskId));
				}
			break;
			case "delete":
				taskId = manager.deleteTask(Integer.parseInt(userCommands[1]));
				if(taskId == null) {
					System.out.println("Task deletion failed miserably");
				}else {
					System.out.println(String.format("Task deleted successfully (ID: %d)",taskId));
				}
			break;
			case "mark-in-progress":
				taskId = manager.updateTaskStatus(Integer.parseInt(userCommands[1]), "mark-in-progress");
				if(taskId == null) {
					System.out.println("Task status not updated");
				}else {
					System.out.println(String.format("Task status updated successfully (ID: %d)",taskId));
				}
			break;
			case "mark-done":
				taskId = manager.updateTaskStatus(Integer.parseInt(userCommands[1]), "mark-done");
				if(taskId == null) {
					System.out.println("Task status not updated");
				}else {
					System.out.println(String.format("Task status updated successfully (ID: %d)",taskId));
				}
			break;
			case "list":
				List<Task> tasks = null;
				if(userCommands.length==1) {
					tasks = manager.getAllTasks();
					System.out.println("Listing all tasks:");
				}else if(userCommands.length==2) {
					tasks = manager.getTaskByStatus(userCommands[1]);
					System.out.println(String.format("Listing %s tasks:",userCommands[1]));
				}
				
				if(tasks == null) {
					System.out.println("No Task Found!");
				}else {
					for(Task task: tasks) {
						System.out.println(String.format("%d. %s - %s",task.getId(), task.getDescription(),task.getStatus()));	
					}
				}
			break;
			case "help":
				help();
			break;
			default:
				System.out.println("No such command! try help command to understand possible commands.");
				
			}
		}

	}
	
	private static void help() {
		System.out.println("# Adding a new task\r\n"
				+ "add \"Buy groceries\"\r\n"
				+ "# Output: Task added successfully (ID: 1)\r\n"
				+ "\r\n"
				+ "# Updating and deleting tasks\r\n"
				+ "update 1 \"Buy groceries and cook dinner\"\r\n"
				+ "delete 1\r\n"
				+ "\r\n"
				+ "# Marking a task as in progress or done\r\n"
				+ "mark-in-progress 1\r\n"
				+ "mark-done 1\r\n"
				+ "\r\n"
				+ "# Listing all tasks\r\n"
				+ "list\r\n"
				+ "\r\n"
				+ "# Listing tasks by status\r\n"
				+ "list done\r\n"
				+ "list todo\r\n"
				+ "list in-progress");
	}
	
	public static String getUserInput() {
		if(reader == null) {
			reader = new BufferedReader(new InputStreamReader(System.in));
		}
		String userInput = "";
		try {
			userInput = reader.readLine();
		} catch (IOException e) {
			System.out.println(e);
		}
		return userInput;
	}

}
