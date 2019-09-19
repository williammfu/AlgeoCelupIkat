import java.util.Scanner; // untuk proses scan

public class Main{
    
    public static void main(String args[]){
        Scanner in = new Scanner(System.in); 

        MATRIKS M1 = new MATRIKS(2,3);
        int i, pilihan;
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
        
    	pilihan = in.nextInt();


        System.out.println(" ");

        while (pilihan != 7) {
            if (pilihan ==1){
                System.out.println("Pilih cara yang ingin Anda gunakan:");
                System.out.println("a. ");
                System.out.println("b. ");
                System.out.println("c. ");
                System.out.println("d. ");
            } 

            else if (pilihan == 2) {
                System.out.println("Pilih cara yang ingin Anda gunakan:");
                System.out.println("a. ");
                System.out.println("b. ");
                System.out.println("c. ");
                System.out.println("d. ");
            }

            else if (pilihan ==3) {
                System.out.println("Pilih cara yang ingin Anda gunakan:");
                System.out.println("a. ");
                System.out.println("b. ");
            }
            
            else if (pilihan == 4) {
                System.out.println("4");
            }

            else if (pilihan == 5) {
                System.out.println("5");

            }

            else if (pilihan == 6) {
                System.out.println("6");
            
            }

            System.out.println(" ");
            System.out.print("Masukkan pilihan Anda: ");
        
    	    pilihan = in.nextInt();
            System.out.println(" ");

        }

        

        





        
    }
}