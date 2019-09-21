import java.io.*;
import java.util.*;
import java.util.Scanner; // untuk proses scan

public class Main{
    
    public static void main(String args[]) throws FileNotFoundException{
        /* Kamus */
        int i,j,k, pilihan, pilihan1;
        Scanner in = new Scanner(System.in); 
        MATRIKS M;

        /* Algoritma */

        //MATRIKS M1 = new MATRIKS(3,3);
        //SPL S1 = new SPL(3);
        SPL S2 = new SPL(4);
        //S1.BacaSPLFile("chinese.txt");
        //S1.BacaSPLKeyBoard();
        //S1.CekSolveAndBebas();
        S2.BacaSPLKeyBoard();
        // Inisialisasi matriks solusi
        /*for(j = S1.Solusi.GetFirstIdxBrs(); j<=S1.Solusi.GetLastIdxBrs(); j++){
            for(k = S1.Solusi.GetFirstIdxKol(); k<=S1.Solusi.GetLastIdxKol(); k++){
                S1.Solusi.SetElmt(j, k, 0);
            }
        }
        S1.SolusiPerBaris(3);
        S1.Solusi.TulisMATRIKS();
        System.out.println("Lalalala");
        S1.SolusiPerBaris(2);
        S1.Solusi.TulisMATRIKS();
        S1.SolusiPerBaris(1);
        S1.Solusi.TulisMATRIKS();*/
        S2.SolusiByGauss();
        S2.Solusi.TulisMATRIKS();
        S2.TulisSPL();
        //S1.Mtrx.Gauss();
        //S1.Mtrx.TulisMATRIKS();
        //System.out.println(S1.Solusi.length);
        //System.out.println(S1.Bebas.length);
        //S1.CekSolveAndBebas();
        //System.out.println();
       
        //i =  M1.GetFirstIdxBrs();
        
        //System.out.println(i);
        //M1.BacaFileMatrix("chinese.txt");
        //M1.BacaMATRIKS();
        //M1.TulisMATRIKS();
        //M1.KurangiRow(2,1,1,M1.Mem[2][1]);
        //M1.KurangiRow(3, 1,1,M1.Mem[3][1]);
        //M1.KaliRow(2, 1/M1.Mem[2][2]);
        //M1.KurangiRow(3, 2,1,M1.Mem[3][2]);
        //M1.KaliRow(3,1/M1.Mem[3][3]);
        //M1.KurangiRow(3, 2, 2, );
        //M1.KaliRow(2, 2);
        //M1.TulisMATRIKS();
        //M1.Swap(2, 3);
        //M1.TulisMATRIKS(); 
        //M1.BotTriangular();
        //S1.Mtrx.Gauss();
        //S1.Mtrx.TulisMATRIKS();
        //S1.CekSolved();
        //M1.Gauss();
        //M1.Pivotting(2,2);
        //M1.TulisMATRIKS();
        //M2.TulisMATRIKS();
        //System.out.println(M1.detByGauss);
        //System.out.println(M1.CekBrsNolSemua(3));
        //System.out.println();
        /*for(i=1; i<=S1.Bebas.length-1; i++){
            System.out.println(S1.Bebas[i]);
        }
        System.out.println(S1.solveable);*/
        //System.out.println();
        //M1.InvOBE(M1);
        //System.out.printf("%.2f",M1.DetCof(M1));       
        //M1.Gauss();
        //M1.Pivotting(2,2);
        //M1.TulisMATRIKS();
        //M1.Jordan();
        //M1.TulisMATRIKS();

        //M1.CariMCrammer();


        //System.out.println();
       // System.out.printf("%.2f",M1.DetCof(M1));       
        
        /*
        System.out.println("--------*** TUGAS BESAR 1 ALJABAR LINEAR & GEOMETRI ***--------");
        System.out.println("----*** Sistem Persamaan Linier, Determinan, dan Aplikasinya ***----");

        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Matriks Kofaktor");
        System.out.println("5. Adjoin");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Keluar");

        System.out.println(" ");
        System.out.print("Masukkan pilihan Anda: ");
        
    	pilihan = in.nextInt();


        System.out.println(" ");

        while (pilihan != 7) {
            if (pilihan ==1){
                System.out.println("Pilih cara yang ingin Anda gunakan:");
                System.out.println("1. Metode Eliminasi Gauss ");
                System.out.println("2. Metode Eliminasi Gauss-Jordan");
                System.out.println("3. Metode Matriks Balikan ");
                System.out.println("4. Kaidah Cramer");

                pilihan1 = in.nextInt();
                
                System.out.println("Masukkan SPL dalam bentuk matriks augmented");
                M.BacaMATRIKS();

                if (pilihan1 == 1){
                    M.Gauss();
                    
                }

                else if (pilihan1 == 2){
                    M.Gauss();
                    M.Jordan();
                }

                else if (pilihan == 3){


                }

                else if (pilihan1 == 4){
                    M.CariMCrammer()
                }

            } 

            else if (pilihan == 2) {
                System.out.println("Pilih cara yang ingin Anda gunakan:");
                System.out.println("1. Metode OBE");
                System.out.println("2. Metode Matriks Kofaktor");
                System.out.println("3. Menggunakan Matriks Adjoin");

                pilihan1 = in.nextInt();
                
                System.out.println("Masukkan matriks yang ingin dicari matriks determinannya");
                M.BacaMATRIKS();

                if (pilihan1 == 1){
                    
                }

                else if (pilihan == 2) {

                }

                else if (pilihan == 3){

                }

            }

            else if (pilihan ==3) {
                System.out.println("Pilih cara yang ingin Anda gunakan:");
                System.out.println("1. Metode OBE");
                System.out.println("2. Menggunakan Adjoin");

                pilihan1 = in.nextInt();
                
                System.out.println("Masukkan matriks yang ingin dicari inversnya");
                M.BacaMATRIKS();

                if (pilihan1 == 1){


                }

                else if (pilihan1 == 2){


                }
            }
            
            else if (pilihan == 4) {
                
                System.out.println("Masukkan matriks yang ingin dicari matriks kofaktornya");
                M.BacaMATRIKS();



            }

            else if (pilihan == 5) {

                System.out.println("Masukkan matriks yang ingin dicari matriks adjoinnya");
                M.BacaMATRIKS();

                M.MakeAdjoint();
                System.out.println("Matriks Adjointnya:")
                M.TulisMATRIKS();

            }

            else if (pilihan == 6) {
                System.out.println("6");
            
            }

            System.out.println(" ");
            System.out.print("Masukkan pilihan Anda: ");
        
    	    pilihan = in.nextInt();
            System.out.println(" ");

        }
        in.close();*/
    }
}