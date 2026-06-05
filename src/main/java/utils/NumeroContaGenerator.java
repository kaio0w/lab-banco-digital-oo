package main.java.utils;

public final class NumeroContaGenerator {

    private static int numero = 1;

    private NumeroContaGenerator() {
    }

    public static int gerar() {
        return numero++;
    }
}