package com.ec.app.microservices.config;

public interface IQueryDslBaseRepository<T> {

    void save(T entity);

    void update(T entity);

    void delete(T entity);

}
