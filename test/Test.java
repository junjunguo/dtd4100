package test;


public class Test {
    
    public static void main(String[] args) {
        Apple a = new Apple("red", 5);
        Orange o = new Orange("orange", 9);
        
        System.out.println(a.toString());
        System.out.println(o.toString());
        Fruit f = new Orange("color", 20);
        System.out.println(f.toString());
        Apple m = new Mango("color", 3);
        System.out.println(m.toString());
        System.out.println(f instanceof Orange);
        
        char c = (char) ('a' + 1);

        System.out.println(c);
        int i = 'b' - 'a';
        System.out.println(i);
        int z = 'b';
        System.out.println(z);
        char ic = 98;
        System.out.println(ic);
        
        char t1 = 'a';
        char t2 = 'b';
        System.out.println((t1 + 1) == t2);
        System.out.println((t1 + 1));
    }
}
