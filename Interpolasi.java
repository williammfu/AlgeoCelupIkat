import java.util.*;
import java.io.*;
import java.lang.Math;
import java.text.DecimalFormat;

public class Interpolasi{

    private static DecimalFormat df2 = new DecimalFormat("#.###");

    /***OUTPUT POLINOM HASIL INTERPOLASI***/
    public String MakePolinom(double a[]){
        /*Membuat string polinom p(x) dari elemen pada array*/
    
            /*Kamus*/
            String polinom;
            int i; 
    
            /*Algoritma*/
            polinom = new String();
            polinom += "p(x) = ";
            
            /*Elemen a^0*/
            polinom += df2.format(a[1]);
            polinom += " ";
    
            /*Elemen selanjutnya*/
            for(i=2; i<=a.length-1; i++){
                
                if(a[i]<0){ /*Elemen negatif*/
                    polinom += " - ";
                    polinom += df2.format(Math.abs(a[i]));
                    polinom += "x";
    
                    if((i-1)>1){
                        polinom += "^";
                        polinom += Integer.toString(i-1);
                    }
                }
                else{ /*Elemen positif atau nol*/
                    polinom += " + ";
                    polinom += df2.format(a[i]);
                    polinom += "x";
    
                    if((i-1)>1){
                        polinom += "^";
                        polinom += Integer.toString(i-1);
                    }
                }
            }
            return polinom;
        }    

    /***INPUT DARI KEYBOARD***/
    public void IntPolKey(){
    /*Prosedur untuk menghitung nilai (xi,yi) dari
    sejumlah n titik(x,y) dengan interpolasi polinom*/
    /*Input dari keyboard*/
    
        /*Kamus*/
        Scanner input = new Scanner(System.in);
        MATRIKS Temp, Pol;
        int n;
        int i,j; /*variabel untuk looping*/
        double xi,yi; /*titik yang ingin ditaksir nilainya*/
        double a[];

        /*Algoritma*/
        System.out.print("Masukan banyaknya titik= ");
        n = input.nextInt();
        System.out.print("Masukan nilai Xi yang ingin diketahui nilai interpolasinya= ");
        xi = input.nextDouble(); /*nilai xi yang ingin dicari yi nya*/

        /*Membuat sebuah matriks Temp berukuran (n x 2)*/
        System.out.println();
        System.out.printf("Masukan %d  buah titik\n", n);
        System.out.println("Antara x dan y dipisahkan dengan spasi");
        System.out.println("Setiap titik dipisahkan dengan ENTER");
        Temp = new MATRIKS(n,2);
        Temp.BacaMATRIKS();

        /*Membuat matriks augmented*/
        Pol = new MATRIKS(n,n+1);
        
        /*Mengisi matriks augmented sesuai titik 
        yang tersimpan pada MATRIKS Temp*/
        for(i=1; i<=Pol.GetLastIdxBrs(); i++){
            for(j=1; j<Pol.GetLastIdxKol(); j++){
                Pol.Mem[i][j] = Math.pow(Temp.Mem[i][1],(j-1));
            }
            Pol.Mem[i][j] = Temp.Mem[i][2];
        }
        
        /*Melakukan operasi Gauss*/
        Pol.Gauss();
        Pol.Jordan();

        /*Nilai koefisien polinom a0,a1,...,an 
        akan disimpan pada sebuah array a*/
        a = new double[n+1];
        for(i=1; i<a.length; i++){
            a[i] = Pol.Mem[i][Pol.GetLastIdxKol()];
        }

        /*Menghitung nilai polinom untuk xi*/
        /*Hasil disimpan pada yi*/
        yi = 0;
        for(i=1; i<=n; i++){
            yi += Math.pow(xi,i-1)*a[i];
        }

        /*Output*/
        System.out.println();
        System.out.println("***** HASIL INTERPOLASI *****");
        System.out.println("Polinom hasil interpolasi adalah: ");
        String polinom = MakePolinom(a);
        System.out.println(polinom);
        /*Empat angka setelah desimal*/
        System.out.print("Taksiran nilai Yi hasil interpolasi= "); System.out.printf("%.4f",yi);
    }

    public void IntPolFile(String filename) throws IOException {
    /*Method untuk menentukan interpolasi dari File Eksternal*/
    /*Prekondisi: Sudah harus ada input nama file txt dari pengguna*/

        /*Kamus*/
        double xi,yi;
        double a[]; /*Untuk menampung koefisien polinom*/
        Scanner input = new Scanner(System.in);
        MATRIKS Temp,Pol;
        int i,j;
        String polinom;

        /*Algoritma*/
        System.out.print("Masukan nilai xi yang ingin dicari= ");
        xi = input.nextDouble();

        /*Membaca file "filename.txt"*/
        Scanner inFile = new Scanner(new File(filename));

        /*Hitung jumlah baris matrix*/
        int countBaris = 0;
        while(inFile.hasNextLine())
        {
            ++countBaris;
            inFile.nextLine();
        }
        inFile.close();


        /*Membuat matriks dari data masukan*/
        inFile = new Scanner (new File(filename));

        Temp = new MATRIKS(countBaris,2);
        for(i = 1; i <= countBaris; ++i){
            for(j = 1; j <= 2; ++j){
                Temp.Mem[i][j] = inFile.nextDouble();
            }
        }
        inFile.close();
        
        /*Membuat matriks augmented sebesar n x (n+1)*/
        Pol = new MATRIKS(countBaris, countBaris+1);
        for(i=1; i<=Pol.GetLastIdxBrs(); i++){
            for(j=1; j<Pol.GetLastIdxKol(); j++){
                Pol.Mem[i][j] = Math.pow(Temp.Mem[i][1],(j-1));
            }
            Pol.Mem[i][j] = Temp.Mem[i][2];
        }

        /*Melakukan operasi Gauss*/
        Pol.Gauss();

        /*Back Subtition, untuk mengisi array a*/
        for(i=Pol.GetLastIdxBrs()-1; i>=1; i--){
            for(j=Pol.GetLastIdxBrs(); j>i; j--){
                int k = j;
                if(Pol.Mem[j][k] != 0){
                    Pol.KurangiRow(i,j,k,Pol.Mem[i][k]/Pol.Mem[j][k]);
                }
            }
        }
        
        /*Mengisi array a*/
        a = new double[countBaris+1];
        for(i=1; i<=Pol.GetLastIdxBrs(); i++){
            a[i] = Pol.Mem[i][Pol.GetLastIdxKol()];
        }

        /*Polinom akan ditulis pada file output*/
        polinom = MakePolinom(a);
        System.out.println(polinom);
        
        /*Menghitung nilai polinom untuk xi*/
        /*Hasil disimpan pada yi*/
        yi = 0;
        for(i=1; i<=countBaris; i++){
            yi += Math.pow(xi,i-1)*a[i];
        }

        /*Nama file output*/
        String namaBaru = "hasil" + filename;

        /*Masukin ke file external Hasil*/
        input.close();
        this.outputPol(namaBaru, polinom, xi, yi);
        System.out.println();
        System.out.println("*** OUTPUT SUKSES ***");
        System.out.print("Hasil telah disimpan pada file");
        System.out.print(" '"); System.out.print(namaBaru + "'\n");
        
    }

    public void outputPol(String a, String polinom, double xi, double yi) throws IOException{
    /*Ini apa yatuhan*/
        
        FileWriter fw = new FileWriter(a);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);

        out.println("Persamaan polinom hasil interpolasi:");
        out.println(polinom);

        out.printf("Nilai xi yang ingin dicari = %.5f", xi);
        out.println();
        out.printf("Nilai yi taksiran = %.5f", yi);
        out.close();
    }

}
