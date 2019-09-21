import java.io.FileNotFoundException;

public class SPL{
    // Atribut
    public MATRIKS Mtrx; // untuk memasukkan persamaan
    public double [] Solusi; // untuk menampung solusi x1..xn
    public int [] Solved;
	/* Status solved untuk x1 - xn sesuai dengan index dari array */
	/* 1 untuk x yang diketahui nilai eksaknya, 2 untuk yang bisa disubstitusikan 
	 * Untuk yang masih 0 harusnya sudah parametric dasar */

     /* Konstruktor */
     public SPL(int nb, int nk){
         this.Mtrx = new MATRIKS(nb, nk+1);
         Solusi = new double [nk+1];
         Solved = new int [nk+1];
     }

     /* Baca SPL */
     public void BacaSPLKeyBoard(){
         this.Mtrx.BacaMATRIKS();
     }

     public void BacaSPLFile(String fileName) throws FileNotFoundException{
         /* Membaca SPL dari file eksternal */
         /* Kamus */
         MATRIKS temp;
         /* Algoritma */
         temp = new MATRIKS(100, 101); // asumsi masukan matriks dari file tidak akan melebihi 100 baris dan 101 kolom
         temp.BacaFileMatrix(fileName);
         this.Mtrx = temp;
         this.Solusi = new double [temp.NKolEff+1];
         this.Solved = new int [temp.NKolEff+1];

     }
     



}