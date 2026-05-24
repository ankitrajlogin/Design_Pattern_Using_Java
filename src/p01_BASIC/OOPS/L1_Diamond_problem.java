package p01_BASIC.OOPS;


interface A {

    default void show() {
        System.out.println("A show()");
    }
}

interface B {

    default void show() {
        System.out.println("B show()");
    }
}

class Test1 implements A, B {

    public void show() {
        System.out.println("Ambiguity resolved");
    }
}

interface Test2 extends A, B {

    @Override
    default void show() {
        A.super.show();
    }
}

class Test3 implements A, B {

    @Override
    public void show() {

        A.super.show();
        B.super.show();

        System.out.println("Own method");
    }
}




public class L1_Diamond_problem {

    public static void main(String[] args){
        Test1 t = new Test1() ;
        t.show() ;


        Test3 t2 = new Test3() ;
        t2.show() ;


    }

}
