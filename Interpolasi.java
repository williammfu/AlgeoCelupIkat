import java.util.Scanner;
import java.lang.Math;

public class Interpolasi{

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
        n = input.nextInt();
        xi = input.nextDouble(); /*nilai xi yang ingin dicari yi nya*/

        /*Membuat sebuah matriks Temp berukuran (n x 2)*/
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
        /*Output*/ System.out.printf("%.4f", yi);
        /*Empat angka setelah desimal*/
    }
}