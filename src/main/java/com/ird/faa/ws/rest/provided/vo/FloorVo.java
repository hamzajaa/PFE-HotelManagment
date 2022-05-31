package  com.ird.faa.ws.rest.provided.vo;

    import java.util.Date;
    import javax.persistence.Temporal;
    import javax.persistence.TemporalType;
    import com.fasterxml.jackson.annotation.JsonFormat;

public class FloorVo {

    private String id ;
    private String name ;
    private String flootNumber ;
    private Boolean active ;
    private Boolean archive ;
    private String dateArchivage ;
    private String dateCreation ;


            private String flootNumberMax ;
            private String flootNumberMin ;
            private String dateArchivageMax ;
            private String dateArchivageMin ;
            private String dateCreationMax ;
            private String dateCreationMin ;



    public FloorVo(){
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
        public String getFlootNumber(){
        return this.flootNumber;
        }

        public void setFlootNumber(String flootNumber){
        this.flootNumber = flootNumber;
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


            public String getFlootNumberMax(){
            return this.flootNumberMax;
            }

            public String getFlootNumberMin(){
            return this.flootNumberMin;
            }

            public void setFlootNumberMax(String flootNumberMax){
            this.flootNumberMax = flootNumberMax;
            }

            public void setFlootNumberMin(String flootNumberMin){
            this.flootNumberMin = flootNumberMin;
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




            }
