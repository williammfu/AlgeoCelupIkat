import java.io.*;
import java.util.*;

public class readfile{
    private Scanner x;

    public void openFile(){
        try{
            x = new Scanner(new File("chinese.txt"));
        }
        catch (Exception e){
            System.out.println("could not find file");
        }
    }

    public void readMatriks(){
        while(x.hasNext()){
            int a = x.nextInt();
            int b = x.nextInt();
            //int c = x.next();
            int sum = a+b;

            System.out.printf("%d\n", sum);
        }
    }

    public void closeFile(){
        x.close();
    }
}
