package memento;

import java.util.Deque;
import java.util.ArrayDeque;

/**
 * CARETAKER (Zelador/Cuidador).
 *
 * Responsavel por guardar os mementos em uma pilha e devolve-los quando o
 * usuario pede "desfazer". Ele NAO acessa o conteudo do memento: apenas o
 * armazena e o repassa, respeitando o encapsulamento do originador.
 */
public class HistoricoEditor {
    private final Deque<SnapshotEditor> pilha = new ArrayDeque<>();

    public void guardar(SnapshotEditor snapshot) {
        pilha.push(snapshot);
    }

    /** Devolve o ultimo estado salvo (ou null se nao houver historico). */
    public SnapshotEditor desfazer() {
        return pilha.isEmpty() ? null : pilha.pop();
    }
}
