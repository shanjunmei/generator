package com.ffzx.core.controller;


import com.ffzx.core.service.BaseService;

import java.lang.reflect.ParameterizedType;


public abstract class BaseEditController<T, PK> {


    private Class<T> entityClass;

    public BaseEditController() {
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }


    protected Class<T> getEntityClass() {
        return entityClass;
    }

    public String getBasePath() {
        return "/" + getEntityClass().getSimpleName();
    }

    public String getListPath() {
        return getBasePath() + "/List";
    }

    public String getFormPath() {
        return getBasePath() + "/Form";
    }

    public abstract BaseService<T, PK> getService();


    public T createEntity() {
        T entity = null;
        try {
            entity = getEntityClass().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entity;
    }


}
