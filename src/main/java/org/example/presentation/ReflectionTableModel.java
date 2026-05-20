package org.example.presentation;


import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Clasa utilizata pentru generarea automata a modelelor de tabel
 * folosind reflection.
 */
public class ReflectionTableModel {
    /**
     * Creeaza un model de tabel pe baza unei liste de obiecte.
     *
     * @param objects lista de obiecte utilizata pentru generarea tabelului
     * @return modelul de tabel generat automat
     * @param <T> tipul obiectelor din lista
     */
    public static <T>DefaultTableModel createTableModel(List<T> objects){
        DefaultTableModel model = new DefaultTableModel();

        if(objects == null || objects.isEmpty()){
            return model;
        }
        Class<?> clazz = objects.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();

        for(Field field : fields){
            model.addColumn(field.getName());
        }

        for(T object : objects){
            Object[] row = new Object[fields.length];


            for(int i=0; i<fields.length; i++) {
                try {
                    fields[i].setAccessible(true);
                    row[i] = fields[i].get(object);
                }catch(IllegalAccessException e){
                    row[i] = null;
                }
            }
            model.addRow(row);
        }
        return model;
    }
}
