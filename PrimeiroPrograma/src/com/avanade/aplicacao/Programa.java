package com.avanade.aplicacao;

import com.avanade.aplicacao.servicos.ServicoProcessarArquivos;

public class Programa {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Parâmetro caminho obrigatório");
            System.out.println("Uso: ");
            System.out.println("java com.avanade.aplicacao.Programa [CAMINHO]");
            System.exit(-1);
            return;
        }

        System.out.println("Iniciando aplicação..");
        Programa programa = new Programa();
        programa.iniciar(args[0]);
    }

    public void iniciar(String caminhoDirEntrada) {
        ServicoProcessarArquivos servico = new ServicoProcessarArquivos(caminhoDirEntrada);
        servico.executar();
    }
}
