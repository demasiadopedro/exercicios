import javax.swing.*;
import java.util.Random;

public class Otimizar {
    public static void main(String[] args) {
        Double vetores[][]= new Double[10][3];
        
      
    
        for (int i=0;i<10;i++){
            vetores[i]= GerarVetor();
        }

        Double melhorX = vetores[0][0];
        Double melhorY = vetores[0][1];
        Double melhorPonto = vetores[0][2];
        for (int j =1;j<10;j++){
            if (melhorPonto>vetores[j][2]) {
                melhorPonto = vetores[j][2];
                melhorX = vetores[j][0];
                melhorY = vetores[j][1];


            }

        } 

        

         
        System.out.printf("O melhor ponto é o ponto: (%.0f ; %.0f)%n",melhorX,melhorY);
    }



    public static Double[] GerarVetor() {
        Random random = new Random();
        int modulo1 = random.nextInt(11);
        int modulo2 = random.nextInt(11);
        int sinal = random.nextInt(2);
        int sinal2 = random.nextInt(2);

        if (sinal == 0) {
            modulo1 = -modulo1;
        }

        if (sinal2 == 0) {
            modulo2 = -modulo2;
        }

        System.out.printf("(%d ; %d)%n", modulo1, modulo2);

        //String mensage =String.format("(%d ; %d)%n", modulo1, modulo2);
        //JOptionPane.showMessageDialog(null, mensage);

        double x = modulo1;
        double y = modulo2;
        double distancia = Math.sqrt(x * x + y * y);

        Double ponto[] = {x, y, distancia};
        return ponto;
    }



}
