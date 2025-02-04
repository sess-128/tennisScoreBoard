package com.rrtyui.repository;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.*;

public interface Repository<K extends Serializable, E> {
    E save(E e);
    void update (E entity);
    void delete (K id);
    Optional<E> findById (K id);
    List<E> findAll();
}
