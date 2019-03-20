package ECOO.e18.r1;

import java.util.Scanner;

class ECOOP2 {
    public static void main(String[] args) {
        String ID = " ";
        Scanner scanner = new Scanner(System.in);
        int NumberOfRouteAbout;
        for (int x = 10; x > 0; x--) {
            int D = 70000;
            int NumberOfRoads = scanner.nextInt();
            for (int N = NumberOfRoads; N > 0; N--) {
                int id = scanner.nextInt();
                NumberOfRouteAbout = scanner.nextInt();
                int thisMinD=70000;
                for (int R = NumberOfRouteAbout; R > 0; R--) {
                    int D1 = scanner.nextInt();
                    if (D1 < thisMinD) {
                        thisMinD=D1;
                    }
                }
                if (thisMinD<D){
                    D=thisMinD;
                    ID=String.valueOf(id);
                }else if (thisMinD==D){
                    ID=ID+","+id;
                }
            }
            System.out.println(D + "{" + ID + "}");
        }
    }
}