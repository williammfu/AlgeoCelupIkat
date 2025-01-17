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
        System.out.println("5. Matriks Adjoint");
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

        System.out.print(">> Pilihan Anda: ");
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
        return M;
    }

    public static void main(String args[]) throws IOException{
        
        /* Kamus */
        MATRIKS M;
        int pilihan, pilihan1;
        Scanner in = new Scanner(System.in);
        int lanjut = 1; 
        boolean isSPL;

        /* Algoritma */      
        TampilMenu();

        System.out.print(">> Pilihan Anda: ");
    	pilihan = in.nextInt();
        System.out.println(" ");

        while (pilihan != 7 && lanjut == 1) {/*SPL*/

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

                if (pilihan1 == 1){/*Gauss*/
                    
                    /* Kamus */
                    SPL splG;
                    int pilihan5;
                    String fileName;
                    /* Algoritma */
                    System.out.println("Apakah masukan dari keyboard?");
                    System.out.println("1.Ingin dari keyboard?");
                    System.out.println("2.Ingin dari file?");
                    pilihan5 = in.nextInt();
                    if(pilihan5 == 1){
                        System.out.print("Masukkan jumlah persamaan yang diinginkan = ");
                        int jpers = in.nextInt();
                        System.out.print("Masukkan variabel tak diketahui = ");
                        int jvar = in.nextInt();
                        splG = new SPL(jpers,jvar);
                        System.out.println("Masukkan persamaan dalam matriks augmented = ");
                        splG.BacaSPLKeyBoard();
                        splG.SolusiByGauss();
                        splG.TulisSPL("HasilSPLGaussKey.txt");
                    }
                    else{
                        System.out.printf("Masukkan nama file: ");
                        fileName = in.next();
                        splG = new SPL(100,101);
                        splG.BacaSPLFile(fileName);
                        splG.SolusiByGauss();
                        splG.TulisSPL("HasilSPLGaussFile.txt");
                    }
                    
                }

                else if (pilihan1 == 2){/*Gauss-Jordan*/
                    
                    /* Kamus */
                    SPL splGJ;
                    int pilihan6;
                    String fileName;

                    /* Algoritma */
                    System.out.println("Apakah masukan dari keyboard?");
                    System.out.println("1.Ingin dari keyboard?");
                    System.out.println("2.Ingin dari file?");
                    pilihan6 = in.nextInt();
                    if(pilihan6 == 1){
                        System.out.print("Masukkan jumlah persamaan yang diinginkan = ");
                        int jpers = in.nextInt();
                        System.out.print("Masukkan variabel tak diketahui = ");
                        int jvar = in.nextInt();
                        splGJ = new SPL(jpers,jvar);
                        System.out.println("Masukkan persamaan dalam matriks augmented = ");
                        splGJ.BacaSPLKeyBoard();
                        splGJ.SolusiByGaussJordan();
                        splGJ.TulisSPL("HasilSPLGaussJordanKey.txt");
                    }
                    else{
                        System.out.printf("Masukkan nama file: ");
                        fileName = in.next();
                        splGJ = new SPL(100,101);
                        splGJ.BacaSPLFile(fileName);
                        splGJ.SolusiByGaussJordan();
                        splGJ.TulisSPL("HasilSPLGaussJordanFile.txt");
                    }
                }

                else if (pilihan1 == 3){/*Metode Balikan*/
                    
                    /* Kamus */
                    SPL splB;
                    int pilihan5;
                    String fileName;
                    
                    /* Algoritma */
                    System.out.println("Apakah masukan dari keyboard?");
                    System.out.println("1.Ingin dari keyboard?");
                    System.out.println("2.Ingin dari file?");
                    pilihan5 = in.nextInt();

                    if(pilihan5 == 1){
                        System.out.print("Masukkan jumlah persamaan yang diinginkan = ");
                        int jpers = in.nextInt();
                        System.out.print("Masukkan variabel tak diketahui = ");
                        int jvar = in.nextInt();
                        splB = new SPL(jpers,jvar);
                        System.out.println("Masukkan persamaan dalam matriks augmented = ");
                        splB.BacaSPLKeyBoard();
                        splB.SolusiByBalikan("SolusiDenganBalikan.txt");
                    }
                    else{
                        System.out.printf("Masukkan nama file: ");
                        fileName = in.next();
                        splB = new SPL(100,101);
                        splB.BacaSPLFile(fileName);
                        splB.SolusiByBalikan("SolusiDenganBalikan.txt");
                    }

                }

                else if (pilihan1 == 4){/*Metode Aturan Crammer*/
                    M = TampilInput();
                    M.CariMCrammer();
                }

            } 

            else if (pilihan == 2) {/*Determinan*/

                System.out.println("======= MENCARI DETERMINAN =======");
                System.out.println("Pilih cara yang ingin Anda gunakan:");
                System.out.println("1. Metode OBE");
                System.out.println("2. Metode Matriks Kofaktor");
                System.out.println(" ");

                System.out.print(">> Pilihan Anda: ");
                pilihan1 = in.nextInt();
                System.out.println(" ");

                System.out.println("Perhatian : Matriks harus persegi");
                M = TampilInput();

                M.PrintDet(pilihan1);
            }

            else if (pilihan ==3) { /*Invers Matriks*/

                System.out.println("======= MENCARI MATRIKS BALIKAN =======");
                System.out.println("Pilih cara yang ingin Anda gunakan:");
                System.out.println("1. Metode OBE");
                System.out.println("2. Menggunakan Adjoin");
                System.out.println(" ");

                System.out.print(">> Pilihan Anda: ");
                pilihan1 = in.nextInt();
                
                System.out.println("");
                System.out.println("Perhatian: Matriks harus persegi");
                System.out.println("");
                M = TampilInput();
                

                if (pilihan1 == 1){/*Metode OBE*/
                    M.InvOBE(M);
                }

                else if (pilihan1 == 2){/*Metode Adj/det(A)*/
                    M.PrintInvAdj();
                }
            }
            
            else if (pilihan == 4) { /*Matriks Kofaktor*/
                
                System.out.println("");
                System.out.println("Perhatian: Matriks harus persegi");
                System.out.println("");
                M = TampilInput();
                
                M.PrintKofaktor();
            }

            else if (pilihan == 5) { /*Matriks Adjoint*/

                System.out.println(" ");
                System.out.println("Perhatian : Matriks harus persegi");
                System.out.println(" ");
                M = TampilInput();
                
                M.PrintAdjoint();
            }

            else if (pilihan == 6) { /*Interpolasi Polinom*/
                
                System.out.println();
                System.out.println("===== Interpolasi Polinom =====");
                System.out.println();

                System.out.println("Pilih input matriks: ");
                System.out.println("1. Input KEYBOARD");
                System.out.println("2. Input File External");
                System.out.println("");

                System.out.print(">> Pilihan Anda : ");
                int pilihFile = in.nextInt();
                System.out.println();

                while( (pilihFile!=1) && (pilihFile !=2) ){
                    System.out.println("Pilihan tidak valid");
                    System.out.print(">> Pilihan Anda : ");
                    pilihFile = in.nextInt();
                    System.out.println();
                }

                Interpolasi I = new Interpolasi();
                if(pilihFile==1){
                    I.IntPolKey();
                }
                else if(pilihFile==2){
                    System.out.print("Masukkan nama file yang ingin gunakan: ");
                    String namafile = in.next();
                    I.IntPolFile(namafile);
                }
            }

            System.out.println(" ");
            System.out.println("Apakah masih ada yang ingin dihitung?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            System.out.println(" ");

            System.out.print(">> Pilihan Anda: ");
            lanjut = in.nextInt();
            System.out.println(" ");

            if (lanjut == 1){

                TampilMenu();
                System.out.print(">> Pilihan Anda: ");
        
    	        pilihan = in.nextInt();

                System.out.println(" ");
            }

        }
        System.out.println("======= GOOD BYE =======");
    }
}