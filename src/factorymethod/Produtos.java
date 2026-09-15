package factorymethod;

/**
 * PRODUTOS CONCRETOS (Concrete Products).
 * Cada classe implementa a regra de negocio de um meio de pagamento.
 * Agrupadas em um arquivo apenas para facilitar a leitura do trabalho.
 */

class PagamentoPix implements Pagamento {
    @Override
    public void processar(double valor) {
        System.out.printf("PIX: gerando QR Code e confirmando R$ %.2f na hora.%n", valor);
    }
}

class PagamentoCartaoCredito implements Pagamento {
    @Override
    public void processar(double valor) {
        double comValor = valor * 1.05; // 5% de juros da operadora
        System.out.printf("CARTAO: autorizando R$ %.2f (com 5%% de taxa = R$ %.2f).%n",
                valor, comValor);
    }
}

class PagamentoBoleto implements Pagamento {
    @Override
    public void processar(double valor) {
        System.out.printf("BOLETO: emitindo boleto de R$ %.2f com vencimento em 3 dias.%n", valor);
    }
}
