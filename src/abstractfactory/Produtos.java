package abstractfactory;

/**
 * FAMILIA DE PRODUTOS (Abstract Products) do padrao Abstract Factory.
 *
 * Uma casa inteligente combina varios dispositivos que precisam ser do
 * MESMO ecossistema para conversarem entre si. Aqui definimos as
 * "interfaces" de cada tipo de dispositivo, independente do fabricante.
 */

interface LampadaInteligente {
    void ligar();
}

interface FechaduraInteligente {
    void trancar();
}

interface Termostato {
    void ajustarTemperatura(int graus);
}
