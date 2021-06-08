package com.agamaral.springteste.controller;

import com.agamaral.springteste.error.ResourceNotFoundException;
import com.agamaral.springteste.models.entities.Author;
import com.agamaral.springteste.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("authors")
public class AuthorController {

    private final AuthorRepository authorDAO;

    @Autowired
    public AuthorController(AuthorRepository authorDAO) {
        this.authorDAO = authorDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(authorDAO.findAll(), HttpStatus.OK);
    }

    //num 1 - achar por id
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable("id") Long id) {
        verifyIfAuthorExists(id);
        Optional<Author> a1 = authorDAO.findById(id);
        return new ResponseEntity<>(a1, HttpStatus.OK);
    }

    //num 2 - achar por nome
    @GetMapping(path = "/{id}/{name}")
    public ResponseEntity<?> findAuthorsByName(@PathVariable String name, @PathVariable("id") Long id) {
        return new ResponseEntity<>("Welcome to the Sith Order, "+name+", your id is: "+id, HttpStatus.OK);
    }

    //sith challenge 2
    @PostMapping
    public String save(@Valid @RequestBody Author author) {
        authorDAO.save(author);
        return (author.getName()+" was born in "+author.getBirth());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        verifyIfAuthorExists(id);
        authorDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@RequestBody Author author) {
        verifyIfAuthorExists(author.getId());
        authorDAO.save(author);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyIfAuthorExists(Long id) {
        if(authorDAO.findById(id) == null) {
            throw new ResourceNotFoundException("Author not found for ID: "+id);
        }
    }
}