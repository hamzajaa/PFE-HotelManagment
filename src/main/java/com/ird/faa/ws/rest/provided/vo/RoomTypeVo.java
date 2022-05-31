package  com.ird.faa.ws.rest.provided.vo;

    import java.util.List;
    import java.util.Date;
    import javax.persistence.Temporal;
    import javax.persistence.TemporalType;
    import com.fasterxml.jackson.annotation.JsonFormat;
    import java.math.BigDecimal;

public class RoomTypeVo {

    private String id ;
    private String name ;
    private String image ;
    private String shortCode ;
    private String slug ;
    private String description ;
    private String baseOccupancy ;
    private String higherOcuupancy ;
    private String numberOfExtraBed ;
    private String kidsOccupancy ;
    private String basePrice ;
    private String additionalPersonPrice ;
    private String extraBedPrice ;
    private Boolean archive ;
    private String dateArchivage ;
    private String dateCreation ;


            private String baseOccupancyMax ;
            private String baseOccupancyMin ;
            private String higherOcuupancyMax ;
            private String higherOcuupancyMin ;
            private String numberOfExtraBedMax ;
            private String numberOfExtraBedMin ;
            private String kidsOccupancyMax ;
            private String kidsOccupancyMin ;
            private String basePriceMax ;
            private String basePriceMin ;
            private String additionalPersonPriceMax ;
            private String additionalPersonPriceMin ;
            private String extraBedPriceMax ;
            private String extraBedPriceMin ;
            private String dateArchivageMax ;
            private String dateArchivageMin ;
            private String dateCreationMax ;
            private String dateCreationMin ;


    private List<AmenityVo> amenitysVo ;

    public RoomTypeVo(){
    super();
    }

        public String getId(){
        return this.id;
        }

        public void setId(String id){
        this.id = id;
        }
        public String getName(){
        return this.name;
        }

        public void setName(String name){
        this.name = name;
        }
        public String getImage(){
        return this.image;
        }

        public void setImage(String image){
        this.image = image;
        }
        public String getShortCode(){
        return this.shortCode;
        }

        public void setShortCode(String shortCode){
        this.shortCode = shortCode;
        }
        public String getSlug(){
        return this.slug;
        }

        public void setSlug(String slug){
        this.slug = slug;
        }
        public String getDescription(){
        return this.description;
        }

        public void setDescription(String description){
        this.description = description;
        }
        public String getBaseOccupancy(){
        return this.baseOccupancy;
        }

        public void setBaseOccupancy(String baseOccupancy){
        this.baseOccupancy = baseOccupancy;
        }
        public String getHigherOcuupancy(){
        return this.higherOcuupancy;
        }

        public void setHigherOcuupancy(String higherOcuupancy){
        this.higherOcuupancy = higherOcuupancy;
        }
        public String getNumberOfExtraBed(){
        return this.numberOfExtraBed;
        }

        public void setNumberOfExtraBed(String numberOfExtraBed){
        this.numberOfExtraBed = numberOfExtraBed;
        }
        public String getKidsOccupancy(){
        return this.kidsOccupancy;
        }

        public void setKidsOccupancy(String kidsOccupancy){
        this.kidsOccupancy = kidsOccupancy;
        }
        public String getBasePrice(){
        return this.basePrice;
        }

        public void setBasePrice(String basePrice){
        this.basePrice = basePrice;
        }
        public String getAdditionalPersonPrice(){
        return this.additionalPersonPrice;
        }

        public void setAdditionalPersonPrice(String additionalPersonPrice){
        this.additionalPersonPrice = additionalPersonPrice;
        }
        public String getExtraBedPrice(){
        return this.extraBedPrice;
        }

        public void setExtraBedPrice(String extraBedPrice){
        this.extraBedPrice = extraBedPrice;
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


            public String getBaseOccupancyMax(){
            return this.baseOccupancyMax;
            }

            public String getBaseOccupancyMin(){
            return this.baseOccupancyMin;
            }

            public void setBaseOccupancyMax(String baseOccupancyMax){
            this.baseOccupancyMax = baseOccupancyMax;
            }

            public void setBaseOccupancyMin(String baseOccupancyMin){
            this.baseOccupancyMin = baseOccupancyMin;
            }

            public String getHigherOcuupancyMax(){
            return this.higherOcuupancyMax;
            }

            public String getHigherOcuupancyMin(){
            return this.higherOcuupancyMin;
            }

            public void setHigherOcuupancyMax(String higherOcuupancyMax){
            this.higherOcuupancyMax = higherOcuupancyMax;
            }

            public void setHigherOcuupancyMin(String higherOcuupancyMin){
            this.higherOcuupancyMin = higherOcuupancyMin;
            }

            public String getNumberOfExtraBedMax(){
            return this.numberOfExtraBedMax;
            }

            public String getNumberOfExtraBedMin(){
            return this.numberOfExtraBedMin;
            }

            public void setNumberOfExtraBedMax(String numberOfExtraBedMax){
            this.numberOfExtraBedMax = numberOfExtraBedMax;
            }

            public void setNumberOfExtraBedMin(String numberOfExtraBedMin){
            this.numberOfExtraBedMin = numberOfExtraBedMin;
            }

            public String getKidsOccupancyMax(){
            return this.kidsOccupancyMax;
            }

            public String getKidsOccupancyMin(){
            return this.kidsOccupancyMin;
            }

            public void setKidsOccupancyMax(String kidsOccupancyMax){
            this.kidsOccupancyMax = kidsOccupancyMax;
            }

            public void setKidsOccupancyMin(String kidsOccupancyMin){
            this.kidsOccupancyMin = kidsOccupancyMin;
            }

            public String getBasePriceMax(){
            return this.basePriceMax;
            }

            public String getBasePriceMin(){
            return this.basePriceMin;
            }

            public void setBasePriceMax(String basePriceMax){
            this.basePriceMax = basePriceMax;
            }

            public void setBasePriceMin(String basePriceMin){
            this.basePriceMin = basePriceMin;
            }

            public String getAdditionalPersonPriceMax(){
            return this.additionalPersonPriceMax;
            }

            public String getAdditionalPersonPriceMin(){
            return this.additionalPersonPriceMin;
            }

            public void setAdditionalPersonPriceMax(String additionalPersonPriceMax){
            this.additionalPersonPriceMax = additionalPersonPriceMax;
            }

            public void setAdditionalPersonPriceMin(String additionalPersonPriceMin){
            this.additionalPersonPriceMin = additionalPersonPriceMin;
            }

            public String getExtraBedPriceMax(){
            return this.extraBedPriceMax;
            }

            public String getExtraBedPriceMin(){
            return this.extraBedPriceMin;
            }

            public void setExtraBedPriceMax(String extraBedPriceMax){
            this.extraBedPriceMax = extraBedPriceMax;
            }

            public void setExtraBedPriceMin(String extraBedPriceMin){
            this.extraBedPriceMin = extraBedPriceMin;
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




        public List<AmenityVo> getAmenitysVo(){
        return this.amenitysVo;
        }

        public void setAmenitysVo(List<AmenityVo> amenitysVo){
            this.amenitysVo = amenitysVo;
            }

            }
