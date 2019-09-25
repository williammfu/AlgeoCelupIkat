import java.io.*;
import java.util.*;
import java.util.Scanner; // untuk proses scan
import java.text.DecimalFormat; // untuk menampilkan 2(sbnrnya gk hrs 2) digit desimal
import java.lang.Math; // untuk fungsi absolute

public class MATRIKS{

    /*DEKLARASI KONSTANTA*/
    public static int BrsMin = 1;
    public static int KolMin = 1;
    Scanner in = new Scanner(System.in); // shortcut untuk input co in.nextInt()
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    /*DEKLARASI ATTRIBUTE KELAS*/
    public double [][] Mem;
    public int NBrsEff; /* banyaknya/ukuran baris yg terdefinisi */
    public int NKolEff; /* banyaknya/ukuran kolom yg terdefinisi */
    public double detByGauss;

    /*BLOK KONSTRUKTOR*/
    public MATRIKS(int x, int y) {
        Mem = new double [x+1][y+1];
        NBrsEff=x;
		NKolEff=y;
      }
    /*BLOK SELEKTOR*/
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

    /* Mengubah elemen dari matriks */
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

    /* BLOK METHOD UNTUK OPERASI BARIS ELEMENTER */
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

    /***BLOK METHOD INPUT/OUTPUT MATRIKS***/

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

    /***MODIFIKASI MATRIKS ***/

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


        public MATRIKS KaliMatriks(MATRIKS M2){
            /* Prekondisi : Ukuran kolom efektif M1 = ukuran baris efektif M2 */
            /* Mengirim hasil perkalian matriks: salinan M1 * M2 */
            /*KAMUS*/
            MATRIKS Mhasil;
            int i, j, k;
            double temphasil;

            /*ALGORITMA*/
            Mhasil = new MATRIKS(this.NBrsEff, M2.NKolEff);
           
            for(i=this.GetFirstIdxBrs(); i<= this.GetLastIdxBrs(); i++){
        
                for(j= M2.GetFirstIdxKol(); j<= M2.GetLastIdxKol(); j++){
                    temphasil = 0;
                    for (k= this.GetFirstIdxKol(); k<= this.GetLastIdxKol(); k++){
                        temphasil += this.Mem[i][k] * M2.Mem[k][j];
                        }
                    Mhasil.Mem[i][j] = temphasil;
                }
            }
            return Mhasil;
        }

        public void KaliKons (double K){
            /* I.S. M terdefinisi, K terdefinisi */
            /* F.S. Mengalikan setiap elemen M dengan K */
        
            /*KAMUS*/
            int i, j;

            /*ALGORITMA*/

            for (i=GetFirstIdxBrs(); i<=GetLastIdxBrs(); i++){
                for (j=GetFirstIdxKol(); j <= GetLastIdxKol(); j++){
                    Mem[i][j] = K * Mem[i][j];
                }
            }
        }       

        public void Pivotting (int j, int iM){
        /* Mencari nilai terbesar dari kolom j dari baris iM s/d GetLastBrs(), 
        lalu menaruh nilai terbesar di baris iM (dengan swap) */
            /* Kamus */
            int i, idxBrsMax;
            double max;
		
            /* Algoritma */
            max = Math.abs(this.Mem[iM][j]);
            idxBrsMax = iM;
                for (i= iM+1; i<=this.GetLastIdxBrs(); i++){
                    if(Math.abs(this.Mem[i][j]) > max){
                        max = Math.abs(this.Mem[i][j]);
                        idxBrsMax = i;
                    }
                }
                if(idxBrsMax > iM){
                    this.Swap(iM, idxBrsMax);
                }            
        }

    /***GAUSS-JORDAN***/
        public void Gauss(){
            /* 1. Melakukan pivoting mulai dari kolom pertama, buat index baris = baris pertama
               2. Jika elemen tersebut 0, majukan kolom,sambil lakukan pivoting
               3. Jika ketemu elemen di baris yg tidak 0 bagi row tersebut dengan nilai elemen tsb,tambah index baris
               4. Lakukan traversal dengan index baris baru mulai dari baris 1 s/d baris terakhir
               5. Traversal no 4 mengurangi row pada index baris baru dengan faktor bernilai elemen pada baris dan kolom tsb*/
                
		    /* Kamus */
            int i1,i2,j;
            boolean found = false;
                
            /* Algoritma */
            detByGauss = 1;
            i1 = GetFirstIdxBrs();
            j = GetFirstIdxKol();
                    
            while(i1<=GetLastIdxBrs() && j<=GetLastIdxKol()){ // langkah 1
                    do{
                        Pivotting(j,i1);
                        found = Math.round(Mem[i1][j] * 100000.0)/100000.0 != 0.0;
                        if(!found){
                            j++;
                            detByGauss *= 0; // karena elemen  diagonal ditemukan 0
                        }
                    } while(j<=GetLastIdxKol() && !found);
                        
                    if (found){
                        //ketemu elemen tak 0 di baris itu, langkah3
                        //langkah3
                        for(i2=i1+1; i2<=GetLastIdxBrs(); i2++){
                            KurangiRow(i2,i1,j,GetElmt(i2, j)/GetElmt(i1,j)); // langkah 4 dan 5
                    }
                        detByGauss *= GetElmt(i1,j); // sblm dibikin 1 elemen diagonalnya detnya dikali dulu dgn elemen diagonal tsb.
                        KaliRow(i1, 1/GetElmt(i1,j));//ketemu elemen tak 0 di baris itu, langkah3
                        i1 ++; //langkah3
                        j++; // ulangi dr langkah 1 untuk kolom selanjutnya
                    }       
                }
            }

            public void Jordan ()
            /*Melakukan Eliminasi Jordan*/
            {
                int i,j, k;
                
                /* Algoritma */
                    for (i = GetLastIdxBrs(); i > GetFirstIdxBrs(); i--){
                        j = GetFirstIdxKol();
                        while (j <= GetLastIdxKol() && Mem[i][j] != 1){
                            j ++;
                        }
                        
                        if (j <= GetLastIdxKol()){
                            for (k = i-1; k >= GetFirstIdxBrs(); k--){
                                KurangiRow(k, i, 1, Mem[k][j]);
                            }
                        }        
                    }
            }
                        
	
            /***Blok Kofaktor-Adjoint***/
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

            public double DetOBE(){
                /*Mengembalikan nilai determinan matriks*/
                /*Metode: Operasi Baris Elementer*/
                /*Prekondisi: Matriks bujur sangkar!*/
                        
                /* Kamus */
                int i,j,k,p;
                boolean found = false;
                        
                /* Algoritma */
                MATRIKS Temp = new MATRIKS(NBrsEff,NKolEff);
                for(i=1; i<=GetLastIdxBrs(); i++){
                    for(j=1; j<=GetLastIdxKol(); j++){
                        Temp.Mem[i][j] = this.Mem[i][j];
                    }
                }
    
                double det = 1;
                i = GetFirstIdxBrs();
                j = GetFirstIdxKol();
                            
                while(i<=GetLastIdxBrs() && j<=GetLastIdxKol()){ 
                        
                    do{
                        double max = Math.abs(Temp.Mem[i][j]);
                        int idxBrsMax = i;
                        for(p=i+1; p<=Temp.GetLastIdxBrs(); p++){
                            if(Math.abs(Temp.Mem[p][j])>max){
                                max = Math.abs(Temp.Mem[p][j]);
                                idxBrsMax = p;
                            }
                        }
                        if(idxBrsMax > i){
                            Temp.Swap(i,idxBrsMax);
                            det *= -1;
                        }
                        found = Math.round(Temp.Mem[i][j] * 100000.0)/100000.0 != 0.0;
                        if(!found){
                            j++;
                            det *= 0; 
                        }
                    } while(j<=GetLastIdxKol() && !found);
                                
                    if (found){
                        for(k=i+1; k<=GetLastIdxBrs(); k++){
                            Temp.KurangiRow(k,i,j,Temp.GetElmt(k, j)/Temp.GetElmt(i,j));
                    }
                        det *= Temp.GetElmt(i,j); 
                        i++; 
                        j++; 
                    }       
                }
                return det;
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
            /*Menghasilkan Matriks Kofaktor*/
            /*Prekondisi: Matriks Bujur Sangkar*/

                /*Kamus*/
                MATRIKS MKof = new MATRIKS(this.NBrsEff,this.NKolEff);
                int i,j;

                /*Algoritma*/
                for(i=MKof.GetFirstIdxBrs(); i<=MKof.GetLastIdxBrs(); i++){
                    for(j=MKof.GetFirstIdxKol(); j<=MKof.GetLastIdxKol(); j++){
                        MKof.SetElmt(i,j,Kofaktor(i,j));
                        if(MKof.GetElmt(i, j)== -0){/*Penanganan nilai -0*/
                            MKof.SetElmt(i, j, 0);
                        }                        
                    }
                }                
                return MKof;
            }

            public void PrintKofaktor() throws IOException{

                /*Menghasilkan sebuah matriks kofaktor dari matriks*/
                /*Prekondisi: Matriks bujur sangkar*/

                /*Kamus*/
                MATRIKS MKof;
                String pesan;

                /*Algoritma*/
                if(this.NBrsEff==this.NKolEff){
                    MKof = new MATRIKS(this.NBrsEff, this.NKolEff);
                    MKof = this.MakeKofaktor();
                    pesan = "======= MATRIKS KOFAKTOR =======";
                    System.out.println(pesan);
                    System.out.println();
                    MKof.TulisMATRIKS();
                    /*Output ke file .txt*/
                    MKof.TulisFileMatrix("Matriks_Kofaktor.txt", pesan); 
                }
                else{
                    System.out.println("Matriks bukan SQUARE MATRIX");
                    System.out.println("Matriks kofaktor tidak dapat dicari");
                }
                
            }

            public MATRIKS MakeAdjoint(){
            /*Menghasilkan Matriks Adjoint*/
            /*Prekondisi: Matriks bujur sangkar*/

                /*Kamus*/
                MATRIKS MAdj = new MATRIKS(this.NBrsEff,this.NKolEff);

                /*Algoritma*/
		        MAdj = this.MakeKofaktor();
                MAdj.Transpose();
                return MAdj;
            }

            public void PrintAdjoint() throws IOException{
            /*Prekondisi: matriks bujur sangkar*/
                /*Menghasilkan matriks adjoint*/
                /*Matriks adjoint merupakan 
                transpose dari matriks kofaktor*/

                /*Kamus*/
                MATRIKS MAdj;
		    
                /*Algoritma*/
                if(this.NBrsEff==this.NKolEff){
                    MAdj = this.MakeAdjoint();
                    String pesan = "======= MATRIKS ADJOINT =======";
                    
                    System.out.println("======= MATRIKS ADJOINT =======");
                    System.out.println();
                    MAdj.TulisMATRIKS(); System.out.println();
                    MAdj.TulisFileMatrix("Matriks_Adjoint.txt", pesan);
                }
                else{
                    System.out.println("Bukan Matriks Persegi");
                    System.out.println("Matriks Adjoint tidak ada");
                }
            }

            public void BacaFileMatrix(String fileName) throws FileNotFoundException{

                /*Membuat matriks dari input file external*/
		        Scanner input = new Scanner (new File(fileName));
		        int rows = 0;
		        int columns = 0;
		
	            //Hitung jumlah kolom matrix
		        Scanner eachLine = new Scanner(input.nextLine());
	            while(eachLine.hasNextDouble())
	            {
	    	        eachLine.nextDouble();
	                ++columns;
	            }
	            eachLine.close();
	    
	            //Hitung jumlah baris matrix
	            input = new Scanner (new File(fileName));
		        while(input.hasNextLine())
		        {
		            ++rows;
		            input.nextLine();
		        }
		        input.close();
		
		        NBrsEff=rows;
		        NKolEff=columns;
	
		        //Baca data
		        input = new Scanner(new File(fileName));
		        for(int i = 1; i <= rows; ++i)
		        {
		            for(int j = 1; j <= columns; ++j)
		            {
		    	        Mem[i][j] = input.nextDouble();
		            }
		        }
		        input.close();
            }
            
            public void TulisFileMatrix(String FileName, String Pesan) throws IOException{

                FileWriter fw = new FileWriter(FileName);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw);
                
                out.println(Pesan);
                for(int i=1; i<=this.NBrsEff; i++){
                    for(int j=1; j<=this.NKolEff; j++){
                        out.printf("%.3f   ", this.Mem[i][j]);
                    }
                    out.println();
                }
                out.close();
                
                System.out.println("======== OUTPUT FILE ========");
                System.out.println();
                System.out.println("Hasil telah berhasil ditulis pada file: ");
                System.out.println(FileName); System.out.println();
                System.out.println("======== TERIMA KASIH ========");
            }

            /*** CRAMER'S RULE ***/
            public void CariMCrammer(){
            /* Mengganti 1 kolom i dengan kolom terakhir matriks augmented lalu mencari determinan 
            dari matriks baru tersebut*/
                int i, j, n, k;
                double hasil;

                MATRIKS Mtemp, Mtemp2;

                Mtemp = new MATRIKS(this.NBrsEff, this.NKolEff-1);
                Mtemp2 = new MATRIKS(this.NBrsEff, this.NKolEff-1);

                for (i=Mtemp2.GetFirstIdxBrs(); i <= Mtemp2.GetLastIdxBrs(); i++){
                    for (j = Mtemp2.GetFirstIdxKol(); j<= Mtemp2.GetLastIdxKol(); j++){
                        Mtemp2.Mem[i][j] = this.Mem[i][j];
                    }
                }

                if ((DetCof(Mtemp2) == 0) || (Mtemp2.NBrsEff != Mtemp2.NKolEff)) {
                    System.out.println("Solusi SPL tidak bisa dicari menggunakan metode ini");
                }

                else {
                    for (n = 1; n < this.GetLastIdxKol(); n++){
                        for (i = Mtemp.GetFirstIdxBrs(); i <= Mtemp.GetLastIdxBrs(); i++){
                            for (j = Mtemp.GetFirstIdxKol(); j <= Mtemp.GetLastIdxKol(); j++){
                                k = this.GetLastIdxKol();
                                if (j == n){
                                    Mtemp.Mem[i][j] = this.Mem[i][k];
                                }
                                else{
                                    Mtemp.Mem[i][j] = this.Mem[i][j];
                                }

                            }
                        }
                        hasil = DetCof(Mtemp)/DetCof(Mtemp2);
                        System.out.print("X");
                        System.out.print(n);
                        System.out.print(" = ");
                        System.out.println(hasil);
                    }
                }   
            }

            public boolean CekBrsNolSemua(int brs,int finalKol){
                /* Menghasilkan true jika elemen matriks pada baris i bernilai 0 semua */
                /* Kamus */
                boolean cek=true;
                int j=GetFirstIdxKol();
                /* Algoritma */
                //System.out.println(finalKol);
                while(cek && j<=finalKol){
                    cek = Math.round(GetElmt(brs, j)*100000.0)/100000.0 == 0;
                    j++;
                }
                return cek;

            }

        /**INVERS MATRIKS**/
            
            /*METODE OBE*/
            public void InvOBE(MATRIKS M) throws IOException{
            /*Menampilkan invers dari matriks*/
            /*I.S. Matriks bujur sangkar*/
            /*F.S. Menampilkan invers dari matriks
                dengan metode eliminasi Gauss*/

                /*Kamus*/
                MATRIKS Aug, Inv;
                int i,j,k;
                int p,q,r;
                String pesan;

                /*Algoritma*/
                if((this.DetCof(this)!=0)&&(this.NBrsEff==this.NKolEff)){

                    /*Membuat augmented matriks*/
                    Aug = new MATRIKS(M.NBrsEff, 2*M.NKolEff);

                    /*Matriks identitas ditambahkan pada sebelah kanan matriks M*/
                    for(i=1; i<=Aug.GetLastIdxBrs(); i++){//baris
                        for(j=1; j<=Aug.GetLastIdxKol(); j++){//kolom
                            
                            if(i<=M.GetLastIdxBrs() && j<=M.GetLastIdxKol()){
                                Aug.Mem[i][j] = M.Mem[i][j];
                            }
                            else{
                                if((i+M.GetLastIdxBrs())==j){//elemen diagonal
                                    Aug.Mem[i][j] = 1;
                                }
                                else{//bukan elemen diagonal
                                    Aug.Mem[i][j] = 0;
                                }
                            } 
                        }
                    }

                    /*Melakukan eliminasi Gauss*/
                    Aug.Gauss();

                    /*Matriks M telah menjadi upper triangle, 
                    M akan dijadikan matriks identitas*/
                    for(p=Aug.GetLastIdxBrs()-1; p>=1; p--){
                        for(r=Aug.GetLastIdxBrs(); r>p; r--){
                            q = r;
                            if(Aug.Mem[r][q]!=0){
                                Aug.KurangiRow(p, r, q, Aug.Mem[p][q]/Aug.Mem[r][q]);
                            }
                        }
                    }

                    /*Mengambil matriks invers dari 
                    matriks augmented*/
                    Inv = new MATRIKS(M.NBrsEff, M.NKolEff);
                    for(i=1; i<=M.GetLastIdxBrs(); i++){
                        for(j=M.GetLastIdxKol()+1; j<=Aug.GetLastIdxKol();j++){
                            k = j-M.GetLastIdxKol();
                            Inv.Mem[i][k] = Aug.Mem[i][j];
                        }
                    }

                    /*Menampilkan matriks invers*/
                    Inv.TulisMATRIKS();
                    pesan = "Matriks Invers (Metode Operasi Baris Elementer)";
                    System.out.println();
                    System.out.println(pesan);
                    TulisFileMatrix("Hasil_Invers_OBE.txt", pesan);
                    System.out.println();
                }
                else{/*Matriks tidak invertible*/
                    pesan = "Invers tidak dapat dicari. Berikut adalah matriks awal";
                    System.out.println();
                    System.out.println(">>>> Pesan: Invers tidak dapat dicari");
                    TulisFileMatrix("Hasil_Invers_OBE.txt", pesan);
                    System.out.println();
                }
            }

            /*METODE ADJOINT*/
            public MATRIKS InvAdj(){
            /*Membuat matriks dengan metode Adjoint*/

                /*Kamus*/
                int i,j;
                double det;
                MATRIKS Inv;

                /*Algoritma*/
                
                /*Membuat matriks Adjoint dan
                menghitung determinan Matriks*/
                det = this.DetCof(this);
                Inv = this.MakeAdjoint();

                /*Mengisi matriks Invers*/
                for(i=1; i<=Inv.GetLastIdxBrs(); i++){
                    for(j=1; j<=Inv.GetLastIdxKol(); j++){
                        Inv.Mem[i][j] = (Inv.Mem[i][j]/det);
                        if (Inv.Mem[i][j]== -0){
                                Inv.Mem[i][j] = 0;
                        }
                    }
                }

                return Inv;
            }

            public void PrintInvAdj() throws IOException{
            /*Prekondisi: MATRIKS BUJUR SANGKAR (n x n)*/                
                /*Menampilkan Invers Matriks*/
                /*Metode Adjoint*/

                /*Kamus*/
                MATRIKS Temp;
                int i,j;
                double det;
                String pesan;

                /*Algoritma*/
                if((this.DetCof(this)!=0) && (this.NBrsEff==this.NKolEff)){
                    
                    /*Membuat matriks Adjoint dan
                    menghitung determinan Matriks*/
                    det = this.DetCof(this);
                    this.MakeAdjoint();
                    
                    /*Mengisi matriks Invers*/
                    Temp = new MATRIKS(this.NBrsEff, this.NKolEff);
                    for(i=1; i<=this.GetLastIdxBrs(); i++){
                        for(j=1; j<=this.GetLastIdxKol(); j++){
                            Temp.Mem[i][j] = this.Mem[i][j];
                        }
                    }
                    Temp.InvAdj();
                    
                    /*Output Invers Matriks*/
                    pesan = "Matriks Invers (Metode Adjoint)";
                    System.out.println(pesan);
                    Temp.TulisMATRIKS();
                    Temp.TulisFileMatrix("Hasil_Invers_Adj.txt", pesan);
                }
                else{
                    pesan = "Invers tidak dapat dicari. Berikut adalah matriks awal";
                    System.out.println();
                    System.out.println(">>>> Pesan: Invers tidak dapat dicari");
                    TulisFileMatrix("Hasil_Invers_Adj.txt", pesan);
                    System.out.println();
                }
            }
    }
