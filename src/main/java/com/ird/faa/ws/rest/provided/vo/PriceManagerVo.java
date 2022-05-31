package  com.ird.faa.ws.rest.provided.vo;

    import java.util.Date;
    import javax.persistence.Temporal;
    import javax.persistence.TemporalType;
    import com.fasterxml.jackson.annotation.JsonFormat;
    import java.math.BigDecimal;

public class PriceManagerVo {

    private String id ;
    private String dateStart ;
    private String dateEnd ;
    private String montant ;
    private Boolean archive ;
    private String dateArchivage ;
    private String dateCreation ;


            private String dateStartMax ;
            private String dateStartMin ;
            private String dateEndMax ;
            private String dateEndMin ;
            private String montantMax ;
            private String montantMin ;
            private String dateArchivageMax ;
            private String dateArchivageMin ;
            private String dateCreationMax ;
            private String dateCreationMin ;

        private RoomTypeVo roomTypeVo ;


    public PriceManagerVo(){
    super();
    }

        public String getId(){
        return this.id;
        }

        public void setId(String id){
        this.id = id;
        }
        public String getDateStart(){
        return this.dateStart;
        }

        public void setDateStart(String dateStart){
        this.dateStart = dateStart;
        }
        public String getDateEnd(){
        return this.dateEnd;
        }

        public void setDateEnd(String dateEnd){
        this.dateEnd = dateEnd;
        }
        public String getMontant(){
        return this.montant;
        }

        public void setMontant(String montant){
        this.montant = montant;
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


            public String getDateStartMax(){
            return this.dateStartMax;
            }

            public String getDateStartMin(){
            return this.dateStartMin;
            }

            public void setDateStartMax(String dateStartMax){
            this.dateStartMax = dateStartMax;
            }

            public void setDateStartMin(String dateStartMin){
            this.dateStartMin = dateStartMin;
            }

            public String getDateEndMax(){
            return this.dateEndMax;
            }

            public String getDateEndMin(){
            return this.dateEndMin;
            }

            public void setDateEndMax(String dateEndMax){
            this.dateEndMax = dateEndMax;
            }

            public void setDateEndMin(String dateEndMin){
            this.dateEndMin = dateEndMin;
            }

            public String getMontantMax(){
            return this.montantMax;
            }

            public String getMontantMin(){
            return this.montantMin;
            }

            public void setMontantMax(String montantMax){
            this.montantMax = montantMax;
            }

            public void setMontantMin(String montantMin){
            this.montantMin = montantMin;
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


            }
