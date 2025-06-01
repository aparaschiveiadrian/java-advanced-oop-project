package org.adrianaparaschivei.services;


import java.util.List;

public interface CRUDService<T> {
    void create(T entity);
    T read(long id);
    List<T> readAll();
    void update(T entity);
    void delete(long id);
}