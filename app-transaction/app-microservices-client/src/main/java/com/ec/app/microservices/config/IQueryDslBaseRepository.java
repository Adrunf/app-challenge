package com.ec.app.microservices.config;

import org.springframework.data.jpa.repository.Query;

public interface IQueryDslBaseRepository<T> {

    void save(T entity);

    void update(T entity);

    void delete(T entity);

}
