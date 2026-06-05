package main.java;

import main.java.domain.Banco;
import main.java.domain.Cliente;
import main.java.domain.Conta;
import main.java.domain.ContaCorrente;
import main.java.domain.ContaPoupanca;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        try {

            Cliente kaio =
                    new Cliente("Kaio Silva");

            Conta cc =
                    new ContaCorrente(kaio);

            Conta poupanca =
                    new ContaPoupanca(kaio);

            Banco banco =
                    new Banco("DIO Bank");

            banco.adicionarConta(cc);
            banco.adicionarConta(poupanca);

            cc.depositar(new BigDecimal("1000"));

            cc.sacar(new BigDecimal("150"));

            cc.transferir(
                    new BigDecimal("300"),
                    poupanca);

            banco.listarContas();

            banco.buscarConta(1)
                    .ifPresent(Conta::imprimirExtrato);

        } catch (Exception e) {

            System.out.println(
                    "Erro: "
                            + e.getMessage());
        }
    }
}