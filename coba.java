import java.util.Scanner; // untuk proses scan
public class coba{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in); // shortcut untuk input co in.nextInt()
        int i;
        int [] k = new int[6];
        for (i=1; i<=5; i++){
            k[i] = in.nextInt();
            System.out.println(k[i]);
            System.out.println(i);
        }

    }
}