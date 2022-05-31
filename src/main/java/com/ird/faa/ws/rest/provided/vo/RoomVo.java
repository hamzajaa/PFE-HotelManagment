package  com.ird.faa.ws.rest.provided.vo;

    import java.util.Date;
    import javax.persistence.Temporal;
    import javax.persistence.TemporalType;
    import com.fasterxml.jackson.annotation.JsonFormat;

public class RoomVo {

    private String id ;
    private String roomNumber ;
    private String libelle ;
    private Boolean archive ;
    private String dateArchivage ;
    private String dateCreation ;


            private String roomNumberMax ;
            private String roomNumberMin ;
            private String dateArchivageMax ;
            private String dateArchivageMin ;
            private String dateCreationMax ;
            private String dateCreationMin ;

        private RoomTypeVo roomTypeVo ;
        private FloorVo floorVo ;
        private HouseKeepingVo houseKeepingVo ;


    public RoomVo(){
    super();
    }

        public String getId(){
        return this.id;
        }

        public void setId(String id){
        this.id = id;
        }
        public String getRoomNumber(){
        return this.roomNumber;
        }

        public void setRoomNumber(String roomNumber){
        this.roomNumber = roomNumber;
        }
        public String getLibelle(){
        return this.libelle;
        }

        public void setLibelle(String libelle){
        this.libelle = libelle;
        }
        public Boolean getArchive(){
        return this.archive;
        }

        public void setArchive(Boolean archive){
        this.archive = archive;
        }
        public String getDateArchivage(){
        return this.dateArchivage;
        }

        public void setDateArchivage(String dateArchivage){
        this.dateArchivage = dateArchivage;
        }
        public String getDateCreation(){
        return this.dateCreation;
        }

        public void setDateCreation(String dateCreation){
        this.dateCreation = dateCreation;
        }


            public String getRoomNumberMax(){
            return this.roomNumberMax;
            }

            public String getRoomNumberMin(){
            return this.roomNumberMin;
            }

            public void setRoomNumberMax(String roomNumberMax){
            this.roomNumberMax = roomNumberMax;
            }

            public void setRoomNumberMin(String roomNumberMin){
            this.roomNumberMin = roomNumberMin;
            }

            public String getDateArchivageMax(){
            return this.dateArchivageMax;
            }

            public String getDateArchivageMin(){
            return this.dateArchivageMin;
            }

            public void setDateArchivageMax(String dateArchivageMax){
            this.dateArchivageMax = dateArchivageMax;
            }

            public void setDateArchivageMin(String dateArchivageMin){
            this.dateArchivageMin = dateArchivageMin;
            }

            public String getDateCreationMax(){
            return this.dateCreationMax;
            }

            public String getDateCreationMin(){
            return this.dateCreationMin;
            }

            public void setDateCreationMax(String dateCreationMax){
            this.dateCreationMax = dateCreationMax;
            }

            public void setDateCreationMin(String dateCreationMin){
            this.dateCreationMin = dateCreationMin;
            }


        public RoomTypeVo getRoomTypeVo(){
        return this.roomTypeVo;
        }

        public void setRoomTypeVo(RoomTypeVo roomTypeVo){
        this.roomTypeVo = roomTypeVo;
        }
        public FloorVo getFloorVo(){
        return this.floorVo;
        }

        public void setFloorVo(FloorVo floorVo){
        this.floorVo = floorVo;
        }
        public HouseKeepingVo getHouseKeepingVo(){
        return this.houseKeepingVo;
        }

        public void setHouseKeepingVo(HouseKeepingVo houseKeepingVo){
        this.houseKeepingVo = houseKeepingVo;
        }


            }
