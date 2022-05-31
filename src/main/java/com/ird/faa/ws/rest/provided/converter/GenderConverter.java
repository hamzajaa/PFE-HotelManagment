package com.ird.faa.ws.rest.provided.converter;

import com.ird.faa.bean.Gender;
import com.ird.faa.service.util.NumberUtil;
import com.ird.faa.service.util.StringUtil;
import com.ird.faa.ws.rest.provided.vo.GenderVo;
import org.springframework.stereotype.Component;

@Component
public class GenderConverter extends AbstractConverter<Gender,GenderVo>{


    public GenderConverter(){
        init(true);
    }

    @Override
    public Gender toItem(GenderVo vo) {
        if (vo == null) {
            return null;
        } else {
            Gender item = new Gender();
            if(StringUtil.isNotEmpty(vo.getId()))
                item.setId(NumberUtil.toLong(vo.getId()));
            if(StringUtil.isNotEmpty(vo.getName()))
                item.setName(vo.getName());
            if(StringUtil.isNotEmpty(vo.getCode()))
                item.setCode(vo.getCode());


            return item;
        }
    }

    @Override
    public GenderVo toVo(Gender item) {
        if (item == null) {
            return null;
        } else {
            GenderVo vo = new GenderVo();
            if(item.getId()!=null)
                vo.setId(NumberUtil.toString(item.getId()));

            if(StringUtil.isNotEmpty(item.getName()))
                vo.setName(item.getName());

            if(StringUtil.isNotEmpty(item.getCode()))
                vo.setCode(item.getCode());


            return vo;
        }
    }

    public void init(Boolean value) {
    }









}
