public class Book {
    private String title;
    private String autor;
    private int age;


    public Book(String titulo, String autor, int ano){
        this.title=titulo;
        this.autor= autor;
        this.age= ano;
    }

    public String getTitle(){
        return title;
    }
    public String setTitle( String titulo){
        this.title= titulo;
        return titulo;
    }

    public String getAutor(){
        return autor;
    }
    public String setAutor(String autor){
        this.autor= autor;
        return autor;
    }

    public int getAge(){
        return age;
    }
    public int setAge(int ano){
        this.age=ano;
        return age;
    }

}