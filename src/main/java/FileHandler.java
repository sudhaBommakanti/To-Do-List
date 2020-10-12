import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FileHandler {
    private String path = "/Users/sudhabommakanti/IdeaProjects/To-Do-List/src/";
    String content = "Task_Title| Due date | Status | Project ";
    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
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

    public ArrayList<Task> readAsData()
    {
        ArrayList<Task> list = new ArrayList<>();

        try
        {
            FileReader fileReader = new FileReader(new File( path + "Task.txt"));
            BufferedReader br = new BufferedReader(fileReader);

            String line = "";
            String[] data;
            while ( (line = br.readLine()) != null )
            {
                //data = line.split("\\|\\|");
                data = line.split("\\*\\*");
                //System.out.println("the available data" + data[1]);
               Task a = new Task(data[0],format((data[1])),data[2],data[3]);
                //Task a = new Task(data[0],data[1],data[2]),data[3]);
               // list.add(a);
            }

            br.close();
        }
        catch (IOException e)
        {
            System.out.println("File doesn't found " +  e);
        }
        return list;
    }
}
