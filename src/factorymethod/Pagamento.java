package factorymethod;

/**
 * PRODUTO (Product) do padrao Factory Method.
 *
 * Cada forma de pagamento (PIX, cartao, boleto) implementa esta interface.
 * O codigo cliente trabalha sempre com o tipo 'Pagamento', sem conhecer a
 * classe concreta que foi criada.
 */
public interface Pagamento {
    void processar(double valor);
}
