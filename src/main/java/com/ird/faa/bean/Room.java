package com.ird.faa.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;



@Entity
@Table(name = "room")
public class Room  implements Archivable  {

@Id
    @SequenceGenerator(name="room_seq",sequenceName="room_seq",
    allocationSize=1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="room_seq")
private Long id;

            private Long roomNumber ;
            @Column(length = 500)
            private String libelle;
            @Column(columnDefinition = "boolean default false")
                 private Boolean archive = false;
            @JsonFormat(pattern="yyyy-MM-dd")
            @Temporal(TemporalType.DATE)
            private Date dateArchivage ;
            @JsonFormat(pattern="yyyy-MM-dd")
            @Temporal(TemporalType.DATE)
            private Date dateCreation ;

    @ManyToOne
    private RoomType roomType ;
    @ManyToOne
    private Floor floor ;
    @ManyToOne
    private HouseKeeping houseKeeping ;


public Room(){
super();
}


            public Long getId(){
            return this.id;
            }
            public void setId(Long id){
            this.id = id;
            }
            public Long getRoomNumber(){
            return this.roomNumber;
            }
            public void setRoomNumber(Long roomNumber){
            this.roomNumber = roomNumber;
            }
            public String getLibelle(){
            return this.libelle;
            }
            public void setLibelle(String libelle){
            this.libelle = libelle;
            }
            public RoomType getRoomType(){
            return this.roomType;
            }
            public void setRoomType(RoomType roomType){
            this.roomType = roomType;
            }
            public Floor getFloor(){
            return this.floor;
            }
            public void setFloor(Floor floor){
            this.floor = floor;
            }
            public HouseKeeping getHouseKeeping(){
            return this.houseKeeping;
            }
            public void setHouseKeeping(HouseKeeping houseKeeping){
            this.houseKeeping = houseKeeping;
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
        Room room = (Room) o;
        return id != null && id.equals(room.id);
        }

        @Override
        public int hashCode() {
        return Objects.hash(id);
        }

}

