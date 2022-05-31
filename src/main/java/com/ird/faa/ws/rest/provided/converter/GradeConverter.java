package com.ird.faa.ws.rest.provided.converter;

import com.ird.faa.bean.Grade;
import com.ird.faa.service.util.NumberUtil;
import com.ird.faa.service.util.StringUtil;
import com.ird.faa.ws.rest.provided.vo.GradeVo;
import org.springframework.stereotype.Component;

@Component
public class GradeConverter extends AbstractConverter<Grade, GradeVo> {


    public GradeConverter() {
        init(true);
    }

    @Override
    public Grade toItem(GradeVo vo) {
        if (vo == null) {
            return null;
        } else {
            Grade item = new Grade();
            if (StringUtil.isNotEmpty(vo.getId()))
                item.setId(NumberUtil.toLong(vo.getId()));
            if (StringUtil.isNotEmpty(vo.getName()))
                item.setName(vo.getName());
            if (StringUtil.isNotEmpty(vo.getCode()))
                item.setCode(vo.getCode());


            return item;
        }
    }

    @Override
    public GradeVo toVo(Grade item) {
        if (item == null) {
            return null;
        } else {
            GradeVo vo = new GradeVo();
            if (item.getId() != null)
                vo.setId(NumberUtil.toString(item.getId()));

            if (StringUtil.isNotEmpty(item.getName()))
                vo.setName(item.getName());

            if (StringUtil.isNotEmpty(item.getCode()))
                vo.setCode(item.getCode());


            return vo;
        }
    }

    public void init(Boolean value) {
    }


}
