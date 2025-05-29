import java.lang.classfile.instruction.SwitchCase;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ListaTarefa listaTarefa= new ListaTarefa();
        listaTarefa.adicionarTarefa("Fazer Café");
        listaTarefa.adicionarTarefa("Terminar dever do Bruno");
        String tarefa;
        System.out.println("O QUE VOCE DESEJA FAZER?");


        int temp = 0;
        while(temp<1){
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Remover Tarefas");
            System.out.println("3. Ver todas as Tarefas");
            System.out.println("4. SAIR");
            int decida= scan.nextInt();
            scan.nextLine();
            switch (decida){
                case 1:
                    System.out.println("Qual é a tarefa?  ");
                    tarefa = scan.nextLine();
                    listaTarefa.adicionarTarefa(tarefa);
                    break;
                case 2:
                    tarefa = scan.nextLine();
                    listaTarefa.removerTarefa(tarefa);
                    break;
                case 3:
                    System.out.println("Voce tem: "+listaTarefa.obterNumeroTotalTarefas());
                    listaTarefa.obterDescricoesTarefas();
                    break;
                case 4:
                    temp++;
                    break;
                default:
                    System.out.println("Digite novamente!! ");
            }
        }
        listaTarefa.obterDescricoesTarefas();
    }
}