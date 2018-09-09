package test;

/**
 * Created by pc on 2018/8/19.
 */
public class Father extends Thread{
    public int a = 1;
    protected static int b = 2;
    private int c = 3;
    int d = 4;

    public void tt(String a){
        System.out.println("father");
    }

}
