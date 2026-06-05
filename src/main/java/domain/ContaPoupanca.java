package main.java.domain;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente titular) {
        super(titular);
    }

    @Override
    protected String getTipoConta() {
        return "CONTA POUPANÇA";
    }
}