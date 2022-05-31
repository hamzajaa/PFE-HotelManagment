package com.ird.faa.bean;

import java.util.Objects;


import javax.persistence.*;


@Entity
@Table(name = "country")
public class Country {

    @Id
    @SequenceGenerator(name = "country_seq", sequenceName = "country_seq",
            allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_seq")
    private Long id;

    @Column(length = 500)
    private String libelle;
    @Column(length = 500)
    private String code;


    public Country() {
        super();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id != null && id.equals(country.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

