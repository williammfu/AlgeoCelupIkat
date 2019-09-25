import java.io.*;

public class deletedsoon{
    public static void main(String args[])throws IOException{
        /* Kamus */
        SPL splku;
        /* Algoritma */
        splku = new SPL(3,6);
        splku.BacaSPLKeyBoard();
        splku.SolusiByGaussJordan();
        splku.TulisSPL("Angels.txt");
    }
}