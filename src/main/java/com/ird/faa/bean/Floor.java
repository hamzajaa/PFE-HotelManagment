package com.ird.faa.bean;

import java.util.Objects;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.*;



@Entity
@Table(name = "floor")
public class Floor    implements Archivable  {

@Id
    @SequenceGenerator(name="floor_seq",sequenceName="floor_seq",
    allocationSize=1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="floor_seq")
private Long id;

            @Column(length = 500)
            private String name;
            private Long flootNumber ;
            @Column(columnDefinition = "boolean default false")
                 private Boolean active = false;
            @Column(columnDefinition = "boolean default false")
                 private Boolean archive = false;
            @JsonFormat(pattern="yyyy-MM-dd")
            @Temporal(TemporalType.DATE)
            private Date dateArchivage ;
            @JsonFormat(pattern="yyyy-MM-dd")
            @Temporal(TemporalType.DATE)
            private Date dateCreation ;



public Floor(){
super();
}


            public Long getId(){
            return this.id;
            }
            public void setId(Long id){
            this.id = id;
            }
            public String getName(){
            return this.name;
            }
            public void setName(String name){
            this.name = name;
            }
            public Long getFlootNumber(){
            return this.flootNumber;
            }
            public void setFlootNumber(Long flootNumber){
            this.flootNumber = flootNumber;
            }
        public Boolean  getActive(){
        return this.active;
        }
        public void setActive(Boolean active){
        this.active = active;
        }
        public Boolean  getArchive(){
        return this.archive;
        }
        public void setArchive(Boolean archive){
        this.archive = archive;
        }
            public Date getDateArchivage(){
            return this.dateArchivage;
            }
            public void setDateArchivage(Date dateArchivage){
            this.dateArchivage = dateArchivage;
            }
            public Date getDateCreation(){
            return this.dateCreation;
            }
            public void setDateCreation(Date dateCreation){
            this.dateCreation = dateCreation;
            }

        @Override
        public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return id != null && id.equals(floor.id);
        }

        @Override
        public int hashCode() {
        return Objects.hash(id);
        }

}

