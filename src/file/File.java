package file;

import java.io.BufferedReader;
import java.io.FileReader;

//Class that manipulates a file
public class File {
        
    public Boolean WordChecks(String Line) {
    	return Line.matches("[A-Za-z]+");
    }
    
    public void loadFileInsert(String WayFile) throws Exception{
        FileReader Read = new FileReader(WayFile);
        BufferedReader ReadFile = new BufferedReader(Read);
        String Line = ReadFile.readLine();
        while(Line != null) {
        	if(this.WordChecks(Line)) {
        		Line.toLowerCase();
//				inserir();
        	}
        	Line = ReadFile.readLine();        	
        }
        ReadFile.close();
    }
    public void loadFileRemove(String WayFile) throws Exception{
        FileReader Read = new FileReader(WayFile);
        BufferedReader ReadFile = new BufferedReader(Read);
        String Line = ReadFile.readLine();
        while(Line != null) {
        	Line.toLowerCase();
//			remove();
        	Line = ReadFile.readLine();        	
        }
        ReadFile.close();
    }
}
