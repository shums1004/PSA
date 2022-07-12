package edu.neu.coe.info6205.union_find;
import java.util.Random;
import java.util.Scanner;

public class UFclient {
    public static int count(int n){
        int c=0,pair=0;
        UF_HWQUPC uf = new UF_HWQUPC(n,true);
        Random random=new Random();
        while(uf.components()!=1){
            int p=random.nextInt(n);
            int  q=random.nextInt(n);
            if(!uf.connected(p,q)){
                uf.union(p,q);
                c++;
//                System.out.println(c);
            }
            pair++;
        }
        return pair;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        for(int i=200; i<=32000; i=i*2){
            int  j = count(i);
            System.out.println("Sites:"+i + "   Pairs:  " + j);
        }


    }

}
