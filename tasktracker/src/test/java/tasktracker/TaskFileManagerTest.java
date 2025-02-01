package tasktracker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import tasktracker.fileparser.FileParser;
import tasktracker.fileparser.JsonParser;
import tasktracker.fileparser.JsonToTasksConvertor;
import tasktracker.model.Task;
import tasktracker.repository.TaskFileManager;

class TaskFileManagerTest {
	
	
	String content="{\r\n"
			+ "  \"tasks\": [\r\n"
			+ "    {\r\n"
			+ "      \"id\": 1,\r\n"
			+ "      \"description\": \"Write project proposal\",\r\n"
			+ "      \"status\": \"todo\",\r\n"
			+ "      \"createdAt\": \"2025-01-19T10:00:00Z\",\r\n"
			+ "      \"updatedAt\": \"2025-01-19T10:00:00Z\"\r\n"
			+ "    },\r\n"
			+ "    {\r\n"
			+ "      \"id\": 2,\r\n"
			+ "      \"description\": \"Implement user authentication\",\r\n"
			+ "      \"status\": \"in-progress\",\r\n"
			+ "      \"createdAt\": \"2025-01-18T14:30:00Z\",\r\n"
			+ "      \"updatedAt\": \"2025-01-19T09:00:00Z\"\r\n"
			+ "    },\r\n"
			+ "    {\r\n"
			+ "      \"id\": 3,\r\n"
			+ "      \"description\": \"Test payment gateway integration\",\r\n"
			+ "      \"status\": \"todo\",\r\n"
			+ "      \"createdAt\": \"2025-01-17T11:45:00Z\",\r\n"
			+ "      \"updatedAt\": \"2025-01-17T11:45:00Z\"\r\n"
			+ "    },\r\n"
			+ "    {\r\n"
			+ "      \"id\": 4,\r\n"
			+ "      \"description\": \"Fix UI bugs on the dashboard\",\r\n"
			+ "      \"status\": \"done\",\r\n"
			+ "      \"createdAt\": \"2025-01-16T09:15:00Z\",\r\n"
			+ "      \"updatedAt\": \"2025-01-18T13:00:00Z\"\r\n"
			+ "    },\r\n"
			+ "    {\r\n"
			+ "      \"id\": 5,\r\n"
			+ "      \"description\": \"Prepare presentation slides\",\r\n"
			+ "      \"status\": \"in-progress\",\r\n"
			+ "      \"createdAt\": \"2025-01-18T08:00:00Z\",\r\n"
			+ "      \"updatedAt\": \"2025-01-19T10:30:00Z\"\r\n"
			+ "    }\r\n"
			+ "  ]\r\n"
			+ "}\r\n";

	@Test
	void testGetTaskFileContent() {
		String fileContent = TaskFileManager.getTaskFileContent();
		FileParser parser = new JsonToTasksConvertor();
		List<Task> taskList = (List<Task>)parser.stringToObject(fileContent);
		System.out.println(taskList);
		assertNotNull(taskList);
	}

	@Test
	void testGetTaskFile() {
		fail("Not yet implemented");
	}
	

	@Test
	void testMoveCurrentFileToHistory() {
		assert(TaskFileManager.moveCurrentFileToHistory());
	}

}
