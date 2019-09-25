import java.io.*;

public class deletedsoon{
    public static void main(String args[])throws IOException{
        /* Kamus */
        SPL splku;
        MATRIKS M;
        /* Algoritma */
       /* M = new MATRIKS(3, 3);
        M.BacaMATRIKS();
        M.TulisMATRIKS();
        M.InvAdj();
        M.TulisMATRIKS(); */
        splku = new SPL(3,3);
        splku.BacaSPLKeyBoard();
        splku.SolusiByBalikan("Grande.txt");
        /* splku.SolusiByGaussJordan();
        splku.TulisSPL("Angels.txt"); */
    }
}