package com.ffzx.core.service.impl;


import com.ffzx.core.service.BaseService;
import com.ffzx.orm.common.GenericExample;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public abstract class BaseServiceImpl<T, PK> implements BaseService<T, PK> {


    public BaseServiceImpl() {

    }
    // @Resource
    // Mapper<T> mapper;

    public abstract Mapper<T> getMapper();/* {
        throw new RuntimeException(
				"type[" + getClass() + "] unsupported  getMapper() method");
	}*/


    public int add(T entity) {

        return getMapper().insert(entity);
    }


    public int deleteById(String id) {
        return getMapper().deleteByPrimaryKey(id);
    }


    public int update(T entity) {

        return getMapper().updateByPrimaryKey(entity);
    }


    public int updateSelective(T entity) {

        return getMapper().updateByPrimaryKeySelective(entity);
    }


    public T findById(String id) {
        return (T) getMapper().selectByPrimaryKey(id);
    }


    public <EX extends GenericExample<?>> List<T> selectByExample(EX example) {
        return (List<T>) getMapper().selectByExample(example);
    }


    public <EX extends GenericExample<?>> int updateByExample(T record, EX example) {
        return getMapper().updateByExampleSelective(record, example);
    }


    public <EX extends GenericExample<?>> int countByExample(EX example) {
        return getMapper().selectCountByExample(example);

    }


    public T findByCode(String code) {
        return null;
    }


    public List<T> selectByEntity(T entity) {
        return getMapper().select(entity);
    }
}
