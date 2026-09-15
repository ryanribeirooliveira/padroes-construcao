package prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * PROTOTIPO CONCRETO (Concrete Prototype).
 *
 * Um escritorio de advocacia mantem modelos-padrao de contrato. Em vez de
 * redigir cada novo contrato do zero, ele CLONA um modelo pronto (com todas
 * as clausulas ja escritas) e apenas personaliza os dados do cliente.
 *
 * A clonagem e PROFUNDA (deep copy): a lista de clausulas do clone e uma
 * nova lista, para que alterar o clone nao afete o modelo original.
 */
public class ContratoTrabalho implements Prototipo<ContratoTrabalho> {

    private String contratante;
    private String contratado;
    private double salario;
    private final List<String> clausulas;

    public ContratoTrabalho(String contratante) {
        this.contratante = contratante;
        this.contratado = "(a preencher)";
        this.salario = 0.0;
        this.clausulas = new ArrayList<>();
    }

    /** Construtor de copia usado internamente pela clonagem. */
    private ContratoTrabalho(ContratoTrabalho original) {
        this.contratante = original.contratante;
        this.contratado = original.contratado;
        this.salario = original.salario;
        // COPIA PROFUNDA: nova lista com os mesmos elementos.
        this.clausulas = new ArrayList<>(original.clausulas);
    }

    @Override
    public ContratoTrabalho clonar() {
        return new ContratoTrabalho(this);
    }

    public void adicionarClausula(String texto) {
        clausulas.add(texto);
    }

    public void setContratado(String contratado) {
        this.contratado = contratado;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Contrato de Trabalho\n");
        sb.append("  Contratante: ").append(contratante).append("\n");
        sb.append("  Contratado.: ").append(contratado).append("\n");
        sb.append(String.format("  Salario....: R$ %.2f%n", salario));
        sb.append("  Clausulas..:\n");
        for (String c : clausulas) {
            sb.append("     - ").append(c).append("\n");
        }
        return sb.toString();
    }
}
