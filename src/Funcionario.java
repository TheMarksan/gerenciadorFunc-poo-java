public class Funcionario {
    private String nome;
    private String cpf;
    private double salario;
    private int matricula;
    private Departamento dep;

    public Funcionario(String nome, String cpf, double salario, int matricula, Departamento dep){
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.matricula = matricula;
        this.dep = dep;
    }

    public String getNome() {
        return nome;
    }
    public String getCPF() {
        return cpf;
    }

    public int getMatricula() {
        return matricula;
    }
    public double showSalario(){
        return this.salario;
    }

    public Departamento getDep(){
        return this.dep;
    }
}
