import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Produto> carrinho= new ArrayList();

    public Carrinho(){
        this.carrinho= new ArrayList<>();
    }

    public void adicionarProduto(String nome, double preco, int quantidade) {
        carrinho.add(new Produto(nome, preco, quantidade));
    }

    public void removerProduto(String nome){
        List<Produto> ProdutosParaRemover = new ArrayList<>();
        for (Produto p : carrinho) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                ProdutosParaRemover.add(p);
            }
        }
        carrinho.removeAll(ProdutosParaRemover);
    }

    public int obterNumeroTotalProdutos(){

        return carrinho.size();
    }

    public void obteProdutos() {
        if (!carrinho.isEmpty()) {
            System.out.println("| Índice | Descrição                 |");
            System.out.println("|---------|--------------------------|");
            for (int i = 0; i < carrinho.size(); i++) {
                System.out.printf("| %-6d | %-25s |%n", i + 1, carrinho.get(i).getNome());
            }
        } else {
            System.out.println("A lista está vazia!");
        }
    }

}
