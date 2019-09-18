import java.util.Scanner; // untuk proses scan
import java.text.DecimalFormat; // untuk menampilkan 2 digit desimal
import java.lang.Math; // untuk fungsi absolute

public class MATRIKS{
    // Define konstanta
    public static int BrsMin = 1;
    public static int KolMin = 1;
    public Scanner in = new Scanner(System.in); // shortcut untuk input co in.nextInt()
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    // Define atribut class
    public double [][] Mem;
    public int NBrsEff; /* banyaknya/ukuran baris yg terdefinisi */
    public int NKolEff; /* banyaknya/ukuran kolom yg terdefinisi */

    //Konstruktor
    public MATRIKS(int x, int y) {
        Mem = new double [x+1][y+1];
        NBrsEff=x;
		NKolEff=y;
      }
    //Selektor
        /* *** Selektor: Untuk sebuah matriks M yang terdefinisi: *** */
        public int GetFirstIdxBrs (){
        /* Mengirimkan indeks baris terkecil M */
            return BrsMin;
        }
        public int GetFirstIdxKol (){
        /* Mengirimkan indeks kolom terkecil M */
            return KolMin;
        }
        public int GetLastIdxBrs (){
        /* Mengirimkan indeks baris terbesar M */
            return this.NBrsEff;
        }
        public int GetLastIdxKol (){
        /* Mengirimkan indeks kolom terbesar M */
            return this.NKolEff;
        }
        public double GetElmt (int i,int j){
            /* Mengirimkan elemen M(i,j) */
            return Mem[i][j];
        }
        public double GetElmtDiagonal (int i){
        /* Mengirimkan elemen M(i,i) */
            return GetElmt(i,i);
        }
        // Mengubah elemen dari matriks
        public void SetElmt(int i, int j, double X){
            Mem[i][j] = X;
        }

        public void KurangiElmt(int i, int j, double X){
            SetElmt(i, j, GetElmt(i,j)-X);
        }
        public void KaliElmt(int i, int j, double X){
            SetElmt(i, j, GetElmt(i,j)*X);
        }

        /* OBE */
        public void KurangiRow(int i, int j, double faktor){
        /* Mengurangi elemen baris i dengan faktor dikali elemen baris j */
            /* Kamus */
            int k;
            /* Algoritma */
            for(k=GetFirstIdxKol(); k<=GetLastIdxKol(); k++){
                KurangiElmt(i,k,faktor*GetElmt(j, k));
            }
            
        }
        public void KaliRow(int i, double x){
            /* Kamus */
            int j;
            /* Algoritma */
            for(j=GetFirstIdxKol(); j<=GetLastIdxKol(); j++){
                KaliElmt(i, j, x);
            }
            
        }
        public void Swap(int i, int j){
            /* menukar semua elemen pada baris i dengan semua elemen pada baris j*/
            /* Kamus */
            double temp;
            int k;
            /* Algoritma */
            for (k=GetFirstIdxKol(); k<=GetLastIdxKol(); k++){
                temp = GetElmt(i, k);
                SetElmt(i, k, GetElmt(j,k));
                SetElmt(j ,k, temp);
            }
        }

        public void BacaMATRIKS (){
        /* I.S. IsIdxValid(NB,NK) */
        /* F.S. M terdefinisi nilai elemen efektifnya, berukuran NB x NK */
        /* Proses: Melakukan MakeMATRIKS(M,NB,NK) dan mengisi nilai efektifnya */
        /* Selanjutnya membaca nilai elemen per baris dan kolom */
        /* Contoh: Jika NB = 3 dan NK = 3, maka contoh cara membaca isi matriks :
        1 2 3
        4 5 6
        8 9 10
        */
            /* Kamus */
            int i,j;
            /* Algoritma */
            for (i=GetFirstIdxBrs(); i<=GetLastIdxBrs(); i++){
                for (j=GetFirstIdxKol(); j<=GetLastIdxKol(); j++){
                    SetElmt(i, j, in.nextDouble());
                }
            }
            
        }
        public void TulisMATRIKS (){
        /* I.S. M terdefinisi */
        /* F.S. Nilai M(i,j) ditulis ke layar per baris per kolom, masing-masing elemen per baris
        dipisahkan sebuah spasi */
        /* Proses: Menulis nilai setiap elemen M ke layar dengan traversal per baris dan per kolom */
        /* Contoh: menulis matriks 3x3 (ingat di akhir tiap baris, tidak ada spasi)
        1 2 3
        4 5 6
        8 9 10
        */
            /* Kamus */
            int i,j;
            /* Algoritma */
            for (i=GetFirstIdxBrs(); i<=GetLastIdxBrs(); i++){
                for (j=GetFirstIdxKol(); j<GetLastIdxKol(); j++){
                    System.out.print(df2.format(GetElmt(i, j))+" ");
                }
                System.out.print(df2.format(GetElmt(i, j)));
                System.out.println("");
            }
        }

        public void Pivotting (int j){
            /* Mencari nilai terbesar dari kolom j dari baris j s/d GetLastBrs(), lalu menaruh nilai terbesar di baris j (dengan swap) */
            /* Kamus */
            int i, idxBrsMax;
            double max;
            /* Algoritma */
            max = Math.abs(GetElmt(j, j));
            idxBrsMax = j;
            for (i= j+1; i<=GetLastIdxBrs(); i++){
                if(Math.abs(GetElmt(i, j)) > max){
                    max = Math.abs(GetElmt(i,j));
                    idxBrsMax = i;
                }
            }
            Swap(j, idxBrsMax);
        }
        

}


