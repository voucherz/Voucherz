package com.iswAcademy.Voucherz.dao;

import java.util.List;

public interface IBaseDao<T> {
    public T create(T Model);

    public boolean update(T model);

    public T find(String token);

    public T findById(long id);

    public List<T> ReadAll();

    public boolean delete(T model);
}
