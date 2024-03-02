import java.util.ArrayList;

public class Departamento {
    private String nome;
    private static ArrayList<Departamento> departamentos = new ArrayList<>();

    public Departamento(String nome) {
        this.nome = nome;
    }

    public static void inicializarDepartamentos() {
        departamentos.add(new Departamento("TI"));
        departamentos.add(new Departamento("Marketing"));
        departamentos.add(new Departamento("RH"));
        departamentos.add(new Departamento("Gerência"));
        departamentos.add(new Departamento("Serviços Gerais"));
    }

    public static ArrayList<Departamento> getDepartamentos() {
        return departamentos;
    }

    public String getNome() {
        return nome;
    }

    public static Departamento getDep(int indice) {
        return Departamento.getDepartamentos().get(indice);
    }

}

