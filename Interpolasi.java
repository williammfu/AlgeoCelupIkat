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
        int p,q; /*variabel untuk looping*/
        double xi;

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
        for(p=1; p<=Pol.GetLastIdxBrs(); p++){
            for(q=1; q<Pol.GetLastIdxKol(); q++){
                Pol.Mem[p][q] = Math.pow(Temp.Mem[p][1],(q-1));
            }
            Pol.Mem[p][q] = Temp.Mem[p][2];
        }
        
        /*Melakukan operasi Gauss*/
        Pol.Gauss();

        /*Nilai koefisien polinom a0,a1,...,an 
        akan disimpan pada sebuah array a*/

        /*Backward subtitution*/
    } 
}