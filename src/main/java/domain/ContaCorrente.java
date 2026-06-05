package main.java.domain;

public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente titular) {
        super(titular);
    }

    @Override
    protected String getTipoConta() {
        return "CONTA CORRENTE";
    }
}