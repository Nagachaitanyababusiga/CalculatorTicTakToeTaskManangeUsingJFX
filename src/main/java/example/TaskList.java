package example;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class TaskList{
    static public ArrayList<String> taskList;

    static public void showTask() {
        taskList=new ArrayList<>();
        Scanner sc;
        String sourcefilepath="C:\\Users\\NAGA BABU SIGA\\OneDrive\\Desktop\\assignment_test_txt.txt";
        String namedt="";String dest="";String duet="";String stat="";
        try{
            sc=new Scanner(new File(sourcefilepath));
            sc.useDelimiter("[,\n]");
            while(sc.hasNext()){
                namedt=sc.next();
                dest=sc.next();
                duet=sc.next();
                stat=sc.next();
                taskList.add(namedt);
                System.out.println(dest+" "+duet+" "+stat);
            }
            sc.close();
        }
        catch(IOException e){
            System.out.println("There is an io exception!");
        }
    }
}
