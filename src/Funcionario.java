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

    public String setNome(String newName) {
        return nome = newName;
    }
    public String setCPF(String newCPF) {
        return cpf = newCPF;
    }

    public double setSalario(double newSalario){
        return salario = newSalario;
    }

    public Departamento getDep(){
        return this.dep;
    }

    public Departamento setDep(Departamento newDep){
        return dep = newDep;
    }
}
