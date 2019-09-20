public class SPL{
    // Atribut
    public MATRIKS Mtrx; // untuk memasukkan persamaan
    public double [] Solusi; // untuk menampung solusi x1..xn
    public int [] solved;
	/* Status solved untuk x1 - xn sesuai dengan index dari array */
	/* 1 untuk x yang diketahui nilai eksaknya, 2 untuk yang bisa disubstitusikan 
	 * Untuk yang masih 0 harusnya sudah parametric dasar */

     /* Konstruktor */
     public SPL(int nb, int nk){
         this.Mtrx = new MATRIKS(nb-1, nk-1);
         Solusi = new double [nk];
         solved = new int [nk];
     }

}