package com.avanade.aplicacao;

import com.avanade.aplicacao.servicos.ServicoProcessarArquivos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Programa {

    private static final Logger LOG = LoggerFactory.getLogger(Programa.class);

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Parâmetro caminho obrigatório");
            System.out.println("Uso: ");
            System.out.println("java com.avanade.aplicacao.Programa [CAMINHO]");
            System.exit(-1);
            return;
        }

        LOG.info("Iniciando aplicação..");
        Programa programa = new Programa();
        programa.iniciar(args[0]);
    }

    public void iniciar(String caminhoDirEntrada) {
        ServicoProcessarArquivos servico = new ServicoProcessarArquivos(caminhoDirEntrada);
        servico.executar();
    }
}
