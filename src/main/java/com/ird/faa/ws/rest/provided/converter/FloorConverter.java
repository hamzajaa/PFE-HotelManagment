package  com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.Floor;
import com.ird.faa.ws.rest.provided.vo.FloorVo;

@Component
public class FloorConverter extends AbstractConverter<Floor,FloorVo>{


public  FloorConverter(){
init(true);
}

@Override
public Floor toItem(FloorVo vo) {
if (vo == null) {
return null;
} else {
Floor item = new Floor();
        if(StringUtil.isNotEmpty(vo.getId()))
        item.setId(NumberUtil.toLong(vo.getId()));
        if(StringUtil.isNotEmpty(vo.getName()))
        item.setName(vo.getName());
        if(StringUtil.isNotEmpty(vo.getFlootNumber()))
        item.setFlootNumber(NumberUtil.toLong(vo.getFlootNumber()));
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
public FloorVo toVo(Floor item) {
if (item == null) {
return null;
} else {
FloorVo vo = new FloorVo();
        if(item.getId()!=null)
        vo.setId(NumberUtil.toString(item.getId()));

        if(StringUtil.isNotEmpty(item.getName()))
        vo.setName(item.getName());

        if(item.getFlootNumber()!=null)
        vo.setFlootNumber(NumberUtil.toString(item.getFlootNumber()));

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
