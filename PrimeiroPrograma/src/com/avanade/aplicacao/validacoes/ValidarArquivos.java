package com.avanade.aplicacao.validacoes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ValidarArquivos {

    private List<String> lstArquivos;

    private void mensagemValidacao(String caminhoDirEntrada, String mensagem)  {
        String modeloMensagem = mensagem + " [{0}]";
        System.err.println(MessageFormat.format(modeloMensagem, caminhoDirEntrada));
        System.exit(-2);
    }

    public Path validarDirEntrada(String caminhoDirEntrada) {
        System.out.println("Validação do diretório de entrada...");

        Path dirEntrada = Paths.get(caminhoDirEntrada);
        if (!Files.exists(dirEntrada)) {
            mensagemValidacao(caminhoDirEntrada, "Diretório informado não existe");
            return null;
        }

        if (!Files.isDirectory(dirEntrada)) {
            mensagemValidacao(caminhoDirEntrada, "Caminho informado não é um diretório");
            return null;
        }

        File fDirEntra = dirEntrada.toFile();
        String[] arquivos = fDirEntra.list((dir, name) -> name.toUpperCase().matches(
                "(VENDAS)-([0-9]){4}-([0-9]){2}-([0-9]){2}\\.(TXT)"));

        if (arquivos == null || arquivos.length == 0) {
            mensagemValidacao(caminhoDirEntrada, "Não há arquivos no diretório informado");
            return null;
        }

        lstArquivos = Arrays.asList(arquivos);

        System.out.println("Diretório validado com sucesso " + caminhoDirEntrada );
        return dirEntrada;
    }

    public List<String> getLstArquivos() {
        if (lstArquivos == null) {
            lstArquivos = Collections.emptyList();
        }
        return lstArquivos;
    }
}
