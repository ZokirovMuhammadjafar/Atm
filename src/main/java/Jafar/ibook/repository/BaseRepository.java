package Jafar.ibook.repository;


import Jafar.ibook.models.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <E> -> Entity .....
 */
public abstract class BaseRepository<E extends BaseEntity> {

    public abstract void save(E e);


    public abstract void delete(String id);


    public abstract void delete(E e);


    public abstract E get();


    public abstract List<E> getAll();


}
