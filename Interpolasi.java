import java.util.Scanner;
import java.lang.Math;
import java.text.DecimalFormat;

public class Interpolasi{

    private static DecimalFormat df2 = new DecimalFormat("#.###");

    /***OUTPUT POLINOM HASIL INTERPOLASI***/
    public void PrintPolinom(double a[]){
        /*I.S. Array a tidak kosong*/
        /*F.S. Mencetak string persamaan polinom dari elemen-elemen
        sebuah array koefisien polinom*/
    
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
                    polinom += df2.format(a[i]);
                    polinom += "x";
    
                    if((i-1)>1){
                        polinom += "^";
                        polinom += Integer.toString(i-1);
                    }
                }
                else{ /*Elemen positif atau nol*/
                    polinom += "+";
                    polinom += df2.format(a[i]);
                    polinom += "x";
    
                    if((i-1)>1){
                        polinom += "^";
                        polinom += Integer.toString(i-1);
                    }
                }
    
                polinom += " ";
            }
            System.out.println(polinom);
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
        PrintPolinom(a);
        /*Empat angka setelah desimal*/
        System.out.print("Taksiran nilai Yi hasil interpolasi= "); System.out.printf("%.4f",yi);
    }

    public void IntPolFile(String filename){
    /*Method untuk menentukan interpolasi dari File Eksternal*/

    }

/*  public static void main(String[] args){

        Interpolasi i = new Interpolasi();

        i.IntPolKey();
    }*/
}