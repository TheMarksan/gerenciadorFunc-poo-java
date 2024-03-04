import java.util.ArrayList;

public class Departamento {
    private String nome;
    private double folhaSalarial = 0.0;
    private int alocados = 0;
    private static ArrayList<Departamento> departamentos = new ArrayList<>();

    public Departamento(String nome, double folhaSalarial, int alocados) {
        this.nome = nome;
        this.folhaSalarial = folhaSalarial;
        this.alocados = alocados;
    }

    public static void inicializarDepartamentos() {
        departamentos.add(new Departamento("TI", 0.0, 0));
        departamentos.add(new Departamento("Marketing", 0.0, 0));
        departamentos.add(new Departamento("RH", 0.0, 0));
        departamentos.add(new Departamento("Gerência", 0.0, 0));
        departamentos.add(new Departamento("Serviços Gerais", 0.0, 0));
    }

    public static ArrayList<Departamento> getDepartamentos() {
        return departamentos;
    }

    public String getNome() {
        return nome;
    }

    public double incrementFolha(double salario) {
        return folhaSalarial += salario;
    }

    public double getFolhaSalarial() {
        return folhaSalarial;
    }

    public int alocar(int count) {
        return alocados += count;
    }

    public int getAlocados() {
        return alocados;
    }

    public double getMediaSalarial(){
        if(alocados==0){
            return 0;
        }
        return folhaSalarial/alocados;
    }

    public static Departamento getDep(int indice) {
        return Departamento.getDepartamentos().get(indice);
    }



}

