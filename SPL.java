import java.io.FileNotFoundException;

public class SPL{
    // Atribut
    public MATRIKS Mtrx,Solusi; // untuk memasukkan persamaan
     // untuk menampung solusi x1..xn
    public boolean [] Bebas;
    boolean solveable = true;
	/* Status Bebas untuk x1 - xn sesuai dengan index dari array */
    /* true jika ada variabel bebas
    false jika tidak */

     /* Konstruktor */
     public SPL(int n){
         this.Mtrx = new MATRIKS(n, n+1);
         this.Solusi = new MATRIKS(n, n);
         Bebas = new boolean [n+1];
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
         this.Solusi = new MATRIKS(temp.NKolEff-1,temp.NKolEff-1);
         this.Bebas = new boolean [temp.NKolEff];

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
     public void SolusiByGauss(){
        // Kamus
        int i,j;
        // Algoritma
        Mtrx.Gauss();
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
     public void TulisSPL(){
         // Kamus
         int i,j;
         // Algoritma 
         if(solveable){
            for(i = Solusi.GetLastIdxBrs(); i>= Solusi.GetFirstIdxBrs(); i--){
                if(Bebas[i]){
                    System.out.printf("x%d :t%d ",i,i);
                }
                else{
                    System.out.printf("x%d :%.2f ",i,Solusi.GetElmt(i, i));
                }
                for(j = i+1; j<=Solusi.GetLastIdxKol(); j++){
                   if(Solusi.GetElmt(i, j)> 0){
                       System.out.printf("+%.2ft%d ",Solusi.GetElmt(i, j),j);
                   }
                   if(Solusi.GetElmt(i, j)< 0){
                       System.out.printf("%.2ft%d ",Solusi.GetElmt(i, j),j);
                   }
                }
                System.out.println("");
            }
         }
         

     }

     




}