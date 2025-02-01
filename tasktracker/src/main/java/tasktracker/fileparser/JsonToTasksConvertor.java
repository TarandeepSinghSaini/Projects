package tasktracker.fileparser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DateFormatter;

import tasktracker.model.Task;

public class JsonToTasksConvertor implements JsonParser {

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	@Override
	public List<Task> stringToObject(String data) {
		List<Task> taskList = new ArrayList<>();
		if (data != null && data != "" && data != " ") {
			String tasks = data.substring(data.indexOf('[') + 1, data.lastIndexOf(']')).replace("\"", "").trim();
			String[] taskArray = tasks.split("},\\s*\\{");
			taskArray[0] = taskArray[0].replace("{", "");
			taskArray[taskArray.length - 1] = taskArray[taskArray.length - 1].replace("}", "");
			for (String singleTask : taskArray) {
				String[] taskProperties = singleTask.split(",");
				Task task = new Task();
				for (String property : taskProperties) {
					property = property.trim();
					String key = property.substring(0, property.indexOf(':'));
					String value = property.substring(property.indexOf(':') + 1);
					switch (key.trim()) {
					case "id":
						task.setId(Integer.parseInt(value.trim()));
						break;
					case "description":
						task.setDescription(value.trim());
						break;
					case "status":
						task.setStatus(value.trim());
						break;
					case "createdAt":
						try {
							task.setCreatedAt(dateFormatter.parse(value.trim()));
						} catch (ParseException e) {
						}
						break;
					case "updatedAt":
						try {
							task.setUpdatedAt(dateFormatter.parse(value.trim()));
						} catch (ParseException e) {
							
						}
						break;
					}
				}
				taskList.add(task);
			}
		}
		return taskList;
	}

	@Override
	public String objectToString(Object object) {
		List<Task> taskList = (List<Task>) object;
		String content = "{\r\n" + "  \"tasks\": [\r\n";
		int totalTasks = taskList.size();
		for (int i = 0; i < totalTasks - 1; i++) {
			content += String.format(
					"{\"id\": %d,\"description\": \"%s\",\"status\": \"%s\",\"createdAt\": \"%s\",\"updatedAt\": \"%s\"},",
					taskList.get(i).getId(), taskList.get(i).getDescription(), taskList.get(i).getStatus(),
					dateFormatter.format(taskList.get(i).getCreatedAt()), taskList.get(i).getUpdatedAt() == null ? "NA"
							: dateFormatter.format(taskList.get((i)).getUpdatedAt()));
		}

		content += String.format(
				"{\"id\": %d,\"description\": \"%s\",\"status\": \"%s\",\"createdAt\": \"%s\",\"updatedAt\": \"%s\"}",
				taskList.get(totalTasks - 1).getId(), taskList.get(totalTasks - 1).getDescription(),
				taskList.get(totalTasks - 1).getStatus(),
				dateFormatter.format(taskList.get(totalTasks - 1).getCreatedAt()),
				taskList.get(totalTasks - 1).getUpdatedAt() == null ? "NA"
						: dateFormatter.format(taskList.get((totalTasks - 1)).getUpdatedAt()));

		content += "]}";
		return content;
	}
}
