package com.avanade.aplicacao.filtros;

import java.io.File;
import java.io.FilenameFilter;

public class FiltrarArquivoVenda implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        String nameUpper = name.toUpperCase();
        return nameUpper.matches("(VENDAS)-([0-9]){4}-([0-9]){2}-([0-9]){2}\\.(TXT)");
    }
}
