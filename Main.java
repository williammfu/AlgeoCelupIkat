
import java.util.Scanner; // untuk proses scan


public class Main{
    
    public static void main(String args[]){
<<<<<<< HEAD
        public Scanner in = new Scanner(System.in); 

        MATRIKS M1 = new MATRIKS(2,3);
=======
        MATRIKS M1 = new MATRIKS(3,4);
>>>>>>> 84992338214e04ca673ce3d66722b415abd80f87
        int i;
        i =  M1.GetFirstIdxBrs();
        
        System.out.println(i);

        M1.BacaMATRIKS();
        //M1.TulisMATRIKS();
        //M1.KurangiRow(2, 1,2);
        //M1.KaliRow(2, 2);
        //M1.TulisMATRIKS();
        //M1.Swap(2, 3);
        //M1.TulisMATRIKS(); 
        M1.Gauss();
        //M1.Pivotting(1);
        M1.TulisMATRIKS();

        System.out.println("--------*** TUGAS BESAR 1 ALJABAR LINEAR & GEOMETRI ***--------");
        System.out.println("----*** Sistem Persamaan Linier, Determinan, dan Aplikasinya ***----");

        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Matriks Kofaktor");
        System.out.println("5. Adjoin");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Keluar");

        System.out.println(" ");
        System.out.print("Masukkan pilihan Anda: ");
        
    	
    	int pilihan = in.nextInt();


        System.out.println(" ");

        while (pilihan != 7) {
            switch (pilihan){
                case 1 :
                    System.out.println("1");
                
                break;
            }
        }

        





        
    }
}