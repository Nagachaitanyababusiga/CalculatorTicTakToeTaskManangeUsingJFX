package example;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class TaskDeletor{
    String tName;
    
    public TaskDeletor(String tName) {
        this.tName = tName;
    }

    public void deleteTask() {
        Scanner sc;
        String sourcefilepath="C:\\Users\\NAGA BABU SIGA\\OneDrive\\Desktop\\assignment_test_txt.txt";
        String tempfilepath="C:\\Users\\NAGA BABU SIGA\\OneDrive\\Desktop\\assignment_temp_txt.txt";
        String deletestring=tName;
        File oldfile =new File(sourcefilepath);
        File newfile=new File(tempfilepath);
        String namedt="";String dest="";String duet="";String stat="";
        try{
            BufferedWriter bw=new BufferedWriter(new FileWriter(tempfilepath,true));
            sc=new Scanner(new File(sourcefilepath));
            sc.useDelimiter("[,\n]");
            while(sc.hasNext()){
                namedt=sc.next();
                dest=sc.next();
                duet=sc.next();
                stat=sc.next();
                if(!namedt.equals(deletestring)){
                    bw.write(namedt+","+dest+","+duet+","+stat+"\n");
                }
            }
            sc.close();
            bw.flush();
            bw.close();
            oldfile.delete();
            File dump=new File(sourcefilepath);
            newfile.renameTo(dump);
        }
        catch(IOException e){
            System.out.println("There is an io exception!");
        }
    }
}