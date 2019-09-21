import java.io.*;
import java.util.*;
import java.util.Scanner; // untuk proses scan

public class Main{
    
    public static void main(String args[]) throws FileNotFoundException{
        /* Kamus */
        int i, pilihan, pilihan1, lanjut;
        double hasilDet;
        MATRIKS Mkof;
        Scanner in = new Scanner(System.in); 
        //MATRIKS M;

        /* Algoritma */

        MATRIKS M = new MATRIKS(3,3);
        /*SPL S1 = new SPL(100,101);
        S1.BacaSPLFile("chinese.txt");
        //S1.BacaKeyBoard();
        S1.Mtrx.TulisMATRIKS();
        System.out.println(S1.Solusi.length);
        System.out.println(S1.Solved.length);*/
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
        //M1.Gauss();
        //M1.Pivotting(2,2);
        //M1.TulisMATRIKS();
        //M2.TulisMATRIKS();
        //System.out.println(M1.detByGauss);
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
        
        lanjut = 1;

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

        while (pilihan != 7 && lanjut == 1) {
            if (pilihan ==1){
                System.out.println("/****MENCARI SOLUSI DARI SPL****/");
                System.out.println("Pilih cara yang ingin Anda gunakan:");
                System.out.println("1. Metode Eliminasi Gauss ");
                System.out.println("2. Metode Eliminasi Gauss-Jordan");
                System.out.println("3. Metode Matriks Balikan ");
                System.out.println("4. Kaidah Cramer");
                System.out.println(" ");

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
                    M.CariMCrammer();
                }

            } 

            else if (pilihan == 2) {
                System.out.println("/****MENCARI DETERMINAN****/");
                System.out.println("Pilih cara yang ingin Anda gunakan:");
                System.out.println("1. Metode OBE");
                System.out.println("2. Metode Matriks Kofaktor");
                System.out.println("3. Menggunakan Matriks Adjoin");
                System.out.println(" ");

                pilihan1 = in.nextInt();

                System.out.println(" ");
                
                System.out.println("Masukkan matriks yang ingin dicari matriks determinannya");
                M.BacaMATRIKS();

                System.out.println(" ");
                System.out.print("Nilai determinan dari matriks tersebut = ");
                if (pilihan1 == 1){

                }

                else if (pilihan == 2) {
                    hasilDet = M.DetCof(M);
                    System.out.println(hasilDet);
                }

                else if (pilihan == 3){
                    
                }

            }

            else if (pilihan ==3) {
                System.out.println("/****MENCARI MATRIKS BALIKAN****/");
                System.out.println("Pilih cara yang ingin Anda gunakan:");
                System.out.println("1. Metode OBE");
                System.out.println("2. Menggunakan Adjoin");
                System.out.println(" ");

                pilihan1 = in.nextInt();
                
                System.out.println(" ");
                System.out.println("Masukkan matriks yang ingin dicari matriks balikannya");
                M.BacaMATRIKS();

                System.out.println(" ");
                System.out.println("Berikut matriks balikan dari matriks yang Anda input");

                if (pilihan1 == 1){
                    M.InvOBE(M);

                }

                else if (pilihan1 == 2){
                    M.InvAdj();
                }
            }
            
            else if (pilihan == 4) {

                System.out.println(" ");
                System.out.println("/****MENCARI MATRIKS KOFAKTOR****/");
                System.out.println("Masukkan matriks yang ingin dicari matriks kofaktornya");

                M.BacaMATRIKS();
                
                System.out.println(" ");
                System.out.println("Berikut matriks kofaktor dari matriks yang Anda input");

                Mkof = M.MakeKofaktor();
                Mkof.TulisMATRIKS();                

            }

            else if (pilihan == 5) {
                System.out.println(" ");
                System.out.println("/****MENCARI MATRIKS ADJOIN****/");
                System.out.println("Masukkan matriks yang ingin dicari matriks adjoinnya");

                M.BacaMATRIKS();
                
                System.out.println(" ");
                System.out.println("Berikut matriks adjoin dari matriks yang Anda input");

                M.MakeAdjoint();
                M.TulisMATRIKS();

            }

            else if (pilihan == 6) {
                System.out.println(" ");
                System.out.println("/****INTERPOLASI POLINOM****/");
                
            
            }

            System.out.println(" ");
            System.out.println("Apakah masih ada yang ingin dihitung?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            System.out.println(" ");

            lanjut = in.nextInt();

            if (lanjut == 1){

                System.out.println(" ");
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
            }

        }
        System.out.println(" ");
        System.out.println("/*** GOOD BYE ***/");
        in.close();

    }
}