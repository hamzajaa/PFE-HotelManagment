package  com.ird.faa.ws.rest.provided.vo;

    import java.util.Date;
    import javax.persistence.Temporal;
    import javax.persistence.TemporalType;
    import com.fasterxml.jackson.annotation.JsonFormat;

public class HouseKeepingVo {

    private String id ;
    private Boolean archive ;
    private String dateArchivage ;
    private String dateCreation ;


            private String dateArchivageMax ;
            private String dateArchivageMin ;
            private String dateCreationMax ;
            private String dateCreationMin ;

        private RoomVo roomVo ;
        private RoomTypeVo roomTypeVo ;
        private FloorVo floorVo ;
        private HouseKeepingStatutVo houseKeepingStatusVo ;
        private EmployeeVo employeeVo ;


    public HouseKeepingVo(){
    super();
    }

        public String getId(){
        return this.id;
        }

        public void setId(String id){
        this.id = id;
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


        public RoomVo getRoomVo(){
        return this.roomVo;
        }

        public void setRoomVo(RoomVo roomVo){
        this.roomVo = roomVo;
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
        public HouseKeepingStatutVo getHouseKeepingStatusVo(){
        return this.houseKeepingStatusVo;
        }

        public void setHouseKeepingStatusVo(HouseKeepingStatutVo houseKeepingStatusVo){
        this.houseKeepingStatusVo = houseKeepingStatusVo;
        }
        public EmployeeVo getEmployeeVo(){
        return this.employeeVo;
        }

        public void setEmployeeVo(EmployeeVo employeeVo){
        this.employeeVo = employeeVo;
        }


            }
