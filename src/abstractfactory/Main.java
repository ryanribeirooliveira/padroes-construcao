package abstractfactory;

/**
 * Demonstracao do padrao Abstract Factory.
 *
 * Cenario real: ao contratar um plano de automacao residencial, o cliente
 * escolhe UM ecossistema. A partir dai, todos os dispositivos instalados
 * pertencem a mesma familia, sem risco de misturar marcas incompativeis.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== PADRAO ABSTRACT FACTORY - Automacao Residencial ===\n");

        System.out.println("[Cliente A escolheu o ecossistema SmartLife]");
        instalarCasa(new KitSmartLifeFactory());

        System.out.println("\n[Cliente B escolheu o ecossistema CasaConnect]");
        instalarCasa(new KitCasaConnectFactory());
    }

    /**
     * O metodo depende apenas da fabrica abstrata: nao conhece nenhuma marca.
     * Trocar o ecossistema e trocar a fabrica passada como argumento.
     */
    private static void instalarCasa(KitCasaInteligenteFactory fabrica) {
        LampadaInteligente lampada = fabrica.criarLampada();
        FechaduraInteligente fechadura = fabrica.criarFechadura();
        Termostato termostato = fabrica.criarTermostato();

        lampada.ligar();
        fechadura.trancar();
        termostato.ajustarTemperatura(23);
    }
}
