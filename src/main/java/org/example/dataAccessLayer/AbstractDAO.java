package org.example.dataAccessLayer;

import org.example.connection.ConnectionFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> {
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType)
                getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

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

    public T findById(int id) {
        String query = "SELECT * FROM " + type.getSimpleName().toLowerCase() + " WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return createObject(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private T createObject(ResultSet rs) throws Exception {
        T instance = type.getDeclaredConstructor().newInstance();

        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = rs.getObject(field.getName());
            field.set(instance, value);
        }

        return instance;
    }

    public void insert(T obj){
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(type.getSimpleName().toLowerCase()).append(" (");

        Field[] fields = type.getDeclaredFields();

        for(Field field : fields){
            if(!field.getName().equals("id")){
                query.append(field.getName()).append(",");
            }
        }

        query.deleteCharAt(query.length() - 1);
        query.append(") VALUES (" );

        for(Field field : fields){
            if(!field.getName().equals("id")){
                query.append("?,");
            }
        }
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
}