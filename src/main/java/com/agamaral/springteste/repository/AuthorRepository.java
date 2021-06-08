package com.agamaral.springteste.repository;

import com.agamaral.springteste.models.entities.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author,Long>
{
    List<Author> findByNameIgnoreCaseContaining(String name);
}
