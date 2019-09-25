import java.io.*;
import java.util.*;
import java.util.Scanner; // untuk proses scan

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Main{
    
    public static void TampilMenu(){

        System.out.println();
        System.out.println("*             TUGAS BESAR 1 ALJABAR LINEAR & GEOMETRI              *");
        System.out.println("*       Sistem Persamaan Linier, Determinan, dan Aplikasinya       *");

        System.out.println();
        System.out.println("========   MENU   ========");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Matriks Kofaktor");
        System.out.println("5. Adjoin");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Keluar");

        System.out.println("");        
    }

    public static MATRIKS TampilInput() throws IOException{
    /*Menghasilkan matriks dari input sesuai keinginan pengguna*/
    /*Prekondisi: Matriks tidak kosong*/
        
        /*Kamus*/
        Scanner in = new Scanner(System.in);
        MATRIKS M;

        /*Algoritma*/
        System.out.println("Pilih input matriks: ");
        System.out.println("1. Input KEYBOARD");
        System.out.println("2. Input File External");
        System.out.println("");

        int pilihFile = in.nextInt();
        M = new MATRIKS(100,101);
        if(pilihFile == 1){
            System.out.print("Masukkan jumlah baris matriks Anda = ");
            int baris = in.nextInt();
            System.out.print("Masukkan jumlah kolom matriks Anda = ");
            int kolom = in.nextInt();
            
            M = new MATRIKS(baris, kolom);
            System.out.println("Silahkan masukkan elemen matriks sesuai ukuran yang Anda berikan");
            System.out.println("Setiap elemen dipisahkan dengan sebuah spasi");
            System.out.println("Setiap baris dipisahkan dengan ENTER");
            M.BacaMATRIKS();
            
        }

        else if(pilihFile == 2){
            System.out.print("Masukkan nama file matriks yang ingin Anda gunakan: ");
            String namafile = in.next();

            M = new MATRIKS(100,101);
            M.BacaFileMatrix(namafile);
            System.out.println("Pembacaan berhasil");
            
        }

        System.out.println();
        in.close();
        return M;
    }

    public static void main(String args[]) throws IOException{
        
        /* Kamus */
        int baris, kolom;
        double hasilDet;
        MATRIKS M, Mkof;
        int pilihan, pilihan1;
        Scanner in = new Scanner(System.in);
        int lanjut = 1; 

        /* Algoritma */      
        TampilMenu();

        System.out.print(">> Pilihan Anda: ");
    	pilihan = in.nextInt();
        System.out.println(" ");

        while (pilihan != 7 && lanjut == 1) {

            if (pilihan ==1){
                
                System.out.println("======= MENCARI SOLUSI DARI SPL =======");
                System.out.println("Pilih cara yang ingin Anda gunakan:");
                System.out.println("1. Metode Eliminasi Gauss ");
                System.out.println("2. Metode Eliminasi Gauss-Jordan");
                System.out.println("3. Metode Matriks Balikan ");
                System.out.println("4. Kaidah Cramer");
                System.out.println(" ");

                System.out.print(">> Pilihan Anda: ");
                pilihan1 = in.nextInt();

                System.out.println("Anda harus memasukan SPL dalam bentuk matriks augemented");
                M = TampilInput();

                if (pilihan1 == 1){
                    M.Gauss();
                }

                else if (pilihan1 == 2){
                    M.Gauss();
                    M.Jordan();
                }

                else if (pilihan1 == 3){


                }

                else if (pilihan1 == 4){
                    M.CariMCrammer();
                }

            } 

            else if (pilihan == 2) {
                System.out.println("======= MENCARI DETERMINAN =======");
                System.out.println("Pilih cara yang ingin Anda gunakan:");
                System.out.println("1. Metode OBE");
                System.out.println("2. Metode Matriks Kofaktor");
                System.out.println(" ");

                System.out.print(">> Pilihan Anda: ");
                pilihan1 = in.nextInt();
                System.out.println(" ");

                M = TampilInput();
                
                System.out.println(" ");
                System.out.print("Nilai determinan dari matriks tersebut = ");

                if (pilihan1 == 1){
                    M.Gauss();
                }
                else if (pilihan == 2) {
                    hasilDet = M.DetCof(M);
                    System.out.printf("%.3f", hasilDet);
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
                System.out.print("Masukkan jumlah baris dan kolom matriks Anda = ");
                kolom = in.nextInt();
                M = new MATRIKS(kolom, kolom);
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
                System.out.print("Masukkan jumlah baris dan kolom matriks Anda = ");
                kolom = in.nextInt();
                M = new MATRIKS(kolom, kolom);
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
                System.out.print("Masukkan jumlah baris dan kolom matriks Anda = ");
                kolom = in.nextInt();
                M = new MATRIKS(kolom, kolom);
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

                TampilMenu();
                System.out.print(">> Pilihan Anda: ");
        
    	        pilihan = in.nextInt();

                System.out.println(" ");
            }

        }
        System.out.println("======= GOOD BYE =======");
        in.close();

    }
}