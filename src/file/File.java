package file;

//Class that manipulates a file
public class File {
    String fileName;

    //File class constructor
    public File(String fileName){
        this.fileName = fileName;
    }

    //Overlodade File class constructor
    public File(){
        this.fileName = "NULL";
    }

    //fileName setter
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    //fileName getter
    public String getFileName(){
        return this.fileName;
    }
}