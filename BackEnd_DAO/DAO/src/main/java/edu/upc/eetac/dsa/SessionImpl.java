package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.util.ObjectHelper;
import edu.upc.eetac.dsa.util.QueryHelper;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public void save(Object entity) {
        try{
            String insertQuery = QueryHelper.createQueryINSERT(entity);

            PreparedStatement statement = conn.prepareStatement(insertQuery);
            int i = 1;

            for(String field: ObjectHelper.getFields(entity)) {
                statement.setObject(i++, ObjectHelper.getter(entity, field));
            }
            statement.executeQuery();
        } catch (SQLException | NoSuchFieldException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        conn.close();
    }

    @Override
    public Object get(Class theClass, String id) {
        Object entity;
        try {
            entity = theClass.newInstance();
            ObjectHelper.setter(entity, ObjectHelper.getIdAttributeName(theClass), id);
            String selectQuery = QueryHelper.createQuerySELECT(entity);

            PreparedStatement statement = conn.prepareStatement(selectQuery);
            statement.setObject(1, id);
            entity = ObjectHelper.createObjects(statement.executeQuery(), theClass).get(0);
            assert entity != null;

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }

    @Override
    public List<Object> getElementos(Class theClass, String parametro, String valor){

        String selectElementosQuery = QueryHelper.createQuerySELECTElementos(theClass,parametro);
        PreparedStatement statement;
        List<Object> lista = null;
        try{
            statement = conn.prepareStatement(selectElementosQuery);
            statement.setObject(1, valor);
            lista = ObjectHelper.createObjects(statement.executeQuery(), theClass);

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public void update(Object object) {
        try{
            String updateQuery = QueryHelper.createQueryUPDATE(object);
            PreparedStatement statement = conn.prepareStatement(updateQuery);
            int i = 1;

            for(String field: ObjectHelper.getFields(object)) {
                statement.setObject(i++, ObjectHelper.getter(object, field));
            }
            statement.setObject(i, ObjectHelper.getter(object, ObjectHelper.getIdAttributeName(object.getClass())));

            statement.executeQuery();
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Object object) {
        try{
            String updateQuery = QueryHelper.createQueryDELETE(object);
            PreparedStatement statement = conn.prepareStatement(updateQuery);
            statement.setObject(1, ObjectHelper.getter(object, ObjectHelper.getIdAttributeName(object.getClass())));

            statement.executeQuery();
        } catch (SQLException | NoSuchFieldException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Object> findAll(Class theClass) {
        String selectQuery = QueryHelper.createQuerySelectAll(theClass);
        PreparedStatement statement;
        List<Object> objects = null;


        try{
            statement = conn.prepareStatement(selectQuery);
            objects = ObjectHelper.createObjects(statement.executeQuery(), theClass);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException |
                 NoSuchFieldException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return objects;
    }

    @Override
    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }

    @Override
    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }

    @Override
    public void deleteRecords(Class theClass) {
        String selectQuery = QueryHelper.createQueryDeleteRecords(theClass);

        try{
            PreparedStatement statement = conn.prepareStatement(selectQuery);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateObjetoArmario(int cantidad, String pouId, String idArticulo) {
        try{
            String query = QueryHelper.createQueryUPDATEObjetoArmario();
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setObject(1, cantidad);
            statement.setObject(2, pouId);
            statement.setObject(3,idArticulo);

            statement.executeQuery();
        }catch (SQLException e) {throw new RuntimeException(e);}

    }

    @Override
    public List<Object> obtenerObjetosOrdenadosPorAlgo(Class theClass, String criterioOrden) {
        String selectQuery = QueryHelper.createQueryORDERSomethingByColumnValues(theClass, criterioOrden);
        PreparedStatement statement;
        List<Object> listaOrdenadaDescendientemente = null;

        try{
            statement = conn.prepareStatement(selectQuery);
            listaOrdenadaDescendientemente = ObjectHelper.createObjects(statement.executeQuery(), theClass);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException |
                NoSuchFieldException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return listaOrdenadaDescendientemente;
    }
}
