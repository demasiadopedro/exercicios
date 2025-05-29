import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ArrayList<Book> biblioteca = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        Book book0 = new Book("Java: Como programar","PJ DEITEL Harvey Deitel", 2012);
        Book book1 = new Book("Pequeno Principe", "Maquiavel", 1890);
        Book book2 = new Book("Biblia","Jesus Cristo", 33);
        Book book3= new Book("Harry Potter","J. K. Rowling",1998);

        biblioteca.add(book0);
        biblioteca.add(book1);
        biblioteca.add(book2);
        biblioteca.add(book3);
        int i=0;
        while (i<1){
            System.out.println("O QUE DESEJA FAZER");
            System.out.printf(" 1- Ver todos os livros %n" +
                    " 2- Emprestar algum livro %n" +
                    " 3- Doar um livro %n " +
                    "4- Pesquisar por Autor %n" +
                    " 5- SAIR %n");
            int decida= scan.nextInt();
            scan.nextLine();
            switch (decida){
                case 1:
                    listar();
                    break;
                case 2:
                    emprestar();
                    break;
                case 3:
                    doar();
                    break;
                case 4:
                    System.out.println("O que deseja pesquisar: ");
                    String search= scan.nextLine();
                    pesquisarPorAutor(search);
                case 5:
                    i++;
                    break;

                default:
                    System.out.println("Digite novamente!! ");
            }
        }





    }

    static void listar(){
        for (int i = 0; i < biblioteca.size(); i++) {
            Book livro = biblioteca.get(i);
            System.out.println("-----------------------------------------------------");
            System.out.println(livro.getTitle());
            System.out.println(livro.getAutor());
            System.out.println(livro.getAge());
        }
    }

    static void emprestar(){
        System.out.println("Digite o id do livro: ");
        int id= scan.nextInt();
        biblioteca.remove(id);
    }
    static void doar(){
        Book book4= new Book(null,null, 0);
        System.out.println("Digite o nome do livro: ");
        String titulo= scan.nextLine();
        book4.setTitle(titulo);
        System.out.println("Digite o nome do autor: ");
        String autor = scan.nextLine();
        book4.setAutor(autor);
        System.out.println("Digite o ano em que foi lamçado esse livro: ");
        int ano= scan.nextInt();
        book4.setAge(ano);
        biblioteca.add(book4);
    }
    public static List<Book> pesquisarPorAutor(String autor){
        List<Book> livrosPorAutor = new ArrayList<>();
        for(Book b: biblioteca){
            if (b.getAutor().equalsIgnoreCase(autor)){
                livrosPorAutor.add(b);
            }
        }
        for (int i = 0; i < livrosPorAutor.size(); i++) {
            Book livro = livrosPorAutor.get(i);
            System.out.println("-----------------------------------------------------");
            System.out.println(livro.getTitle());
        }
        return livrosPorAutor;
    }
}