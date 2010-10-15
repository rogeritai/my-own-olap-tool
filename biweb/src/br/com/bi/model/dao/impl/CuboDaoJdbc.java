/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bi.model.dao.impl;


import br.com.bi.model.dao.CuboDao;
import br.com.bi.model.dao.DimensionDao;
import br.com.bi.model.entity.metadata.Cube;
import br.com.bi.model.entity.metadata.CubeLevel;
import br.com.bi.model.entity.metadata.Filter;
import br.com.bi.model.entity.metadata.Measure;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Luiz
 */
public class CuboDaoJdbc extends AbstractDaoJdbc implements CuboDao {
    private DimensionDao dimensionDao;

    /**
     * Retorna todos os cubos cadastrados (carga rasa).
     * @return
     */
    public List<Cube> findAll() {
        return getJdbcTemplate().query("select * from cubo order by nome",
                                       new CuboShallowMapper());
    }

    /**
     * Retorna um cubo dado o seu identificador (carga profunda).
     * @param id
     * @return
     */
    public Cube findById(int id) {
        return getJdbcTemplate().queryForObject("select * from cubo where id = ?",
                                                new Object[] { id },
                                                new CuboDeepMapper());
    }

    /**
     * Salva um cubo no banco de dados.
     * @param cubo
     */
    @Transactional
    public void save(Cube cubo) {
        Map<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("nome", cubo.getName());
        parameters.put("descricao", cubo.getDescription());
        parameters.put("esquema", cubo.getSchema());
        parameters.put("tabela", cubo.getTable());

        if (!cubo.isPersisted()) {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(getDataSource());
            insert.withTableName("cubo");
            insert.usingGeneratedKeyColumns("id");
            cubo.setId(insert.executeAndReturnKey(parameters).intValue());
        } else {
            parameters.put("id", cubo.getId());
            getSimpleJdbcTemplate().update("update cubo set nome = :nome, " +
                                           "descricao = :descricao, esquema = :esquema, tabela = :tabela " +
                                           "where id = :id", parameters);
        }

        salvarMetricas(cubo);
        salvarFiltros(cubo);
        salvarNiveis(cubo);
    }

    /**
     * Persiste as métricas de um cubo.
     * @param cubo
     */
    @Transactional
    private void salvarMetricas(Cube cubo) {
        List metricas = new ArrayList();

        for (Measure metrica : cubo.getMeasures()) {

            Map<String, Object> parameters = new HashMap<String, Object>();

            parameters.put("idcubo", cubo.getId());
            parameters.put("nome", metrica.getName());
            parameters.put("descricao", metrica.getDescription());
            parameters.put("funcao", metrica.getCodigoFuncao());
            parameters.put("coluna", metrica.getColumn());
            parameters.put("expressaoFiltro", metrica.getFilterExpression());
            parameters.put("metricaPadrao",
                           metrica.isDefaultMeasure() ? 1 : 0);

            if (!metrica.isPersisted()) {
                SimpleJdbcInsert insert =
                    new SimpleJdbcInsert(getDataSource());
                insert.withTableName("metrica").usingGeneratedKeyColumns("id");
                metrica.setId(insert.executeAndReturnKey(parameters).intValue());
            } else {
                parameters.put("id", metrica.getId());
                getSimpleJdbcTemplate().update("update metrica set nome = :nome, " +
                                               "descricao = :descricao, funcao = :funcao, coluna = :coluna," +
                                               "expressaoFiltro = :expressaoFiltro, metricaPadrao = :metricaPadrao " +
                                               "where id = :id", parameters);
            }

            metricas.add(metrica.getId());
        }

        if (metricas.size() > 0) {
            getJdbcTemplate().update("delete from metrica where not id in (:ids) and idcubo = :idcubo".replace(":ids",
                                                                                                               listToString(metricas)).replace(":idcubo",
                                                                                                                                               Integer.toString(cubo.getId())));
        }
    }

    /**
     * Persiste os filtros de um cubo.
     * @param cubo
     */
    @Transactional
    private void salvarFiltros(Cube cubo) {
        List filtros = new ArrayList();

        for (Filter filtro : cubo.getFilters()) {

            Map<String, Object> parameters = new HashMap<String, Object>();

            parameters.put("idcubo", cubo.getId());
            parameters.put("nome", filtro.getName());
            parameters.put("descricao", filtro.getDescription());
            parameters.put("expressao", filtro.getExpression());

            if (!filtro.isPersisted()) {
                SimpleJdbcInsert insert =
                    new SimpleJdbcInsert(getDataSource());
                insert.withTableName("filtro").usingGeneratedKeyColumns("id");
                filtro.setId(insert.executeAndReturnKey(parameters).intValue());
            } else {
                parameters.put("id", filtro.getId());
                getSimpleJdbcTemplate().update("update filtro set nome = :nome, " +
                                               "descricao = :descricao, expressao = :expressao where id = :id",
                                               parameters);
            }

            filtros.add(filtro.getId());
        }

        if (filtros.size() > 0) {
            getJdbcTemplate().update("delete from filtro where not id in (:ids) and idcubo = :idcubo".replace(":ids",
                                                                                                              listToString(filtros)).replace(":idcubo",
                                                                                                                                             Integer.toString(cubo.getId())));
        }
    }

    /**
     * Persiste os níveis de um cubo.
     * @param cubo
     */
    @Transactional
    private void salvarNiveis(Cube cubo) {
        List niveis = new ArrayList();

        for (CubeLevel nivel : cubo.getCubeLevels()) {

            Map<String, Object> parameters = new HashMap<String, Object>();

            parameters.put("idcubo", cubo.getId());
            parameters.put("idnivel", nivel.getLevel().getId());
            parameters.put("colunaJuncao", nivel.getJoinColumn());

            if (!nivel.isPersisted()) {
                SimpleJdbcInsert insert =
                    new SimpleJdbcInsert(getDataSource());
                insert.withTableName("cubo_nivel");
                insert.execute(parameters);
            } else {
                getSimpleJdbcTemplate().update("update cubo_nivel set colunaJuncao = :colunaJuncao" +
                                               " where idcubo = :idcubo and idnivel = :idnivel",
                                               parameters);
            }

            niveis.add(nivel.getLevel().getId());
        }

        if (niveis.size() > 0) {
            getJdbcTemplate().update("delete from cubo_nivel " +
                                     "where not idnivel in (:ids) and idcubo = :idcubo".replace(":ids",
                                                                                                listToString(niveis)).replace(":idcubo",
                                                                                                                              Integer.toString(cubo.getId())));
        }
    }

    /**
     * Apaga do banco de dados um cubo dado o seu identificador.
     * @param id
     */
    @Transactional
    public void delete(int id) {
        apagarFiltros(id);
        apagarMetricas(id);
        apagarNiveis(id);

        getJdbcTemplate().update("delete from cubo where id = ?",
                                 new Object[] { id });
    }

    private List<CubeLevel> findNiveisByCubo(int idCubo) {
        return getJdbcTemplate().query("select * from cubo_nivel where idcubo = ?",
                                       new Object[] { idCubo },
                                       new RowMapper<CubeLevel>() {

                public CubeLevel mapRow(ResultSet rs,
                                        int i) throws SQLException {
                    CubeLevel cubeLevel = new CubeLevel();
                    cubeLevel.setLevel(dimensionDao.findLevelById(rs.getInt("idnivel")));
                    cubeLevel.setJoinColumn(rs.getString("colunaJuncao"));
                    cubeLevel.setPersisted(true);

                    return cubeLevel;
                }
            });
    }

    /**
     * Retorna os filtros de um cubo dado seu identificador.
     * @param idCubo
     * @return
     */
    private List<Filter> findFiltrosByCubo(int idCubo) {
        return getJdbcTemplate().query("select * from filtro where idcubo = ?",
                                       new Object[] { idCubo },
                                       new RowMapper<Filter>() {

                public Filter mapRow(ResultSet rs, int i) throws SQLException {
                    Filter filter = new Filter();

                    filter.setDescription(rs.getString("descricao"));
                    filter.setExpression(rs.getString("expressao"));
                    filter.setId(rs.getInt("id"));
                    filter.setName(rs.getString("nome"));
                    filter.setPersisted(true);

                    return filter;
                }
            });
    }

    /**
     * Retorna as métricas de cubo dado o seu identificador.
     * @param idCubo
     * @return
     */
    private List<Measure> findMetricasByCubo(int idCubo) {
        return getJdbcTemplate().query("select * from metrica where idCubo = ?",
                                       new Object[] { idCubo },
                                       new RowMapper<Measure>() {

                public Measure mapRow(ResultSet rs,
                                      int i) throws SQLException {
                    Measure measure = new Measure();

                    measure.setColumn(rs.getString("coluna"));
                    measure.setDescription(rs.getString("descricao"));
                    measure.setFilterExpression(rs.getString("expressaoFiltro"));
                    measure.setCodigoFuncao(rs.getInt("funcao"));
                    measure.setId(rs.getInt("id"));
                    measure.setDefaultMeasure(rs.getInt("metricaPadrao") == 1);
                    measure.setName(rs.getString("nome"));
                    measure.setPersisted(true);
                    return measure;
                }
            });
    }

    /**
     * Apaga os filtros de um cubo.
     * @param cubo
     */
    @Transactional
    private void apagarFiltros(int idCubo) {
        getJdbcTemplate().update("delete from filtro where idcubo = ?",
                                 new Object[] { idCubo });
    }

    /**
     * Apaga as métricas de cubo.
     * @param cubo
     */
    @Transactional
    private void apagarMetricas(int idCubo) {
        getJdbcTemplate().update("delete from metrica where idcubo = ?",
                                 new Object[] { idCubo });
    }

    /**
     * Apaga os níveis de um cubo.
     * @param cubo
     */
    @Transactional
    private void apagarNiveis(int idCubo) {
        getJdbcTemplate().update("delete from cubo_nivel where idcubo = ?",
                                 new Object[] { idCubo });
    }

    public DimensionDao getDimensionDao() {
        return dimensionDao;
    }

    public void setDimensionDao(DimensionDao dimensionDao) {
        this.dimensionDao = dimensionDao;
    }

    class CuboShallowMapper implements RowMapper<Cube> {

        public Cube mapRow(ResultSet rs, int i) throws SQLException {
            Cube cubo = new Cube();
            cubo.setDescription(rs.getString("descricao"));
            cubo.setSchema(rs.getString("esquema"));
            cubo.setId(rs.getInt("id"));
            cubo.setName(rs.getString("nome"));
            cubo.setTable(rs.getString("tabela"));
            cubo.setPersisted(true);

            return cubo;
        }
    }

    class CuboDeepMapper extends CuboShallowMapper {

        @Override
        public Cube mapRow(ResultSet rs, int i) throws SQLException {
            Cube cubo = super.mapRow(rs, i);

            cubo.setCubeLevels(findNiveisByCubo(cubo.getId()));
            cubo.setFilters(findFiltrosByCubo(cubo.getId()));
            cubo.setMeasures(findMetricasByCubo(cubo.getId()));
            cubo.setPersisted(true);

            return cubo;
        }
    }
}
