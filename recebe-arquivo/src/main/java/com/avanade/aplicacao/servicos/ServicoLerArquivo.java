package com.avanade.aplicacao.servicos;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

public class ServicoLerArquivo {

    private static final Logger LOG = LoggerFactory.getLogger(ServicoLerArquivo.class);

    public List<String> executar(String caminhoArquivo) {
        try {
            File arquivo = new File(caminhoArquivo);
            return FileUtils.readLines(arquivo, Charset.defaultCharset());
        } catch (IOException ex) {
            LOG.error("Falha ao ler arquivo [{}]", caminhoArquivo, ex);
            return Collections.emptyList();
        }
    }

}
