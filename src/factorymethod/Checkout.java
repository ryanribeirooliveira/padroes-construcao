package factorymethod;

/**
 * CREATOR (Criador) do padrao Factory Method.
 *
 * Declara o "factory method" abstrato criarPagamento(). A logica comum de
 * finalizar a compra fica na classe base; a decisao de QUAL objeto de
 * pagamento instanciar e delegada as subclasses.
 */
abstract class Checkout {

    /** Factory Method: cada subclasse decide o produto concreto. */
    protected abstract Pagamento criarPagamento();

    /** Regra de negocio comum que usa o produto sem saber seu tipo concreto. */
    public void finalizarCompra(double valor) {
        Pagamento pagamento = criarPagamento();
        System.out.print("Finalizando compra -> ");
        pagamento.processar(valor);
    }
}

/** CREATORS CONCRETOS: cada um "sabe" fabricar seu proprio pagamento. */

class CheckoutPix extends Checkout {
    @Override
    protected Pagamento criarPagamento() {
        return new PagamentoPix();
    }
}

class CheckoutCartao extends Checkout {
    @Override
    protected Pagamento criarPagamento() {
        return new PagamentoCartaoCredito();
    }
}

class CheckoutBoleto extends Checkout {
    @Override
    protected Pagamento criarPagamento() {
        return new PagamentoBoleto();
    }
}
