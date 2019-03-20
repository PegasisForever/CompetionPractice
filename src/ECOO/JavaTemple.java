package ECOO;

import java.util.List;
import java.util.Scanner;

public class JavaTemple {
    private static Scanner input=new Scanner(System.in);

    public static void main(String[] args){
        repeat(10,(it)->{
            println(it);
        });
    }

    static String next(){
        return input.next();
    }
    static int nextInt(){
        return input.nextInt();
    }
    static float nextFloat(){
        return input.nextFloat();
    }
    static void repeat(int time, Action runnable){
        for (int i=0;i<time;i++){
            runnable.run(i);
        }
    }
    static void print(Object o){
        System.out.print(o);
    }
    static void println(Object o){
        System.out.println(o);
    }
    static<E> String list2String(List<E> list,String start,String separate,String end){
        StringBuilder sb=new StringBuilder();
        sb.append(start);
        for (int i=0;i<list.size();i++){
            sb.append(list.get(i).toString());
            if (i!=list.size()-1){
                sb.append(separate);
            }
        }
        sb.append(end);
        return sb.toString();
    }
}
