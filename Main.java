public class Main{
    public static void main(String args[]){
        MATRIKS M1 = new MATRIKS(3,4);
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
    }
}