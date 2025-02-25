package com.rrtyui.repository;

import java.io.Serializable;
import java.util.Optional;

public interface Repository<K extends Serializable, E> {
    E save(E e);

    E update(E entity);

    Optional<E> findByName(String name);
}
