import java.io.*;
import java.util.ArrayList;


public class FileHandler {
    private String path = "/Users/sudhabommakanti/IdeaProjects/To-Do-List/src/";
    String content = "Task_Name | Due date | Status | Project_category ";

    public void writeAsData(ArrayList<Task> list){
        int counter = 0;
        try {
            FileWriter fileWriter = new FileWriter(new File(path + "Task.txt"));
            BufferedWriter bw = new BufferedWriter(fileWriter);
            //bw.write(content);
            bw.append("-------------------------------------------------------");
            bw.append("\n");
            bw.append(content);
            bw.append("\n");
            bw.append("-------------------------------------------------------");
            bw.append("\n");
            for( Task task : list ){
                bw.write(++counter + ") " + task.getItem() + "\n");
            }
            bw.close();
        }
        catch (IOException e) {
            System.out.println(("File doesn't found: " + e));
        }
    }

    //Object Stream
    public void writeAsObject(ArrayList<Task> list)
    {
        try {
            FileOutputStream file = new FileOutputStream(path + "Task.txt");
            ObjectOutputStream output = new ObjectOutputStream(file);

            // writes objects to output stream
            output.writeObject(list);

            output.close();
            file.close();
        }
        catch(IOException e)
        {
            System.out.println("File doesn't found " +  e);
        }

    }

}
