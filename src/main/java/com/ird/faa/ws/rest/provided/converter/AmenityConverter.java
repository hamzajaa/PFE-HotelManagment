package com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.Amenity;
import com.ird.faa.ws.rest.provided.vo.AmenityVo;

@Component
public class AmenityConverter extends AbstractConverter<Amenity, AmenityVo> {


    public AmenityConverter() {
        init(true);
    }

    private Boolean roomType;


    @Override
    public Amenity toItem(AmenityVo vo) {
        if (vo == null) {
            return null;
        } else {
            Amenity item = new Amenity();
            if (StringUtil.isNotEmpty(vo.getId()))
                item.setId(NumberUtil.toLong(vo.getId()));
            if (StringUtil.isNotEmpty(vo.getName()))
                item.setName(vo.getName());
            if (StringUtil.isNotEmpty(vo.getImage()))
                item.setImage(vo.getImage());
            if (vo.getActive() != null)
                item.setActive(vo.getActive());
            if (vo.getArchive() != null)
                item.setArchive(vo.getArchive());
            if (StringUtil.isNotEmpty(vo.getDateArchivage()))
                item.setDateArchivage(DateUtil.parse(vo.getDateArchivage()));
            if (StringUtil.isNotEmpty(vo.getDateCreation()))
                item.setDateCreation(DateUtil.parse(vo.getDateCreation()));


            return item;
        }
    }

    @Override
    public AmenityVo toVo(Amenity item) {
        if (item == null) {
            return null;
        } else {
            AmenityVo vo = new AmenityVo();
            if (item.getId() != null)
                vo.setId(NumberUtil.toString(item.getId()));

            if (StringUtil.isNotEmpty(item.getName()))
                vo.setName(item.getName());

            if (StringUtil.isNotEmpty(item.getImage()))
                vo.setImage(item.getImage());

            if (item.getActive() != null)
                vo.setActive(item.getActive());
            if (item.getArchive() != null)
                vo.setArchive(item.getArchive());
            if (item.getDateArchivage() != null)
                vo.setDateArchivage(DateUtil.formateDate(item.getDateArchivage()));
            if (item.getDateCreation() != null)
                vo.setDateCreation(DateUtil.formateDate(item.getDateCreation()));

            return vo;
        }
    }

    public void init(Boolean value) {
        roomType = value;
    }
    public void setRoomType(boolean roomType) {
        this.roomType = roomType;
    }


}
