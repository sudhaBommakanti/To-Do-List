import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * FileHandler class to write the data into the file and read data from the file
 * @author Sudha Bommakanti
 * @version 2020.10.11
 */
public class FileHandler {
    private String path = "/Users/sudhabommakanti/IdeaProjects/To-Do-List/src/main/java/";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Method to write the list of tasks into the file called Task.txt
     * @ param List of Tasks
     */
    public void writeAsObject(List<Task> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
            FileOutputStream file = new FileOutputStream(path + "Task.txt");
            ObjectOutputStream output = new ObjectOutputStream(file);
            // writes objects to output stream
            output.writeObject(list);
            output.close();
            file.close();
        } catch (IOException e) {
            System.out.println("File not found " + e);
        }
    }

    /**
     * Method to read the task data from the file called Task.txt
     * returns List of tasks.
     */
    public List<Task> readAsObject() {
        List<Task> list = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(path + "Task.txt");
            ObjectInputStream stream = new ObjectInputStream(file);
            // reads objects from input  stream
            list = (ArrayList<Task>) stream.readObject();
            stream.close();
            file.close();
        } catch (IOException e) {
            System.out.println("File not found " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("problems inside the file " + e);
        }
        return list;
    }
}



