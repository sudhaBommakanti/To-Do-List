import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * FileHandler class to write the data into the file and read data from the file
 *
 * @author Sudha Bommakanti
 * @version 2020.10.11
 */
public class FileHandler {
    private String path = "/Users/sudhabommakanti/IdeaProjects/To-Do-List/src/main/java/";
    String content = "Task_Title| Due date | Status | Project ";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Method to write the task data into the file called Task.txt
     * @ param ArrayList of Task
     */
    public void writeAsData(ArrayList<Task> list) {
        int counter = 0;
        try {
            FileWriter fileWriter = new FileWriter(new File(path + "Task.txt"));
            BufferedWriter bw = new BufferedWriter(fileWriter);
            for (Task task : list) {
                bw.write(task.getItem() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(("File doesn't found: " + e));
        }
    }

    /**
     * Method to read the task data from the file called Task.txt
     * returns arraylist of tasks.
     */
    public ArrayList<Task> readAsData() {
        ArrayList<Task> list = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(new File(path + "Task.txt"));
            BufferedReader br = new BufferedReader(fileReader);

            String line = "";
            String[] data;
            while ((line = br.readLine()) != null) {
                //data = line.split("\\|\\|");
                data = line.split("\\*\\*");
                //System.out.println("the available data" + data[1]);
                Task a = new Task(data[0], format.parse(data[1]), data[2], data[3]);
                //Task a = new Task(data[0],data[1],data[2]),data[3]);
                list.add(a);
            }

            br.close();
        } catch (IOException | ParseException e) {
            System.out.println("File not found " + e);
        }
        return list;
    }
}
