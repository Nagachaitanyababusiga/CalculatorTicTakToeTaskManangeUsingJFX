package example;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class TaskViewer {
    public String tName;
    public String tDueDate;
    public String tDescription;
    public String tStatus;

    public void viewTask(String tName) {
        this.tName=tName;
        Scanner sc;
        String sourcefilepath="C:\\Users\\NAGA BABU SIGA\\OneDrive\\Desktop\\assignment_test_txt.txt";
        String deletestring=tName;
        String namedt="";String dest="";String duet="";String stat="";
        try{
            sc=new Scanner(new File(sourcefilepath));
            sc.useDelimiter("[,\n]");
            while(sc.hasNext()){
                namedt=sc.next();
                dest=sc.next();
                duet=sc.next();
                stat=sc.next();
                if(namedt.equals(deletestring)){
                    namedt=tName;
                    tDescription=dest;
                    tDueDate=duet;
                    tStatus=stat;
                }
            }
            sc.close();
        }
        catch(IOException e){
            System.out.println("There is an io exception!");
        }
    }
}
