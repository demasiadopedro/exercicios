public class Main {

    interface FactorialNumber{
        boolean test(int x, int y);
    }

    public static void main(String[] args) {
        FactorialNumber isFactor = (x,y)-> (y%x)==0;
        if(isFactor.test(3,9)){
            System.out.println("3 é fator de 9");
        }
        else {
            System.out.println("sla porra");
        }
    }
}