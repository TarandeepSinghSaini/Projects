package tasktracker.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import tasktracker.fileparser.FileParser;
import tasktracker.fileparser.JsonToTasksConvertor;
import tasktracker.model.Task;

public class TaskFileManager {
	static String fileName = "TaskFile";
	static String filePath = "C:\\Users\\tsaini\\";
	static String taskHistoryPath = filePath+"taskHistory\\";
	static String fileExtension = ".json";
	static File file;
	
	public static List<Task> getAllTasks(){
		String fileContent = TaskFileManager.getTaskFileContent();
		FileParser parser = new JsonToTasksConvertor();
		List<Task> taskList = (List<Task>)parser.stringToObject(fileContent);
		return taskList;
	}
	
	public static String getTaskFileContent() {
		String fileContent = "";
		File taskFile = getTaskFile();
		try (BufferedReader fileReader = new BufferedReader(new FileReader(taskFile))) {
			String line;
			while ((line = fileReader.readLine()) != null) {
				fileContent += line;
			}
		} catch (FileNotFoundException fileExp) {
			System.err.println(fileExp);
		} catch (IOException ioExp) {
			System.err.println(ioExp);
		}

		return fileContent;
	}	
	
	
	public static File getTaskFile() {
		if (file == null) {
			file = new File(filePath + fileName + fileExtension);
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
	}
	
	public static boolean updateTaskFile(List<Task> taskList) {
		Boolean success = Boolean.TRUE;
		moveCurrentFileToHistory();
		getTaskFile();
		FileParser parser = new JsonToTasksConvertor();
		String content = parser.objectToString(taskList);
		try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))){
			fileWriter.write(content);
		} catch (FileNotFoundException fileExp) {
			System.err.println(fileExp);
			success = Boolean.FALSE;
		} catch (IOException ioExp) {
			System.err.println(ioExp);
			success = Boolean.FALSE;
		}
		return success;
	}
	
	public static boolean moveCurrentFileToHistory() {
		File oldFile = getTaskFile();
		if(!Files.exists(Paths.get(taskHistoryPath))) {
			try {
				Files.createDirectories(Paths.get(taskHistoryPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		File renamedFile = new File(taskHistoryPath + fileName+"_"+new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss").format(Date.from(Instant.now()))+fileExtension);
		oldFile.renameTo(renamedFile);
		file = null;
		return true;
	}
}
