import java.util.*;
import java.io.*;
import java.lang.Math;
import java.text.DecimalFormat;

public class Interpolasi{

    private static DecimalFormat df2 = new DecimalFormat("#.###");

    /***OUTPUT POLINOM HASIL INTERPOLASI***/
    public String MakePolinom(double a[]){
        /*Membuat string polinom p(x) dari elemen pada array*/
        /*I.S. Interpolasi sudah terdefinisi, a*/
    
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
            for(i=2; i<=(a.length)-1; i++){
                
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
    public void IntPolKey() throws IOException{
        /*Prosedur untuk menghitung nilai (xi,yi) dari
        sejumlah n titik(x,y) dengan interpolasi polinom*/
        /*Input dari keyboard*/
        
            /*Kamus*/
            Scanner input = new Scanner(System.in);
            MATRIKS Temp, Pol;
            int n;
            int i,j,k; /*variabel untuk looping*/
            double xi,yi; /*titik yang ingin ditaksir nilainya*/
            double a[]; /*Menampung koefisien polinom*/
     
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
            
            a = new double[Pol.GetLastIdxBrs()+1];
            a[Pol.GetLastIdxBrs()]= Pol.Mem[Pol.GetLastIdxBrs()][Pol.GetLastIdxKol()];
            for(i=Pol.GetLastIdxBrs()-1; i>=1 ;i--){
                a[i] = Pol.Mem[i][Pol.GetLastIdxKol()];
                for(j=Pol.GetLastIdxKol()-1; j>i; j--){
                    a[i] -= Pol.Mem[i][j]*a[j+1]; 
                }
            }
    
            /*Menghitung nilai polinom untuk xi*/
            /*Hasil disimpan pada yi*/
            yi = 0;
            for(i=1; i<=n; i++){
                yi += Math.pow(xi,i-1)*a[i];
            }
            String polinom = MakePolinom(a);
    
            /*Output*/
            String namafile = "Hasil_Interpolasi.txt";
            outputPol(namafile, polinom, xi, yi);
    
        }

    public void IntPolFile(String filename) throws IOException {
        /*Method untuk menentukan interpolasi dari File Eksternal*/
        /*Prekondisi: Sudah harus ada input nama file txt dari pengguna*/
    
            /*Kamus*/
            double xi,yi;
            Scanner input = new Scanner(System.in);
            MATRIKS Temp,Pol;
            int i,j;
            double a[]; /*Array untuk menampung koefisien polinom*/
            String polinom;
    
            /*Algoritma*/
            System.out.print("Masukan nilai xi yang ingin dicari= ");
            xi = input.nextDouble();
    
            Temp = new MATRIKS(100,101);
    
            /*Membaca file "filename.txt"*/
            Temp.BacaFileMatrix(filename);
            
            /*Membuat matriks augmented sebesar n x (n+1)*/
            Pol = new MATRIKS(Temp.NBrsEff, Temp.NBrsEff+1);
            for(i=1; i<=Pol.GetLastIdxBrs(); i++){
                for(j=1; j<Pol.GetLastIdxKol(); j++){
                    Pol.Mem[i][j] = Math.pow(Temp.Mem[i][1],(j-1));
                }
                Pol.Mem[i][j] = Temp.Mem[i][2];
            }
    
            /*Melakukan operasi Gauss*/
            Pol.Gauss();
            

            /*Back Subtition, untuk mengisi array a*/
            a = new double[Pol.GetLastIdxBrs()+1];
            a[Pol.GetLastIdxBrs()] = Pol.Mem[Pol.GetLastIdxBrs()][Pol.GetLastIdxKol()];
            for(i=Pol.GetLastIdxBrs()-1; i>=1 ;i--){
                a[i] = Pol.Mem[i][Pol.GetLastIdxKol()];
                for(j=Pol.GetLastIdxKol()-1; j>i; j--){
                    a[i] -= Pol.Mem[i][j]*a[j]; 
                }
            }
    
            /*Polinom akan ditulis pada file output*/
            polinom = MakePolinom(a);
            
            /*Menghitung nilai polinom untuk xi*/
            /*Hasil disimpan pada yi*/
            yi = 0;
            for(i=Pol.GetLastIdxBrs(); i>=1; i--){
                yi += a[i]*Math.pow(xi,i-1);
            }
    
            /*Nama file output*/
            String namaBaru = "hasil_" + filename;
    
            /*Masukin ke file external Hasil*/
            this.outputPol(namaBaru, polinom, xi, yi);        
        }

    public void outputPol(String a, String polinom, double xi, double yi) throws IOException{
    /*Mencetak hasil interpolasi dalam sebuah file txt*/
        
        FileWriter fw = new FileWriter(a);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);

        out.println("Persamaan polinom hasil interpolasi:");
        out.println(polinom);

        out.printf("Nilai xi yang ingin dicari = %.5f", xi);
        out.println();
        out.printf("Nilai yi taksiran = %.5f", yi);
        out.close();

        System.out.println();
        System.out.println("======== OUTPUT SUKSES ========");
        System.out.println("Polinom hasil interpolasi adalah: ");
        System.out.println(polinom);
        System.out.printf("Nilai xi yang ingin dicari = %.5f", xi);
        System.out.println();
        System.out.printf("Nilai yi taksiran = %.5f", yi);
        System.out.println();
        System.out.print("Hasil telah disimpan pada file");
        System.out.print(" '"); System.out.print(a + "'\n");
        System.out.println();
        System.out.println("======== ============= ========");
        
    }
}
