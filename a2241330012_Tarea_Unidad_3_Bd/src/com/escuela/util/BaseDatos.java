package com.escuela.util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class BaseDatos {
    public static <T> ArrayList<T> consultarAObjeto(String sql, Class<T> clase) {
        ArrayList<T> lista = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                T obj = clase.getDeclaredConstructor().newInstance();
                for (Field f : clase.getDeclaredFields()) {
                    f.setAccessible(true);
                    try { f.set(obj, rs.getObject(f.getName().toUpperCase())); } catch(Exception ignored){}
                }
                lista.add(obj);
            }
        } catch(Exception e){ throw new RuntimeException(e);}
        return lista;
    }

    public static int modificar(String tabla, Map<String,Object> valores, String condicion, Object[] params){
        int filas=0;
        StringBuilder sql=new StringBuilder("UPDATE "+tabla+" SET ");
        int i=0;
        for(String campo:valores.keySet()){
            sql.append(campo).append("=?");
            if(i<valores.size()-1) sql.append(", ");
            i++;
        }
        sql.append(" WHERE ").append(condicion);

        try(Connection conn=DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql.toString())){
            int index=1;
            for(Object val:valores.values()) ps.setObject(index++,val);
            for(Object p:params) ps.setObject(index++,p);
            filas=ps.executeUpdate();
        } catch(Exception e){ throw new RuntimeException(e);}
        return filas;
    }
}