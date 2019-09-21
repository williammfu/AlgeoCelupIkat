
import java.io.*;
import java.util.*;

public class coba{
    public void BacaFile() throws FileNotFoundException{
    Scanner x = new Scanner (new File("chinese.txt"));

                while(x.hasNext()){
                    int a = x.nextInt();
                    int b = x.nextInt();
                    //int c = x.next();
                    int sum = a+b;
        
                    System.out.printf("%d\n", sum);
                }

                x.close();
            }
    public void main(String args[]){
        BacaFile();
    }
}