package com.avanade.aplicacao.servicos;

import com.avanade.aplicacao.validacoes.ValidarArquivos;

import java.nio.file.Path;
import java.util.List;

public class ServicoProcessarArquivos {

    private final Path dirEntrada;
    private final List<String> lstArquivos;

    public ServicoProcessarArquivos(String caminhoDirEntrada) {
        ValidarArquivos validacao = new ValidarArquivos();
        dirEntrada = validacao.validarDirEntrada(caminhoDirEntrada);
        lstArquivos = validacao.getLstArquivos();
    }


    public void executar() {
        if (lstArquivos.isEmpty()) {
            System.out.println("Não há arquivos para processamento!");
            return;
        }

        for (String arquivo : lstArquivos) {
            System.out.println("Processando arquivo: " + arquivo);

        }


    }
}
