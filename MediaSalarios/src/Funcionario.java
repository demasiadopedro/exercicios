public class Funcionario {
    private String nome;
    private int idade;
    private float[] salario= new float[3];

    public Funcionario(String nome, int idade, float s1, float s2, float s3 ){
        this.nome =nome;
        this.idade=idade;
        this.salario[0]= s1;
        this.salario[1]= s2;
        this.salario[2]= s3;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }


    public float[] getSalario() {
        return salario;
    }

    public void setSalario(float[] salario) {
        this.salario = salario;
    }

    public void ImprimirDados(){
        float[] salario= getSalario();
        System.out.println("NOME: "+ getNome());
        System.out.println("IDADE: "+getIdade());
        for(int i = 0; i<salario.length;i++){
            System.out.println(i+1 +"° SALÁRIO: "+salario[i]);
        }
    }

    public void MediaSal(){
        float[] salario= getSalario();
        float soma=0;
        for (float v : salario) {

            soma = soma + v;
        }
        float media= soma/salario.length;
        System.out.println(("MEDIA É "+media));
    }
}
