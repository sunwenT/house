package test;

/**
 * Created by work on 2018/9/6.
 */
public enum Grandson {
    APPLE(1,"app"),BNANANA(0,"ban"),ORANGE(2,"ora");

    private  int index;
    private   String name;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Grandson(int i, String s) {
        this.index = i;
        this.name = s;
    }

    public static String getName(int index) {
        return "";
    }


}
class Test{
    public static void main(String[] args) {
        System.out.println(Grandson.getName(0));

    }
}
