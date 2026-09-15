package memento;

/**
 * MEMENTO (Lembranca).
 *
 * Guarda um "retrato" do estado interno do editor em um dado momento.
 * E imutavel e so expoe o estado para o proprio originador, preservando
 * o encapsulamento (o Caretaker guarda o memento sem saber o que ha dentro).
 */
public final class SnapshotEditor {
    private final String conteudo;

    SnapshotEditor(String conteudo) {
        this.conteudo = conteudo;
    }

    String getConteudo() {
        return conteudo;
    }
}
