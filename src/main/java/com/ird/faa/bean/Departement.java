package com.ird.faa.bean;

import java.util.Objects;
import java.util.List;


import javax.persistence.*;


@Entity
@Table(name = "departement")
public class Departement {

    @Id
    @SequenceGenerator(name = "departement_seq", sequenceName = "departement_seq",
            allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departement_seq")
    private Long id;

    @Column(length = 500)
    private String libelle;
    @Column(length = 500)
    private String code;


    @OneToMany(mappedBy = "departement")
    private List<Employee> employees;

    public Departement() {
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

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departement departement = (Departement) o;
        return id != null && id.equals(departement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

