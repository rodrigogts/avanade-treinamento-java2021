package com.avanade.aplicacao.servicos;

import com.avanade.aplicacao.dao.PedidoDao;
import com.avanade.aplicacao.model.PedidoModel;
import com.avanade.aplicacao.validacoes.ValidarArquivos;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ServicoProcessarArquivos {

    private final PedidoDao pedidoDao;
    private final Path dirEntrada;
    private final List<String> lstArquivos;

    public ServicoProcessarArquivos(String caminhoDirEntrada) {
        ValidarArquivos validacao = new ValidarArquivos();
        dirEntrada = validacao.validarDirEntrada(caminhoDirEntrada);
        lstArquivos = validacao.getLstArquivos();
        try {
            pedidoDao = new PedidoDao();
        } catch (SQLException ex) {
            String msg = "Ocorreu um erro ao criar DAO de pedidos";
            log.error(msg, ex);
            throw new RuntimeException(msg, ex);
        }
    }

    public void executar() {
        if (lstArquivos.isEmpty()) {
            log.info("Não há arquivos para processamento!");
            return;
        }

        ServicoLerArquivo servicoLerArquivo = new ServicoLerArquivo();
        lstArquivos.forEach((arquivo) -> {
            String caminhoArquivo = dirEntrada.toString() + File.separator + arquivo;
            log.info("Processando arquivo: {}", caminhoArquivo);

            List<PedidoModel> pedidos = servicoLerArquivo.executar(caminhoArquivo);
            if (pedidos.isEmpty()) {
                log.info("Não há registros no arquivo {}", caminhoArquivo);
                return;
            }

            log.info("Encontrados [{}] pedidos no arquivo {}", pedidos.size(), caminhoArquivo);

            // TODO Gravar no banco de dados
        });

        try {
            Optional<PedidoModel> pedidos = pedidoDao.buscaPorCodigo(5877);;
            System.out.println(pedidos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
