import java.io.*;

public class tes14{
    public static void main(String args[])throws IOException{
        /* Kamus */
        SPL splku;
        /* Algoritma */
       /* M = new MATRIKS(3, 3);
        M.BacaMATRIKS();
        M.TulisMATRIKS();
        M.InvAdj();
        M.TulisMATRIKS(); */
        splku = new SPL(6,6);
        splku.Mtrx.SetElmt(1, 1, 1.0);
        splku.Mtrx.SetElmt(1, 2, 1.0/2.0);
        splku.Mtrx.SetElmt(1, 3, 1.0/3.0);
        splku.Mtrx.SetElmt(1, 4, 1.0/4.0);
        splku.Mtrx.SetElmt(1, 5, 1.0/5.0);
        splku.Mtrx.SetElmt(1, 6, 1.0/6.0);
        splku.Mtrx.SetElmt(1, 7, 1.0);
        splku.Mtrx.SetElmt(2, 1, 1.0/2.0);
        splku.Mtrx.SetElmt(2, 2, 1.0/3.0);
        splku.Mtrx.SetElmt(2, 3, 1.0/4.0);
        splku.Mtrx.SetElmt(2, 4, 1.0/5.0);
        splku.Mtrx.SetElmt(2, 5, 1.0/6.0);
        splku.Mtrx.SetElmt(2, 6, 1.0/7.0);
        splku.Mtrx.SetElmt(2, 7, 0.0);
        splku.Mtrx.SetElmt(3, 1, 1.0/3.0);
        splku.Mtrx.SetElmt(3, 2, 1.0/4.0);
        splku.Mtrx.SetElmt(3, 3, 1.0/5.0);
        splku.Mtrx.SetElmt(3, 4, 1.0/6.0);
        splku.Mtrx.SetElmt(3, 5, 1.0/7.0);
        splku.Mtrx.SetElmt(3, 6, 1.0/8.0);
        splku.Mtrx.SetElmt(3, 7, 0);
        splku.Mtrx.SetElmt(4, 1, 1.0/4.0);
        splku.Mtrx.SetElmt(4, 2, 1.0/5.0);
        splku.Mtrx.SetElmt(4, 3, 1.0/6.0);
        splku.Mtrx.SetElmt(4, 4, 1.0/7.0);
        splku.Mtrx.SetElmt(4, 5, 1.0/8.0);
        splku.Mtrx.SetElmt(4, 6, 1.0/9.0);
        splku.Mtrx.SetElmt(4, 7, 0);
        splku.Mtrx.SetElmt(5, 1, 1.0/5.0);
        splku.Mtrx.SetElmt(5, 2, 1.0/6.0);
        splku.Mtrx.SetElmt(5, 3, 1.0/7.0);
        splku.Mtrx.SetElmt(5, 4, 1.0/8.0);
        splku.Mtrx.SetElmt(5, 5, 1.0/9.0);
        splku.Mtrx.SetElmt(5, 6, 1.0/10.0);
        splku.Mtrx.SetElmt(5, 7, 0);
        splku.Mtrx.SetElmt(6, 1, 1.0/6.0);
        splku.Mtrx.SetElmt(6, 2, X);
        splku.Mtrx.SetElmt(6, 3, X);
        splku.Mtrx.SetElmt(6, 4, X);
        splku.Mtrx.SetElmt(6, 5, X);
        splku.Mtrx.SetElmt(6, 6, X);
        splku.Mtrx.SetElmt(6, 7, X);
        //splku.BacaSPLKeyBoard();
        //splku.SolusiByBalikan("Grande.txt");*/
        /* splku.SolusiByGaussJordan();
        splku.TulisSPL("Angels.txt"); */

    }
}