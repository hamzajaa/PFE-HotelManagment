package com.ird.faa.service.employee.facade;

import java.util.List;
import com.ird.faa.bean.Floor;
import com.ird.faa.ws.rest.provided.vo.FloorVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface FloorEmployeeService extends AbstractService<Floor,Long,FloorVo>{





/**
    * delete Floor from database
    * @param id - id of Floor to be deleted
    *
    */
    int deleteById(Long id);








    Floor archiver(Floor floor) ;
    Floor desarchiver(Floor floor);

}
