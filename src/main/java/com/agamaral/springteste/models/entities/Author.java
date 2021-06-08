package com.agamaral.springteste.models.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Author extends AbstractEntity {
    @NotEmpty
    private String name;
    private String birth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

}
