package builder;

/**
 * PRODUTO (Product) do padrao Builder.
 *
 * Representa um computador montado sob medida em uma loja de e-commerce.
 * O objeto e IMUTAVEL: todos os campos sao 'final' e nao ha setters.
 * A unica forma de criar um Computador e atraves do seu Builder interno,
 * o que evita construtores gigantes ("telescoping constructors") e deixa
 * claro no codigo o que cada valor significa.
 */
public class Computador {

    // Componentes obrigatorios
    private final String processador;
    private final int memoriaRamGb;
    private final int armazenamentoSsdGb;

    // Componentes opcionais
    private final String placaVideo;
    private final boolean wifi;
    private final boolean refrigeracaoLiquida;
    private final int garantiaAnos;

    // Construtor PRIVADO: so o Builder consegue chamar.
    private Computador(Builder builder) {
        this.processador = builder.processador;
        this.memoriaRamGb = builder.memoriaRamGb;
        this.armazenamentoSsdGb = builder.armazenamentoSsdGb;
        this.placaVideo = builder.placaVideo;
        this.wifi = builder.wifi;
        this.refrigeracaoLiquida = builder.refrigeracaoLiquida;
        this.garantiaAnos = builder.garantiaAnos;
    }

    @Override
    public String toString() {
        return "Computador montado:\n"
                + "  - Processador............: " + processador + "\n"
                + "  - Memoria RAM............: " + memoriaRamGb + " GB\n"
                + "  - Armazenamento SSD......: " + armazenamentoSsdGb + " GB\n"
                + "  - Placa de video.........: " + placaVideo + "\n"
                + "  - Wi-Fi..................: " + (wifi ? "Sim" : "Nao") + "\n"
                + "  - Refrigeracao liquida...: " + (refrigeracaoLiquida ? "Sim" : "Nao") + "\n"
                + "  - Garantia...............: " + garantiaAnos + " ano(s)";
    }

    /**
     * BUILDER (classe estatica aninhada).
     *
     * Recebe no construtor apenas os itens obrigatorios e expoe metodos
     * encadeaveis (retornam 'this') para os itens opcionais. O metodo
     * construir() valida e devolve o produto final.
     */
    public static class Builder {
        private final String processador;
        private final int memoriaRamGb;
        private final int armazenamentoSsdGb;

        // Valores padrao dos opcionais
        private String placaVideo = "Video integrado";
        private boolean wifi = false;
        private boolean refrigeracaoLiquida = false;
        private int garantiaAnos = 1;

        public Builder(String processador, int memoriaRamGb, int armazenamentoSsdGb) {
            this.processador = processador;
            this.memoriaRamGb = memoriaRamGb;
            this.armazenamentoSsdGb = armazenamentoSsdGb;
        }

        public Builder comPlacaVideo(String modelo) {
            this.placaVideo = modelo;
            return this;
        }

        public Builder comWifi() {
            this.wifi = true;
            return this;
        }

        public Builder comRefrigeracaoLiquida() {
            this.refrigeracaoLiquida = true;
            return this;
        }

        public Builder comGarantia(int anos) {
            this.garantiaAnos = anos;
            return this;
        }

        public Computador construir() {
            if (memoriaRamGb <= 0 || armazenamentoSsdGb <= 0) {
                throw new IllegalStateException(
                        "Memoria e armazenamento devem ser maiores que zero.");
            }
            return new Computador(this);
        }
    }
}
