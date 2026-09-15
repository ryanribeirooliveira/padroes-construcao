package factorymethod;

/**
 * Demonstracao do padrao Factory Method.
 *
 * Cenario real: no checkout de uma loja, o cliente escolhe o meio de
 * pagamento. Cada escolha ativa um "creator" diferente, mas o restante
 * do fluxo de compra permanece identico.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== PADRAO FACTORY METHOD - Sistema de Pagamentos ===\n");

        Checkout comPix = new CheckoutPix();
        Checkout comCartao = new CheckoutCartao();
        Checkout comBoleto = new CheckoutBoleto();

        comPix.finalizarCompra(150.00);
        comCartao.finalizarCompra(150.00);
        comBoleto.finalizarCompra(150.00);
    }
}
