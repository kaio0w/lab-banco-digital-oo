package main.java.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Banco {

    private String nome;
    private List<Conta> contas = new ArrayList<>();

    public Banco(String nome) {
        this.nome = nome;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

   public Optional<Conta> buscarConta(int numero) {

    return contas.stream()
            .filter(conta -> conta.getNumero() == numero)
            .findFirst();
		}	
		
    public void listarContas() {

        System.out.println(
                "\n=== CONTAS DO BANCO "
                        + nome.toUpperCase()
                        + " ===");

        contas.forEach(conta ->

                System.out.println(
                        "Conta: "
                                + conta.getNumero()));
    }

    public List<Conta> getContas() {
        return contas;
    }

    public String getNome() {
        return nome;
    }
}