package com.ffzx.core.controller;

import com.ffzx.orm.common.GenericExample;

import java.lang.reflect.ParameterizedType;
import java.util.Map;


public abstract class BaseController<T, PK, EX extends GenericExample<?>> extends BaseEditController<T, PK> {

    private Class<EX> exampleClass;

    public BaseController() {
        super();
        exampleClass = (Class<EX>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];

    }

    //throw new RuntimeException(getClass() + " unsuppoted getService() method");
    public EX createExample() {
        EX ex = null;
        try {
            ex = exampleClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ex;
    }


    public Object query(T entity) {
        return null;
    }

    /**
     * 查询实体对象转换，移除空值
     *
     * @param entity
     * @return
     */
    private Map<String, Object> getQueryEntity(T entity) {
        return null;
    }

    /**
     * 查询对象转换，覆写该方法已改变默认查询条件
     *
     * @param params
     * @return
     */
    protected EX getExample(Map<String, Object> params) {
        return null;
    }

}
