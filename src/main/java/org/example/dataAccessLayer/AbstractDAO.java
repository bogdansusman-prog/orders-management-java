package org.example.dataAccessLayer;

import org.example.connection.ConnectionFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * clasa generica pentru operatiile de acces la baza de date
 * foloseste reflection pentru generarea automata a operatiilor CRUD.
 *
 * @param <T> tipul obiectului utilizat
 */

public class AbstractDAO<T> {
    private final Class<T> type;

    /**
     * Initializeaza tipul generic utilizat de clasa DAO.
     */
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType)
                getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    /**
     * Returneaza toate inregistrarile din tabela asociata tipului generic.
     *
     * @return lista de obiecte din baza de date
     */
    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        String query = "SELECT * FROM " + type.getSimpleName().toLowerCase();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                list.add(createObject(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Cauta un obiect dupa id.
     *
     * @param id id-ul obiectului cautat
     * @return obiectul gasit sau null daca nu exista
     */
    public T findById(int id) {
        String query = "SELECT * FROM " + type.getSimpleName().toLowerCase() + " WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            try(ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return createObject(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Creeaza un obiect pe baza datelor din ResultSet folosind reflection.
     *
     * @param rs rezultatul interogarii SQL
     * @return obiectul creat
     * @throws Exception daca apare o eroare la crearea obiectului
     */
    private T createObject(ResultSet rs) throws Exception {
        T instance = type.getDeclaredConstructor().newInstance();

        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = rs.getObject(field.getName());
            field.set(instance, value);
        }

        return instance;
    }

    /**
     * Insereaza un obiect in baza de date.
     *
     * @param obj obiectul care va fi inserat
     */
    public void insert(T obj){
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(type.getSimpleName().toLowerCase()).append(" (");

        Field[] fields = type.getDeclaredFields();

        for(Field field : fields){
            if(!field.getName().equals("id")){
                query.append("\"")
                        .append(field.getName())
                        .append("\",");
            }
        }

        query.deleteCharAt(query.length() - 1);
        query.append(") VALUES (" );

//        for(Field field : fields){
//            if(!field.getName().equals("id")){
//                query.append("?,");
//            }
//        }
        List.of(fields)
                .stream()
                .filter(field -> !field.getName().equals("id"))
                .forEach(field -> query.append("?,"));

        query.deleteCharAt(query.length()-1);
        query.append(")");

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query.toString())){
            int index = 1;
            for(Field field : fields){
                if(!field.getName().equals("id")){
                    field.setAccessible(true);
                    statement.setObject(index++, field.get(obj));
                }
            }
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Actualizeaza un obiect existent in baza de date.
     *
     * @param obj obiectul care va fi actualizat
     */
    public void update(T obj){
        Field[] fields = type.getDeclaredFields();

        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(type.getSimpleName().toLowerCase()).append(" SET ");

        for(Field field : fields){
            if(!field.getName().equals("id")){
                query.append("\"").append(field.getName()).append("\" = ?, ");
            }
        }

        query.delete(query.length() - 2, query.length());
        query.append(" WHERE id = ?");

        try(Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(query.toString())
        ){
            int index = 1;
            int idValue = 0;

            for(Field field: fields){
                field.setAccessible(true);

                if(field.getName().equals("id")){
                    idValue = (int) field.get(obj);
                }else{
                    statement.setObject(index++, field.get(obj));
                }
            }
            statement.setInt(index, idValue);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Sterge un obiect din baza de date dupa id.
     *
     * @param id id-ul obiectului care va fi sters
     */
    public void delete(int id){
        String query = "DELETE FROM " + type.getSimpleName().toLowerCase() +
                " WHERE id = ?";

        try(Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}