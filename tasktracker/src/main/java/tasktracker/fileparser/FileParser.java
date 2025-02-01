package tasktracker.fileparser;

import java.util.List;

import tasktracker.model.Task;

public interface FileParser {
	public Object stringToObject(String data);

	public String objectToString(Object object);
}
