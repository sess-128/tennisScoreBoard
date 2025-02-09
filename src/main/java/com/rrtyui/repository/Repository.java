package com.rrtyui.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<K extends Serializable, E> {
    E save(E e);
    void update (E entity);
    void delete (K id);
    Optional<E> findById (K id);
    Optional<E> findByName (String name);
    List<E> findAll();
}
