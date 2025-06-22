package example;
class DataModel{
    private String name;
    private String description;
    private String due_date;
    private String status;
    DataModel(String name,String description,String due_date,String status){
        this.name=name;
        this.description=description;
        this.due_date=due_date;
        this.status=status;
    }
    String get_name(){
        return name;
    }
    String get_description(){
        return description;
    }
    String get_due_date(){
        return due_date;
    }
    String get_status(){
        return status;
    }
}
