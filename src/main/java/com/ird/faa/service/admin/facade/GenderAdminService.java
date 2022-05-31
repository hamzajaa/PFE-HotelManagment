package com.ird.faa.service.admin.facade;

import com.ird.faa.bean.Gender;
import com.ird.faa.service.core.facade.AbstractService;
import com.ird.faa.ws.rest.provided.vo.GenderVo;

public interface GenderAdminService extends AbstractService<Gender,Long, GenderVo>{



    /**
     * find Gender from database by code (reference)
     * @param code - reference of Gender
     * @return the founded Gender , If no Gender were
     *         found in database return  null.
     */
    Gender findByCode(String code);

    /**
     * find Gender from database by id (PK) or code (reference)
     * @param id - id of Gender
     * @param code - reference of Gender
     * @return the founded Gender , If no Gender were
     *         found in database return  null.
     */
    Gender findByIdOrCode(Gender gender);


    /**
     * delete Gender from database
     * @param id - id of Gender to be deleted
     *
     */
    int deleteById(Long id);




    /**
     * delete Gender from database by code (reference)
     *
     * @param code - reference of Gender to be deleted
     * @return 1 if Gender deleted successfully
     */
    int deleteByCode(String code);





}
