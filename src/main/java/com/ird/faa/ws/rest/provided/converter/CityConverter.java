package  com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.City;
import com.ird.faa.ws.rest.provided.vo.CityVo;

@Component
public class CityConverter extends AbstractConverter<City,CityVo>{

        @Autowired
        private CountryConverter countryConverter ;
    private Boolean country;

public  CityConverter(){
init(true);
}

@Override
public City toItem(CityVo vo) {
if (vo == null) {
return null;
} else {
City item = new City();
        if(StringUtil.isNotEmpty(vo.getId()))
        item.setId(NumberUtil.toLong(vo.getId()));
        if(StringUtil.isNotEmpty(vo.getLibelle()))
        item.setLibelle(vo.getLibelle());
        if(StringUtil.isNotEmpty(vo.getCode()))
        item.setCode(vo.getCode());
    if(vo.getCountryVo()!=null && this.country)
        item.setCountry(countryConverter.toItem(vo.getCountryVo())) ;


return item;
}
}

@Override
public CityVo toVo(City item) {
if (item == null) {
return null;
} else {
CityVo vo = new CityVo();
        if(item.getId()!=null)
        vo.setId(NumberUtil.toString(item.getId()));

        if(StringUtil.isNotEmpty(item.getLibelle()))
        vo.setLibelle(item.getLibelle());

        if(StringUtil.isNotEmpty(item.getCode()))
        vo.setCode(item.getCode());

    if(item.getCountry()!=null && this.country) {
        vo.setCountryVo(countryConverter.toVo(item.getCountry())) ;
    }

return vo;
}
}

public void init(Boolean value) {
    country = value;
}


        public CountryConverter getCountryConverter(){
        return this.countryConverter;
        }
        public void setCountryConverter(CountryConverter countryConverter ){
        this.countryConverter = countryConverter;
        }

    public boolean  isCountry(){
    return this.country;
    }
    public void  setCountry(boolean country){
    this.country = country;
    }








}
