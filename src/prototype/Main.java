package prototype;

/**
 * Demonstracao do padrao Prototype.
 *
 * Cenario real: um sistema juridico guarda um modelo-base de contrato de
 * trabalho (CLT) com clausulas fixas. Para cada novo funcionario, o modelo
 * e clonado e so os dados variaveis sao ajustados.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== PADRAO PROTOTYPE - Modelos de Contrato ===\n");

        // 1) Monta uma unica vez o modelo-base (custoso: varias clausulas).
        ContratoTrabalho modeloCLT = new ContratoTrabalho("Tech Solutions LTDA");
        modeloCLT.adicionarClausula("Jornada de 44 horas semanais.");
        modeloCLT.adicionarClausula("Vale-transporte e vale-refeicao inclusos.");
        modeloCLT.adicionarClausula("Periodo de experiencia de 90 dias.");

        // 2) Clona o modelo para o primeiro contratado.
        ContratoTrabalho contratoMaria = modeloCLT.clonar();
        contratoMaria.setContratado("Maria Oliveira");
        contratoMaria.setSalario(4500.00);
        contratoMaria.adicionarClausula("Cargo: Desenvolvedora Backend.");

        // 3) Clona novamente para outro contratado.
        ContratoTrabalho contratoJoao = modeloCLT.clonar();
        contratoJoao.setContratado("Joao Santos");
        contratoJoao.setSalario(3800.00);
        contratoJoao.adicionarClausula("Cargo: Analista de Suporte.");

        System.out.println(contratoMaria);
        System.out.println(contratoJoao);

        // Prova de que a copia e profunda: o modelo original permanece intacto.
        System.out.println("Modelo-base continua sem alteracoes:");
        System.out.println(modeloCLT);
    }
}
