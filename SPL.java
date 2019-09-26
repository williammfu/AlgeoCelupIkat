import java.io.*;
import java.lang.Math; // untuk fungsi absolute

public class SPL{
    // Atribut
    public MATRIKS Mtrx,Solusi; // untuk memasukkan persamaan
    public double [] SolusiInv;
    // untuk menampung solusi x1..xn
    public boolean [] Bebas;
    public boolean solveable = true;
	/* Status Bebas untuk x1 - xn sesuai dengan index dari array */
    /* true jika ada variabel bebas
    false jika tidak */

     /* Konstruktor */
     public SPL(int m,int n){
         this.Mtrx = new MATRIKS(m, n+1);
         this.Solusi = new MATRIKS(n, n);
         Bebas = new boolean [n+1];
     }

     /* Baca SPL */
     public void BacaSPLKeyBoard(){
         /* Kamus */
         int i,j;
         MATRIKS MTemp;
         /* Algoritma */
         this.Mtrx.BacaMATRIKS();
         if(Mtrx.GetLastIdxKol()-1>Mtrx.GetLastIdxBrs()){
            MTemp = new MATRIKS(Mtrx.GetLastIdxKol()-1, Mtrx.GetLastIdxKol());
            for(i=Mtrx.GetFirstIdxBrs(); i<=Mtrx.GetLastIdxBrs(); i++){
                for(j=Mtrx.GetFirstIdxKol(); j<=Mtrx.GetLastIdxKol(); j++){
                    MTemp.SetElmt(i, j, Mtrx.GetElmt(i, j));
                }
            }
            for(i=Mtrx.GetLastIdxBrs()+1; i<=MTemp.GetLastIdxBrs(); i++){
                for(j=Mtrx.GetFirstIdxKol(); j<=Mtrx.GetLastIdxKol(); j++){
                    MTemp.SetElmt(i, j, 0);
                }
            }
            Mtrx = MTemp;
        }
    }
     public void BacaSPLFile(String fileName) throws FileNotFoundException{
         /* Membaca SPL dari file eksternal */
         /* Kamus */
         MATRIKS MTemp1,MTemp2;
         int i,j;
         /* Algoritma */
         MTemp1 = new MATRIKS(100, 101); // asumsi masukan matriks dari file tidak akan melebihi 100 baris dan 101 kolom
         MTemp1.BacaFileMatrix(fileName);
         if(MTemp1.GetLastIdxKol()-1>MTemp1.GetLastIdxBrs()){
            MTemp2 = new MATRIKS(MTemp1.GetLastIdxKol()-1, MTemp1.GetLastIdxKol());
            for(i=MTemp1.GetFirstIdxBrs(); i<=MTemp1.GetLastIdxBrs(); i++){
                for(j=MTemp1.GetFirstIdxKol(); j<=MTemp1.GetLastIdxKol(); j++){
                    MTemp2.SetElmt(i, j, MTemp1.GetElmt(i, j));
                }
            }
            for(i=MTemp1.GetLastIdxBrs()+1; i<=MTemp2.GetLastIdxBrs(); i++){
                for(j=MTemp1.GetFirstIdxKol(); j<=MTemp1.GetLastIdxKol(); j++){
                    MTemp2.SetElmt(i, j, 0);
                }
            }
            Mtrx = MTemp2;
            this.Solusi = new MATRIKS(MTemp2.NKolEff-1,MTemp2.NKolEff-1);
            this.Bebas = new boolean [MTemp2.NKolEff];
        }
        else{
            this.Mtrx = MTemp1;
            this.Solusi = new MATRIKS(MTemp1.NKolEff-1,MTemp1.NKolEff-1);
            this.Bebas = new boolean [MTemp1.NKolEff];
        }
         

     }
     public void CekSolveAndBebas(){
         /* Kamus */
         int i;
         /* Algoritma */
         for (i = Mtrx.GetFirstIdxBrs(); i<= Mtrx.GetLastIdxBrs(); i++){
            if(Mtrx.CekBrsNolSemua(i,Mtrx.GetLastIdxKol())){
                Bebas[i] = true;
            }
            else if(Mtrx.CekBrsNolSemua(i,Mtrx.GetLastIdxKol()-1)){
                System.out.println("Holaa");
                solveable = false;
                Bebas[i] = false;
            }
            else{
                Bebas[i] = false;
            }
         }
     }

     public void SolusiPerBaris(int i){
        /* Menuntukan nilai xi yaitu tabel baris i pada matriks solusi*/
        /* Kamus */
        double temp=0;
        int j,k;
        /* Algoritma */
        
        // Memasukkan nilai baris ke i
        if(Bebas[i]){
            Solusi.SetElmt(i, i, 1); 
        }
        else{
            for(j=Solusi.GetLastIdxKol(); j>i; j--){
                if(Bebas[j]){
                    Solusi.SetElmt(i, j, Solusi.GetElmt(i, j)-Mtrx.GetElmt(i,j));
                }
                else{
                    for(k=Solusi.GetLastIdxKol(); k>=j; k--){
                        if(Bebas[k]){
                            Solusi.SetElmt(i, k, Solusi.GetElmt(i, k)- Mtrx.GetElmt(i, j)*Solusi.GetElmt(j, k));
                        }
                        else{
                            temp -= Mtrx.GetElmt(i, j)*Solusi.GetElmt(j, k);
                        }
                    }
                }
            }
            temp += Mtrx.GetElmt(i, Mtrx.GetLastIdxKol());
            Solusi.SetElmt(i, j, temp);
        }
    }

    public void UrutinGauss(){
        /* Kamus */
        int i,k;
        boolean found;
        /* Algoritma */
        for(i=Mtrx.GetLastIdxBrs(); i>= Mtrx.GetFirstIdxBrs(); i--){
            if(Mtrx.GetElmt(i, i)==0){
                //cari 1
                found = false;
                k = i+1;
                while(k<=Mtrx.GetLastIdxKol() && !found){
                    found = Math.round(Mtrx.GetElmt(i, k)*100000.0)/100000.0 == 1;
                    k++;
                }
                if(found){
                    Mtrx.Swap(i, k-1);
                }
               
            }
        }
    }

    public void SolusiByBalikan(String FileName)throws IOException{
        FileWriter fw = new FileWriter(FileName);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        /* Kamus */
        MATRIKS M1,M2, MSolusi;
        int i,j;
        /* Algoritma */
        out.println("Solusi Sistem Persamaan Linear");
        M1 = new MATRIKS(Mtrx.GetLastIdxBrs(), Mtrx.GetLastIdxKol()-1);
        M2 = new MATRIKS(Mtrx.GetLastIdxBrs(), 1);
        /* Salin matriks Mtrx index baris 1-baris terakhir dan index kolom 1-kolom terakhir-1 ke M1  */
        for(i=1; i<=Mtrx.GetLastIdxBrs(); i++){
            for(j=1; j<=Mtrx.GetLastIdxKol()-1; j++){
                M1.SetElmt(i, j, Mtrx.GetElmt(i, j));
            }
        }
        /* Salin matriks Mtrx index baris 1-baris terakhir dan index kolom terakhir ke M2 */
        for(i=1; i<=Mtrx.GetLastIdxBrs(); i++){
                M2.SetElmt(i, 1, Mtrx.GetElmt(i, Mtrx.GetLastIdxKol()));
            }
        M1 = M1.InvAdj();
        //MSolusi = M1.KaliMatriks(M2);
        //M1.PrintInvAdj();
        MSolusi = new MATRIKS(M1.GetLastIdxBrs(), 1);
        MSolusi = M1.KaliMatriks(M2);
        for(i=1; i<=MSolusi.GetLastIdxBrs(); i++){
            System.out.printf("X%d: %.2f\n",i,MSolusi.GetElmt(i, 1));
            out.printf("X%d: %.2f\n",i,MSolusi.GetElmt(i, 1));
        }
        out.close();
    }

    public void SolusiByGaussJordan(){
        // Kamus
        int i,j;
        // Algoritma
        Mtrx.Gauss();
        Mtrx.Jordan();
        UrutinGauss();
        CekSolveAndBebas();
        if(!solveable){
            System.out.println("Solusi tidak ada bro!");
        }
        else{
            // Inisialisasi matriks solusi
            for(i = Solusi.GetFirstIdxBrs(); i<=Solusi.GetLastIdxBrs(); i++){
                for(j = Solusi.GetFirstIdxKol(); j<=Solusi.GetLastIdxKol(); j++){
                    Solusi.SetElmt(i, j, 0);
                }
            }
            // Masukan solusi dari tiap variable ke matriks solusi
            for(i = Solusi.GetLastIdxBrs(); i >= Solusi.GetFirstIdxBrs(); i--){
                SolusiPerBaris(i);
            }
        }
    }

     public void SolusiByGauss(){
        // Kamus
        int i,j;
        // Algoritma
        Mtrx.Gauss();
        UrutinGauss();
        CekSolveAndBebas();
        if(!solveable){
            System.out.println("Solusi tidak ada bro!");
        }
        else{
            // Inisialisasi matriks solusi
            for(i = Solusi.GetFirstIdxBrs(); i<=Solusi.GetLastIdxBrs(); i++){
                for(j = Solusi.GetFirstIdxKol(); j<=Solusi.GetLastIdxKol(); j++){
                    Solusi.SetElmt(i, j, 0);
                }
            }
            // Masukan solusi dari tiap variable ke matriks solusi
            for(i = Solusi.GetLastIdxBrs(); i >= Solusi.GetFirstIdxBrs(); i--){
                SolusiPerBaris(i);
            }
        }
    }

    public void TulisSPL(String FileName) throws IOException{
        /*Kamus*/
        boolean isBerawal = false;
        int i,j;
        FileWriter fw = new FileWriter(FileName);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);

        /*Algoritma*/ 
        if(solveable){
            
            out.println("Solusi Sistem Persamaan Linear");
            System.out.println("Solusi Sistem Persamaan Linear");
            for(i = Solusi.GetLastIdxBrs(); i>= Solusi.GetFirstIdxBrs(); i--){
                if(Bebas[i]){
                    System.out.printf("x%d = t%d ",i,i);
                    out.printf("x%d = t%d ",i,i);
                }
                else{
                    System.out.printf("x%d = %.2f ",i,Solusi.GetElmt(i, i));
                    out.printf("x%d = %.2f ",i,Solusi.GetElmt(i, i));
                    isBerawal = true;
                }
                for(j = i+1; j<=Solusi.GetLastIdxKol(); j++){
                   if(Solusi.GetElmt(i, j)> 0){
                        if(isBerawal){
                            System.out.printf("+ %.2ft%d ",Solusi.GetElmt(i, j),j);
                            out.printf("+ %.2ft%d ",Solusi.GetElmt(i, j),j);
                        }
                        else{
                            System.out.printf(" %.2ft%d ",Solusi.GetElmt(i, j),j);
                            out.printf(" %.2ft%d ",Solusi.GetElmt(i, j),j);
                        }
                    }
                   if(Solusi.GetElmt(i, j)< 0){
                        System.out.printf("- %.2ft%d ",Math.abs(Solusi.GetElmt(i, j)),j);
                        out.printf("- %.2ft%d ",Math.abs(Solusi.GetElmt(i, j)),j);
                    }
                }
                System.out.println("");
                out.println(""); 
            }
                    
        }
        else{
            out.println("SPL tidak memiliki solusi");
            System.out.println("SPL tidak memiliki solusi");
        }
        out.close();
        System.out.println("======== OUTPUT FILE ========");
        System.out.println();
        System.out.println("Hasil telah berhasil ditulis pada file: ");
        System.out.println(FileName); System.out.println();
        System.out.println("======== TERIMA KASIH ========");  
    }
}