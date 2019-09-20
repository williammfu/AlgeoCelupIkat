public class Main{
    public static void main(String args[]){
        MATRIKS M1 = new MATRIKS(3,4);
        int i,j;
        i =  M1.Mem[1].length;
        j =  M1.Mem.length;
        
        System.out.println(i);
        System.out.println(j);


        M1.BacaMATRIKS();
        //M1.TulisMATRIKS();
        //M1.KurangiRow(2,1,1,M1.Mem[2][1]);
        //M1.KurangiRow(3, 1,1,M1.Mem[3][1]);
        //M1.KaliRow(2, 1/M1.Mem[2][2]);
        //M1.KurangiRow(3, 2,1,M1.Mem[3][2]);
        //M1.KaliRow(3,1/M1.Mem[3][3]);
        //M1.KurangiRow(3, 2, 2, );
        //M1.KaliRow(2, 2);
        //M1.TulisMATRIKS();
        //M1.Swap(2, 3);
        //M1.TulisMATRIKS(); 
        M1.Gauss();
        //M1.Pivotting(2,2);
        M1.TulisMATRIKS();
    }
}