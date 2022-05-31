package  com.ird.faa.ws.rest.provided.vo;

    import java.util.List;
    import java.util.Date;
    import javax.persistence.Temporal;
    import javax.persistence.TemporalType;
    import com.fasterxml.jackson.annotation.JsonFormat;
    import java.math.BigDecimal;

public class PaidServiceVo {

    private String id ;
    private String title ;
    private String code ;
    private String price ;
    private String description ;
    private Boolean active ;
    private Boolean archive ;
    private String dateArchivage ;
    private String dateCreation ;


            private String priceMax ;
            private String priceMin ;
            private String dateArchivageMax ;
            private String dateArchivageMin ;
            private String dateCreationMax ;
            private String dateCreationMin ;

        private PriceTypeVo priceTypeVo ;

    private List<RoomTypeVo> roomTypesVo ;

    public PaidServiceVo(){
    super();
    }

        public String getId(){
        return this.id;
        }

        public void setId(String id){
        this.id = id;
        }
        public String getTitle(){
        return this.title;
        }

        public void setTitle(String title){
        this.title = title;
        }
        public String getCode(){
        return this.code;
        }

        public void setCode(String code){
        this.code = code;
        }
        public String getPrice(){
        return this.price;
        }

        public void setPrice(String price){
        this.price = price;
        }
        public String getDescription(){
        return this.description;
        }

        public void setDescription(String description){
        this.description = description;
        }
        public Boolean getActive(){
        return this.active;
        }

        public void setActive(Boolean active){
        this.active = active;
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


            public String getPriceMax(){
            return this.priceMax;
            }

            public String getPriceMin(){
            return this.priceMin;
            }

            public void setPriceMax(String priceMax){
            this.priceMax = priceMax;
            }

            public void setPriceMin(String priceMin){
            this.priceMin = priceMin;
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


        public PriceTypeVo getPriceTypeVo(){
        return this.priceTypeVo;
        }

        public void setPriceTypeVo(PriceTypeVo priceTypeVo){
        this.priceTypeVo = priceTypeVo;
        }


        public List<RoomTypeVo> getRoomTypesVo(){
        return this.roomTypesVo;
        }

        public void setRoomTypesVo(List<RoomTypeVo> roomTypesVo){
            this.roomTypesVo = roomTypesVo;
            }

            }
