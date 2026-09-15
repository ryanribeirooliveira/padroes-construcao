package abstractfactory;

/**
 * PRODUTOS CONCRETOS (Concrete Products).
 *
 * Duas familias completas de dispositivos, uma por fabricante.
 * Os produtos de uma familia sao pensados para funcionar juntos.
 */

/* ---------- Familia SmartLife ---------- */

class LampadaSmartLife implements LampadaInteligente {
    @Override
    public void ligar() {
        System.out.println("[SmartLife] Lampada acesa em tom quente (2700K).");
    }
}

class FechaduraSmartLife implements FechaduraInteligente {
    @Override
    public void trancar() {
        System.out.println("[SmartLife] Fechadura trancada por reconhecimento facial.");
    }
}

class TermostatoSmartLife implements Termostato {
    @Override
    public void ajustarTemperatura(int graus) {
        System.out.println("[SmartLife] Termostato ajustado para " + graus + "C via app.");
    }
}

/* ---------- Familia CasaConnect ---------- */

class LampadaCasaConnect implements LampadaInteligente {
    @Override
    public void ligar() {
        System.out.println("[CasaConnect] Lampada RGB acesa em azul.");
    }
}

class FechaduraCasaConnect implements FechaduraInteligente {
    @Override
    public void trancar() {
        System.out.println("[CasaConnect] Fechadura trancada por senha numerica.");
    }
}

class TermostatoCasaConnect implements Termostato {
    @Override
    public void ajustarTemperatura(int graus) {
        System.out.println("[CasaConnect] Termostato ajustado para " + graus + "C por comando de voz.");
    }
}
