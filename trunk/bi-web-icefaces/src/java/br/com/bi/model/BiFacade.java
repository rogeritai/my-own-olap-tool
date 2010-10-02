/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bi.model;

import br.com.bi.model.dao.CuboDao;
import br.com.bi.model.entity.metadata.Cubo;
import br.com.bi.model.entity.query.Consulta;
import br.com.bi.model.query.sql.SqlTranslator;
import java.util.List;

/**
 *
 * @author Luiz
 */
public class BiFacade {

    private static final String CUBO_DAO = "cuboDAO";
    private static BiFacade instance;

    private BiFacade() {
    }

    public static BiFacade getInstance() {
        if (instance == null) {
            instance = new BiFacade();
        }
        return instance;
    }

    public String traduzirParaSql(Consulta consulta) {
        SqlTranslator translator = new SqlTranslator();
        return translator.translateToSql(consulta);
    }

    // ============
    // === Cubo ===
    // ============
    /**
     * Salva no banco de dados o cubo informado.
     * @param cubo
     */
    public void salvarCubo(Cubo cubo) {
        getCuboDAO().salvar(cubo);
    }

    /**
     * Retorna referência para um cubo dado o seu identificador.
     * @param id
     * @return
     */
    public Cubo findCuboById(int id) {
        return getCuboDAO().findCuboById(id);
    }

    /**
     * Retorna referência para todos os cubos.
     * @return
     */
    public List<Cubo> findAllCubos() {
        return getCuboDAO().findAllCubos();
    }

    /**
     * Apagar um cubo do banco de dados.
     * @param id
     */
    public void apagarCubo(int id) {
        getCuboDAO().apagar(id);
    }

    // ================================
    private CuboDao getCuboDAO() {
        return (CuboDao) Application.getContext().getBean(CUBO_DAO);
    }
}
