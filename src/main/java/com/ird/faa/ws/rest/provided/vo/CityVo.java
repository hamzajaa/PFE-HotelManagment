package  com.ird.faa.ws.rest.provided.vo;


public class CityVo {

    private String id ;
    private String libelle ;
    private String code ;



        private CountryVo countryVo ;


    public CityVo(){
    super();
    }

        public String getId(){
        return this.id;
        }

        public void setId(String id){
        this.id = id;
        }
        public String getLibelle(){
        return this.libelle;
        }

        public void setLibelle(String libelle){
        this.libelle = libelle;
        }
        public String getCode(){
        return this.code;
        }

        public void setCode(String code){
        this.code = code;
        }



        public CountryVo getCountryVo(){
        return this.countryVo;
        }

        public void setCountryVo(CountryVo countryVo){
        this.countryVo = countryVo;
        }


            }
