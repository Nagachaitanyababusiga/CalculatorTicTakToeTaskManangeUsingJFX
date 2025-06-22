package example;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


class TaskAdder{
    String tName,tDueDate,tStatus,tDescription;
    

    public TaskAdder(String tName, String tDescription, String tDueDate, String tStatus) {
        this.tName = tName;
        this.tDueDate = tDueDate;
        this.tStatus = tStatus;
        this.tDescription = tDescription;
    }


    public void addTask() {
            DataModel d=new DataModel(tName,tDescription,tDueDate,tStatus);
            try{
                BufferedWriter writer=new BufferedWriter(new FileWriter("C:\\Users\\NAGA BABU SIGA\\OneDrive\\Desktop\\assignment_test_txt.txt",true));
                writer.write(d.get_name()+","+d.get_description()+","+d.get_due_date()+","+d.get_status()+"\n");
                writer.close();
            }
            catch(IOException e){
                System.out.println("There is an IOEXCEPTION!!");
            }
    }
}
