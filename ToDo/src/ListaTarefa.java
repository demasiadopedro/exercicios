import java.util.ArrayList;
import java.util.List;

public class ListaTarefa {
    private List<Tarefa> tarefaList;

    public ListaTarefa(){
        this.tarefaList= new ArrayList<>();
    }

    public void adicionarTarefa(String descricao) {
        tarefaList.add(new Tarefa(descricao));
    }
    public void removerTarefa(String descricao){
        List<Tarefa> tarefasParaRemover = new ArrayList<>();
        for (Tarefa t : tarefaList) {
            if (t.getDescricao().equalsIgnoreCase(descricao)) {
                tarefasParaRemover.add(t);
            }
        }
        tarefaList.removeAll(tarefasParaRemover);
    }

    public int obterNumeroTotalTarefas(){

        return tarefaList.size();
    }

    public void obterDescricoesTarefas() {
        if (!tarefaList.isEmpty()) {
            System.out.println("| Índice | Descrição                 |");
            System.out.println("|---------|--------------------------|");
            for (int i = 0; i < tarefaList.size(); i++) {
                System.out.printf("| %-6d | %-25s |%n", i + 1, tarefaList.get(i).getDescricao());
            }
        } else {
            System.out.println("A lista está vazia!");
        }
    }

}
