import java.util.Scanner; // untuk proses scan
import java.text.DecimalFormat; // untuk menampilkan 2(sbnrnya gk hrs 2) digit desimal
import java.lang.Math; // untuk fungsi absolute

public class MATRIKS{
    // Define konstanta
    public static int BrsMin = 1;
    public static int KolMin = 1;
    Scanner in = new Scanner(System.in); // shortcut untuk input co in.nextInt()
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
            return this.Mem[i][j];
        }
        public double GetElmtDiagonal (int i){
        /* Mengirimkan elemen M(i,i) */
            return GetElmt(i,i);
        }
        // Mengubah elemen dari matriks
        public void SetElmt(int i, int j, double X){
            this.Mem[i][j] = X;
        }

        public void KurangiElmt(int i, int j, double X){
            /* Kamus */
            double a;
            /* Algoritma */
            a = this.GetElmt(i,j)-X;
            if(a == -0){
                a = 0;
            }
            this.SetElmt(i, j, a);

        }
        public void KaliElmt(int i, int j, double X){
            if(this.GetElmt(i,j)!=0){
                this.SetElmt(i, j, this.GetElmt(i,j)*X);
            }
            
        }

        /* OBE */
        public void KurangiRow(int i, int j,int startCol, double faktor){
        /* Mengurangi elemen baris i dengan faktor dikali elemen baris j */
            /* Kamus */
            int k;
            /* Algoritma */
            for(k=startCol; k<=this.GetLastIdxKol(); k++){
                this.KurangiElmt(i,k,faktor*this.GetElmt(j, k));
            }
            
        }
        public void KaliRow(int i, double x){
            /* Kamus */
            int j;
            /* Algoritma */
            for(j=this.GetFirstIdxKol(); j<=this.GetLastIdxKol(); j++){
                this.KaliElmt(i, j, x);
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

        public void Transpose (){
            /* I.S. M terdefinisi, pasti berukuran persegi */
            /* F.S. M "di-transpose", yaitu setiap elemen M(i,j) ditukar nilainya dengan elemen M(j,i) */

            /* KAMUS */
            int i, j;
            double temp;

            /* ALGORITMA */

            for (i=GetFirstIdxBrs(); i<=GetLastIdxBrs(); i++){
                for (j=GetFirstIdxKol()+i; j <= GetLastIdxKol(); j++){
                    temp = GetElmt(i, j); 
                    SetElmt( i, j, GetElmt(j, i));
                    SetElmt(j, i, temp);
                }
            }

        }

        public void Pivotting (int j, int iM){
            /* Mencari nilai terbesar dari kolom j dari baris iM s/d GetLastBrs(), lalu menaruh nilai terbesar di baris iM (dengan swap) */
            /* Kamus */
            int i, idxBrsMax;
            double max;
            /* Algoritma */
            //if(j<=this.GetLastIdxKol() && iM<=this.GetLastIdxBrs()){
                max = Math.abs(this.Mem[iM][j]);
                idxBrsMax = iM;
                for (i= iM; i<=this.GetLastIdxBrs(); i++){
                    if(Math.abs(this.Mem[i][j]) > max){
                        max = Math.abs(this.Mem[i][j]);
                        idxBrsMax = i;
                    }
                }
                this.Swap(iM, idxBrsMax);
            //}
            
        }
        
        public void Gauss(){
            /* 1. Melakukan pivoting mulai dari kolom pertama, buat index baris = baris pertama
               2. Jika elemen tersebut 0, majukan kolom,sambil lakukan pivoting
               3. Jika ketemu elemen di baris yg tidak 0 bagi row tersebut dengan nilai elemen tsb,tambah index baris
               4. Lakukan traversal dengan index baris baru mulai dari baris 1 s/d baris terakhir
               5. Traversal no 4 mengurangi row pada index baris baru dengan faktor bernilai elemen pada baris dan kolom tsb
             */
                /*/* Kamus */
                //int i1,i2,j;
                //boolean found = false;
                /* Algoritma */
                //i1 = 1;
                //j = 1;
                //this.Pivotting(j,i1);
                                /* Kamus */
                                int i1,i2,j;
                                boolean found = false;
                                /* Algoritma */
                                i1 = GetFirstIdxBrs();
                                j = GetFirstIdxKol();
                                while(i1<=GetLastIdxBrs() && j<=GetLastIdxKol()){ // langkah 1
                                    do{
                                        Pivotting(j,i1);
                                        found = Mem[i1][j]!=0;
                                        if(!found){
                                            j++;
                                        }
                                    } while(j<=GetLastIdxKol() && !found);
                                   if (found){
                                   KaliRow(i1, 1/GetElmt(i1,j));//ketemu elemen tak 0 di baris itu, langkah3
                                   //i1 ++; //langkah3
                                   for(i2=i1+1; i2<=GetLastIdxBrs(); i2++){
                                       KurangiRow(i2,i1,j,GetElmt(i2, j)); // langkah 4 dan 5
                                   }
                                   i1 ++; //langkah3
                                   j++; // ulangi dr langkah 1 untuk kolom selanjutnya
                
                                }
                                
                            }
        }
                //KurangiRow(2,1,1,GetElmt(2, 1));
                //KurangiRow(3,1,1,GetElmt(3, 1));
                //KurangiRow(2,2,2,GetElmt(3,2));


            public void Jordan ()
            /*Melakukan Eliminasi Jordan*/
            {

            }
	
            /*Blok Kofaktor-Adjoint*/
            public MATRIKS Minor (int m, int n){

                /*Menghasilkan matriks untuk menghitung nilai Minor ke(m,n)*/
                /*Kamus*/
                MATRIKS MMin;
                int i,j;
                int skipBrs, skipKol;

                /*Algoritma*/
                
                MMin = new MATRIKS(this.NBrsEff-1, this.NKolEff-1);
                skipBrs = 0;
                for(i=MMin.GetFirstIdxBrs(); i<=MMin.GetLastIdxBrs(); i++){

                    skipKol =0;
                    for(j=MMin.GetFirstIdxKol(); j<=MMin.GetLastIdxKol(); j++){

                        if(i==m){
                            skipBrs =1;
                        }
                        if(j==n){
                            skipKol =1;
                        }

                        MMin.Mem[i][j] = this.GetElmt(i+skipBrs, j+skipKol);
                    }
                }
                return MMin;
                
            }
	
            public double DetCof(MATRIKS M){
                
                /*Menghasilkan determinan dari suatu matriks*/
                /*Prekondisi: Matriks bujur sangkar*/
                /*Metode: Cofactor Expansion - Kolom pertama*/

                /*Kamus*/
                double det;
                int i;

                /*Algoritma*/
                if(M.NBrsEff==1){ /*basis*/
                    return M.GetElmt(1,1);
                }
                else{ 
                    det = 0;
                    for(i=M.GetFirstIdxBrs(); i<=M.GetLastIdxBrs(); i++){
                        
                        if(i%2==0){/*rekurens 1*/
                            det += (-1)*M.Mem[i][1]*DetCof(M.Minor(i,1));
                        }
                        else{/*rekurens 2*/
                            det += M.Mem[i][1]*DetCof(M.Minor(i,1));
                        }
                    }

                    return det;
                }

            }
            
            public double Kofaktor(int m, int n) {

                /*Menghasilkan nilai kofaktor dari elemen ke(m,n)*/
                /*Memanfaatkan method Minor*/

                /*Kamus*/
                double kof;

                /*Algoritma*/
                if((m+n)%2==0){
                    kof = this.DetCof(Minor(m,n));
                }
                else{
                    kof = (-1)*this.DetCof(Minor(m,n));
                }

                return kof;
            }

            public MATRIKS MakeKofaktor(){

                /*Menghasilkan sebuah matriks kofaktor dari matriks*/
                /*Prekondisi: Matriks bujur sangkar*/

                /*Kamus*/
                MATRIKS MKof;
                int i,j;

                /*Algoritma*/
		MKof = new MATRIKS(this.NBrsEff, this.NKolEff);
                for(i=GetFirstIdxBrs(); i<=GetLastIdxBrs(); i++){
                    for(j=GetFirstIdxKol(); j<=GetLastIdxKol(); j++){
                        MKof.SetElmt(i,j,Kofaktor(i,j));                        
                    }
                }

                return MKof;
            }

            public void MakeAdjoint(){

                /*Menghasilkan matriks adjoint*/
                /*Matriks adjoint merupakan 
                transpose dari matriks kofaktor*/

                /*Kamus*/
		MATRIKS MKof;
		    
                /*Algoritma*/
                MKof = new MATRIKS(this.NBrsEff, this.NKolEff);
		MKof = this.MakeKofaktor();
		MKof.Transpose();
		MKof.TulisMATRIKS();
    }
}


