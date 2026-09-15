package abstractfactory;

/**
 * ABSTRACT FACTORY (Fabrica Abstrata).
 *
 * Declara metodos para criar CADA produto da familia. Uma implementacao
 * concreta desta fabrica sempre devolve dispositivos do mesmo ecossistema,
 * garantindo compatibilidade entre eles.
 */
interface KitCasaInteligenteFactory {
    LampadaInteligente criarLampada();
    FechaduraInteligente criarFechadura();
    Termostato criarTermostato();
}

/** FABRICA CONCRETA: monta um kit 100% SmartLife. */
class KitSmartLifeFactory implements KitCasaInteligenteFactory {
    @Override
    public LampadaInteligente criarLampada() {
        return new LampadaSmartLife();
    }
    @Override
    public FechaduraInteligente criarFechadura() {
        return new FechaduraSmartLife();
    }
    @Override
    public Termostato criarTermostato() {
        return new TermostatoSmartLife();
    }
}

/** FABRICA CONCRETA: monta um kit 100% CasaConnect. */
class KitCasaConnectFactory implements KitCasaInteligenteFactory {
    @Override
    public LampadaInteligente criarLampada() {
        return new LampadaCasaConnect();
    }
    @Override
    public FechaduraInteligente criarFechadura() {
        return new FechaduraCasaConnect();
    }
    @Override
    public Termostato criarTermostato() {
        return new TermostatoCasaConnect();
    }
}
