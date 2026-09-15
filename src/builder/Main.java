package builder;

/**
 * Demonstracao do padrao Builder.
 *
 * Cenario real: loja de e-commerce onde o cliente monta um PC escolhendo
 * pecas obrigatorias e adicionais. O mesmo processo de construcao gera
 * configuracoes bem diferentes de forma legivel.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== PADRAO BUILDER - Montador de Computador ===\n");

        // PC basico para escritorio: so o obrigatorio.
        Computador escritorio = new Computador.Builder("Intel Core i3", 8, 256)
                .construir();

        // PC gamer: encadeando os opcionais na ordem que quiser.
        Computador gamer = new Computador.Builder("AMD Ryzen 7", 32, 1000)
                .comPlacaVideo("NVIDIA RTX 4070")
                .comRefrigeracaoLiquida()
                .comWifi()
                .comGarantia(3)
                .construir();

        System.out.println("[Configuracao 1 - Escritorio]");
        System.out.println(escritorio);
        System.out.println("\n[Configuracao 2 - Gamer]");
        System.out.println(gamer);
    }
}
