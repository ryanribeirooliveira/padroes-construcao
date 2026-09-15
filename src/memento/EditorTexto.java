package memento;

/**
 * ORIGINADOR (Originator).
 *
 * O objeto cujo estado queremos poder salvar e restaurar: um editor de texto.
 * Ele sabe criar um Memento com seu estado atual e sabe se restaurar a partir
 * de um Memento recebido.
 */
public class EditorTexto {
    private String conteudo = "";

    public void digitar(String texto) {
        conteudo += texto;
    }

    public String getConteudo() {
        return conteudo;
    }

    /** Cria um retrato do estado atual. */
    public SnapshotEditor salvar() {
        return new SnapshotEditor(conteudo);
    }

    /** Volta a um estado salvo anteriormente. */
    public void restaurar(SnapshotEditor snapshot) {
        this.conteudo = snapshot.getConteudo();
    }
}
