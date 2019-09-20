import java.text.DecimalFormat; // untuk menampilkan 2(sbnrnya gk hrs 2) digit desimal

public class coba{
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    public static void main(String args[]){
        double a = 0.00032;
        double b = 83.1115434343;
        a = Math.round(a*100.0)/100.0;
        System.out.println(a);
        System.out.println(a==0.0);
    }
}