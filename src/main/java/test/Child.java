package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Child{

    public static void main(String[] args) {
        int[] a = {1,3,2,5,3,4};
        //时间复杂度为O(n)，空间复杂度为O(1)
        for(int i=0;i<a.length;i++) {

        }
        // {-1,1,2,5,3,4}
        int n = a[a[0]]; //3
        a[a[0]] = a[0]; // 1
        a[0] = -1; //-1







    }


}
