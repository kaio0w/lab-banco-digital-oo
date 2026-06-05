package main.java.domain;

import main.java.enums.TipoTransacao;
import main.java.exceptions.SaldoInsuficienteException;
import main.java.exceptions.ValorInvalidoException;
import main.java.utils.NumeroContaGenerator;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class Conta implements IConta {

    protected static final int AGENCIA_PADRAO = 1;

    protected int agencia;
    protected int numero;
    protected BigDecimal saldo;

    protected Cliente titular;

    protected List<Transacao> historico;

    public Conta(Cliente titular) {

        this.agencia = AGENCIA_PADRAO;
        this.numero = NumeroContaGenerator.gerar();
        this.titular = titular;
        this.saldo = BigDecimal.ZERO;
        this.historico = new ArrayList<>();
    }

    @Override
    public void depositar(BigDecimal valor) {

        validarValor(valor);

        saldo = saldo.add(valor);

        historico.add(
                new Transacao(
                        TipoTransacao.DEPOSITO,
                        valor,
                        "Depósito realizado"));
    }

    @Override
    public void sacar(BigDecimal valor) {

        validarValor(valor);

        if (saldo.compareTo(valor) < 0) {
            throw new SaldoInsuficienteException(
                    "Saldo insuficiente.");
        }

        saldo = saldo.subtract(valor);

        historico.add(
                new Transacao(
                        TipoTransacao.SAQUE,
                        valor,
                        "Saque realizado"));
    }

    @Override
    public void transferir(
            BigDecimal valor,
            Conta destino) {

        sacar(valor);

        destino.depositar(valor);

        historico.add(
                new Transacao(
                        TipoTransacao.TRANSFERENCIA_ENVIADA,
                        valor,
                        "Transferência para conta "
                                + destino.numero));

        destino.historico.add(
                new Transacao(
                        TipoTransacao.TRANSFERENCIA_RECEBIDA,
                        valor,
                        "Transferência recebida da conta "
                                + numero));
    }

    private void validarValor(BigDecimal valor) {

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException(
                    "O valor deve ser maior que zero.");
        }
    }

    protected abstract String getTipoConta();

    @Override
    public void imprimirExtrato() {

        NumberFormat moeda =
                NumberFormat.getCurrencyInstance(
                        new Locale("pt", "BR"));

        System.out.println(
                "\n=== EXTRATO "
                        + getTipoConta()
                        + " ===");

        System.out.println(
                "Titular: "
                        + titular.getNome());

        System.out.println(
                "Agência: "
                        + agencia);

        System.out.println(
                "Conta: "
                        + numero);

        System.out.println(
                "Saldo: "
                        + moeda.format(saldo));

        System.out.println("\nMovimentações:");

        historico.forEach(t ->
                System.out.println(
                        t.getDataHora()
                                + " | "
                                + t.getTipo()
                                + " | "
                                + moeda.format(t.getValor())
                                + " | "
                                + t.getDescricao()));
    }

    public int getNumero(){
        return numero;
    }
}