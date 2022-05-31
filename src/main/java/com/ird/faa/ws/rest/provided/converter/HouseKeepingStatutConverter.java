package  com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.HouseKeepingStatut;
import com.ird.faa.ws.rest.provided.vo.HouseKeepingStatutVo;

@Component
public class HouseKeepingStatutConverter extends AbstractConverter<HouseKeepingStatut,HouseKeepingStatutVo>{


public  HouseKeepingStatutConverter(){
init(true);
}

@Override
public HouseKeepingStatut toItem(HouseKeepingStatutVo vo) {
if (vo == null) {
return null;
} else {
HouseKeepingStatut item = new HouseKeepingStatut();
        if(StringUtil.isNotEmpty(vo.getId()))
        item.setId(NumberUtil.toLong(vo.getId()));
        if(StringUtil.isNotEmpty(vo.getName()))
        item.setName(vo.getName());
            if(vo.getActive() != null)
            item.setActive(vo.getActive());
            if(vo.getArchive() != null)
            item.setArchive(vo.getArchive());
        if(StringUtil.isNotEmpty(vo.getDateArchivage()))
        item.setDateArchivage(DateUtil.parse(vo.getDateArchivage()));
        if(StringUtil.isNotEmpty(vo.getDateCreation()))
        item.setDateCreation(DateUtil.parse(vo.getDateCreation()));


return item;
}
}

@Override
public HouseKeepingStatutVo toVo(HouseKeepingStatut item) {
if (item == null) {
return null;
} else {
HouseKeepingStatutVo vo = new HouseKeepingStatutVo();
        if(item.getId()!=null)
        vo.setId(NumberUtil.toString(item.getId()));

        if(StringUtil.isNotEmpty(item.getName()))
        vo.setName(item.getName());

        if(item.getActive()!=null)
        vo.setActive(item.getActive());
        if(item.getArchive()!=null)
        vo.setArchive(item.getArchive());
        if(item.getDateArchivage()!=null)
        vo.setDateArchivage(DateUtil.formateDate(item.getDateArchivage()));
        if(item.getDateCreation()!=null)
        vo.setDateCreation(DateUtil.formateDate(item.getDateCreation()));

return vo;
}
}

public void init(Boolean value) {
}















}
