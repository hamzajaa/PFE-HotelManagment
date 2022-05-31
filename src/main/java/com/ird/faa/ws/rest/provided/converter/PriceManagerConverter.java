package  com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.PriceManager;
import com.ird.faa.ws.rest.provided.vo.PriceManagerVo;

@Component
public class PriceManagerConverter extends AbstractConverter<PriceManager,PriceManagerVo>{

        @Autowired
        private RoomTypeConverter roomTypeConverter ;
    private Boolean roomType;

public  PriceManagerConverter(){
init(true);
}

@Override
public PriceManager toItem(PriceManagerVo vo) {
if (vo == null) {
return null;
} else {
PriceManager item = new PriceManager();
        if(StringUtil.isNotEmpty(vo.getId()))
        item.setId(NumberUtil.toLong(vo.getId()));
        if(StringUtil.isNotEmpty(vo.getDateStart()))
        item.setDateStart(DateUtil.parse(vo.getDateStart()));
        if(StringUtil.isNotEmpty(vo.getDateEnd()))
        item.setDateEnd(DateUtil.parse(vo.getDateEnd()));
        if(StringUtil.isNotEmpty(vo.getMontant()))
        item.setMontant(NumberUtil.toBigDecimal(vo.getMontant()));
            if(vo.getArchive() != null)
            item.setArchive(vo.getArchive());
        if(StringUtil.isNotEmpty(vo.getDateArchivage()))
        item.setDateArchivage(DateUtil.parse(vo.getDateArchivage()));
        if(StringUtil.isNotEmpty(vo.getDateCreation()))
        item.setDateCreation(DateUtil.parse(vo.getDateCreation()));
    if(vo.getRoomTypeVo()!=null && this.roomType)
        item.setRoomType(roomTypeConverter.toItem(vo.getRoomTypeVo())) ;


return item;
}
}

@Override
public PriceManagerVo toVo(PriceManager item) {
if (item == null) {
return null;
} else {
PriceManagerVo vo = new PriceManagerVo();
        if(item.getId()!=null)
        vo.setId(NumberUtil.toString(item.getId()));

        if(item.getDateStart()!=null)
        vo.setDateStart(DateUtil.formateDate(item.getDateStart()));
        if(item.getDateEnd()!=null)
        vo.setDateEnd(DateUtil.formateDate(item.getDateEnd()));
        if(item.getMontant()!=null)
        vo.setMontant(NumberUtil.toString(item.getMontant()));

        if(item.getArchive()!=null)
        vo.setArchive(item.getArchive());
        if(item.getDateArchivage()!=null)
        vo.setDateArchivage(DateUtil.formateDate(item.getDateArchivage()));
        if(item.getDateCreation()!=null)
        vo.setDateCreation(DateUtil.formateDate(item.getDateCreation()));
    if(item.getRoomType()!=null && this.roomType) {
        vo.setRoomTypeVo(roomTypeConverter.toVo(item.getRoomType())) ;
    }

return vo;
}
}

public void init(Boolean value) {
    roomType = value;
}


        public RoomTypeConverter getRoomTypeConverter(){
        return this.roomTypeConverter;
        }
        public void setRoomTypeConverter(RoomTypeConverter roomTypeConverter ){
        this.roomTypeConverter = roomTypeConverter;
        }

    public boolean  isRoomType(){
    return this.roomType;
    }
    public void  setRoomType(boolean roomType){
    this.roomType = roomType;
    }
















}
