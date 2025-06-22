package example;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class TaskEditor{
    public String tName;
    public String tDueDate;
    public String tDescription;
    public String tStatus;

    
    public TaskEditor(String tName, String tDescription,String tDueDate, String tStatus) {
        this.tName = tName;
        this.tDueDate = tDueDate;
        this.tDescription = tDescription;
        this.tStatus = tStatus;
    }

    public void editTask(String editString){

        String sourcefilepath="C:\\Users\\NAGA BABU SIGA\\OneDrive\\Desktop\\assignment_test_txt.txt";
        String tempfilepath="C:\\Users\\NAGA BABU SIGA\\OneDrive\\Desktop\\assignment_temp_txt.txt";

        File oldfile =new File(sourcefilepath);
        File newfile=new File(tempfilepath);
        String namedt1="";String dest1="";String duet1="";String stat1="";
        try{
            Scanner sc=new Scanner(System.in);
            BufferedWriter bw=new BufferedWriter(new FileWriter(tempfilepath));
            sc=new Scanner(new File(sourcefilepath));
            sc.useDelimiter("[,\n]");
            while(sc.hasNext()){
                namedt1 =sc.next();
                dest1  =sc.next();
                duet1  =sc.next();
                stat1=sc.next();
                
                if(!namedt1.equals(editString)){
                    bw.write(namedt1+","+dest1+","+duet1+","+stat1+"\n");
                }
                else if(namedt1.equals(editString)){
                    namedt1=tName;
                    dest1=tDescription;
                    duet1=tDueDate;
                    stat1=tStatus;
                    bw.write(namedt1+","+dest1+","+duet1+","+stat1+"\n");
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