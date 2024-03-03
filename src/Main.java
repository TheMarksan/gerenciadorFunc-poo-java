import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Departamento.inicializarDepartamentos();

        System.out.println(" ----------------------------------");
        System.out.println("| Bem-vindo à Empresa Não sei o que|");
        System.out.println(" ----------------------------------");

        int opcao;
        do {
            System.out.println("1. Adicionar Funcionário");
            System.out.println("2. Consultar Funcionário");
            System.out.println("3. Demitir Funcionário");
            System.out.println("4. Consultar Salários");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    addFuncionario();
                    break;
                case 2:
                    consultarFuncionarios();
                    break;
                case 3:
                    demitirFuncionário();
                    break;
                case 4:
                    consultarSalarios();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public static int gerarMatricula(ArrayList<Funcionario> funcionarios) {
        Random random = new Random();
        int novaMatricula;
        boolean matriculaExistente;

        do {
            novaMatricula = 100000 + random.nextInt(900000);
            matriculaExistente = false;

            for (Funcionario funcionario : funcionarios) {
                if (funcionario.getMatricula() == novaMatricula) {
                    matriculaExistente = true;
                    break;
                }
            }
        } while (matriculaExistente);

        return novaMatricula;
    }

    private static void addFuncionario() {
        scanner.nextLine();
        System.out.print("Digite o nome do Funcionário: ");
        String name = scanner.nextLine();
        System.out.print("Digite o CPF do Funcionário: ");
        String cpf = scanner.nextLine();
        while(!validarCPF(cpf)){
            System.out.println("Formato de CPF inválido. Tente novamente:");
            cpf = scanner.nextLine();
        }
        System.out.print("Digite o salário do Funcionário: ");
        double salario = scanner.nextDouble();
        while(salario<1412){
            System.out.println("Você está praticando a mais-valia e descumprindo as leis trabalhistas. Digite um valor igual ou superior ao salário mínimo atual:");
            salario = scanner.nextDouble();
        }
        System.out.print("Gerando nova matrícula...\n");
        int matricula = gerarMatricula(funcionarios);
        System.out.println("O número de matrícula do funcionário " + name + " é " + matricula);
        System.out.println("Em qual departamento ele será alocado?");

        for (int i = 0; i < Departamento.getDepartamentos().size(); i++) {
            System.out.println(i + ". " + Departamento.getDepartamentos().get(i).getNome());
        }
        int index = scanner.nextInt();
        Departamento departamento = Departamento.getDep(index);

        Funcionario funcionario = new Funcionario(name, cpf, salario, matricula, departamento);
        funcionario.getDep().incrementFolha(salario);
        funcionarios.add(funcionario);
        System.out.println("\nFuncionário(a) adicionado no sistema!");
    }

    private static void consultarFuncionarios() {
        scanner.nextLine();
        System.out.println("1. Todos os funcionários alocados");
        System.out.println("2. Funcionários por departamento");
        System.out.println("3. Pela matrícula");
        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                System.out.println("\n===== Funcionários da Empresa Não sei o que =====");
                System.out.println();
                for (int i = 0; i < Departamento.getDepartamentos().size(); i++) {
                    Departamento departamento = Departamento.getDep(i);
                    System.out.println("\n----- Departamento de "+departamento.getNome()+" -----");
                    System.out.println();
                    boolean achou = false;
                    for (int j = 0; j < funcionarios.size(); j++) {
                        Funcionario funcionario = funcionarios.get(j);
                        if(funcionario.getDep().getNome().equals(departamento.getNome())){
                            System.out.println((j + 1) + ". " + "Nome: " + funcionario.getNome() + "\n - CPF: " + funcionario.getCPF() + "\n - Matrícula: " + funcionario.getMatricula() + "\n - Salário: R$ " + funcionario.showSalario() + "\n");
                            achou = true;
                        }
                    }
                    if(!achou){
                        System.out.println("- Nenhum funcionário alocado no departamento");
                        achou = false;
                    }
                }

                break;
            case 2:
                if (funcionarios.isEmpty()) {
                    System.out.println("Pague seu DAS MEI.\n");
                } else {
                    System.out.println("Filtrar por qual departamento?");
                    for (int i = 0; i < Departamento.getDepartamentos().size(); i++) {
                        System.out.println(i + ". " + Departamento.getDepartamentos().get(i).getNome());
                    }
                    int index = scanner.nextInt();
                    Departamento departamento = Departamento.getDep(index);
                    System.out.println("\n===== Funcionários do departamento de " + departamento.getNome() + " =====");
                    for (int i = 0; i < funcionarios.size(); i++) {
                        Funcionario funcionario = funcionarios.get(i);
                        if (funcionario.getDep().getNome().equals(departamento.getNome())) {
                            System.out.println((i + 1) + ". " + "Nome: " + funcionario.getNome() + "\n - Departamento: " + funcionario.getDep().getNome() + "\n - CPF: " + funcionario.getCPF() + "\n - Matrícula: " + funcionario.getMatricula() + "\n - Salário: R$ " + funcionario.showSalario() + "\n");
                        }
                    }
                }
                break;
            case 3:
                System.out.println("Digite o número da matrícula:");
                int nMatricula = scanner.nextInt();
                boolean achou = true;
                String matriculaString = Integer.toString(nMatricula);
                while(matriculaString.length()!=6){
                    System.out.println("Valor inválido, a matrícula possui 6 caracteres. Tente novamente: ");
                    nMatricula = scanner.nextInt();
                    matriculaString = Integer.toString(nMatricula);
                }
                for (int i = 0; i < funcionarios.size(); i++) {
                    Funcionario funcionario = funcionarios.get(i);
                    if (funcionario.getMatricula()==nMatricula) {
                        System.out.println((i + 1) + ". " + "Nome: " + funcionario.getNome() + "\n - Departamento: " + funcionario.getDep().getNome() + "\n - CPF: " + funcionario.getCPF() + "\n - Salário: R$ " + funcionario.showSalario() + "\n");
                    }else{
                        achou = false;
                    }
                }

                if(!achou){
                    System.out.println("Não existe funcionário com essa matrícula.");
                }

                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void demitirFuncionário() {
        //scanner.nextLine();
        for (int i = 0; i < Departamento.getDepartamentos().size(); i++) {
            Departamento departamento = Departamento.getDep(i);
            System.out.println("\n----- Departamento de "+departamento.getNome()+" -----");
            System.out.println();
            boolean achou = false;
            for (int j = 0; j < funcionarios.size(); j++) {
                Funcionario funcionario = funcionarios.get(j);
                if(funcionario.getDep().getNome().equals(departamento.getNome())){
                    System.out.println((j + 1) + ". " + "Nome: " + funcionario.getNome() + "\n - CPF: " + funcionario.getCPF() + "\n - Matrícula: " + funcionario.getMatricula() + "\n - Salário: R$ " + funcionario.showSalario() + "\n");
                    achou = true;
                }
            }
            if(!achou){
                System.out.println("- Nenhum funcionário alocado no departamento");
                achou = false;
            }

        }

        System.out.println("Digite o número da matrícula:");
        int nMatricula = scanner.nextInt();
        boolean achou = false;
        String matriculaString = Integer.toString(nMatricula);
        while(matriculaString.length()!=6){
            System.out.println("Valor inválido, a matrícula possui 6 caracteres. Tente novamente: ");
            nMatricula = scanner.nextInt();
            matriculaString = Integer.toString(nMatricula);
        }
        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionario funcionario = funcionarios.get(i);
            if (funcionario.getMatricula()==nMatricula) {
                funcionario.getDep().incrementFolha(funcionario.showSalario()*(-1));
                funcionarios.remove(i);
                System.out.println("Demitido >por justa causa< com sucesso!");
                System.out.println();
                achou = true;
            }
        }

        if(!achou){
            System.out.println("Não existe funcionário com essa matrícula.");
        }


    }
    private static void consultarSalarios() {
        //scanner.nextLine();
        System.out.println("Folha de qual departamento?");
        for (int i = 0; i < Departamento.getDepartamentos().size(); i++) {
            System.out.println(i + ". " + Departamento.getDepartamentos().get(i).getNome());
        }
        int index = scanner.nextInt();
        Departamento departamento = Departamento.getDep(index);
        System.out.println("\n===== Folha salarial mensal do departamento de " + departamento.getNome() + " =====");
        System.out.println("Total: R$ "+departamento.getFolhaSalarial());
        System.out.println();
    }
    public static boolean validarCPF(String cpf) {
        String regex = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";

        return cpf.matches(regex);
    }
}
