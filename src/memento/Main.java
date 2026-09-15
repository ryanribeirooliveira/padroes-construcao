package memento;

/**
 * Demonstracao do padrao Memento.
 *
 * Cenario real: a funcao "desfazer" (Ctrl+Z) de um editor de texto. Antes de
 * cada alteracao importante o editor salva um retrato do seu estado; ao
 * desfazer, ele volta ao ultimo retrato guardado.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== PADRAO MEMENTO - Editor com Desfazer (Ctrl+Z) ===\n");

        EditorTexto editor = new EditorTexto();
        HistoricoEditor historico = new HistoricoEditor();

        editor.digitar("Ola");
        historico.guardar(editor.salvar());       // ponto de restauracao 1
        System.out.println("Texto atual: \"" + editor.getConteudo() + "\"");

        editor.digitar(", mundo");
        historico.guardar(editor.salvar());       // ponto de restauracao 2
        System.out.println("Texto atual: \"" + editor.getConteudo() + "\"");

        editor.digitar(" e universo!");
        System.out.println("Texto atual: \"" + editor.getConteudo() + "\"");

        System.out.println("\n>> Desfazendo a ultima digitacao...");
        editor.restaurar(historico.desfazer());
        System.out.println("Texto atual: \"" + editor.getConteudo() + "\"");

        System.out.println(">> Desfazendo mais uma vez...");
        editor.restaurar(historico.desfazer());
        System.out.println("Texto atual: \"" + editor.getConteudo() + "\"");
    }
}
