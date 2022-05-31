package  com.ird.faa.ws.rest.provided.facade.guest;

import com.ird.faa.service.guest.facade.HouseKeepingStatutGuestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.ird.faa.bean.HouseKeepingStatut;
import com.ird.faa.ws.rest.provided.converter.HouseKeepingStatutConverter;
import com.ird.faa.ws.rest.provided.vo.HouseKeepingStatutVo;

@Api("Manages houseKeepingStatut services")
@RestController
@RequestMapping("api/guest/houseKeepingStatut")
public class HouseKeepingStatutRestGuest {

@Autowired
private HouseKeepingStatutGuestService houseKeepingStatutService;

@Autowired
private HouseKeepingStatutConverter houseKeepingStatutConverter;


            @ApiOperation("Updates the specified  houseKeepingStatut")
            @PutMapping("/")
            public  HouseKeepingStatutVo update(@RequestBody  HouseKeepingStatutVo  houseKeepingStatutVo){
            HouseKeepingStatut houseKeepingStatut = houseKeepingStatutConverter.toItem(houseKeepingStatutVo);
            houseKeepingStatut = houseKeepingStatutService.update(houseKeepingStatut);
            return houseKeepingStatutConverter.toVo(houseKeepingStatut);
            }

    @ApiOperation("Finds a list of all houseKeepingStatuts")
    @GetMapping("/")
    public List<HouseKeepingStatutVo> findAll(){
        return houseKeepingStatutConverter.toVo(houseKeepingStatutService.findAll());
    }

    @ApiOperation("Finds a houseKeepingStatut with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public HouseKeepingStatutVo findByIdWithAssociatedList(@PathVariable Long id){
    return houseKeepingStatutConverter.toVo(houseKeepingStatutService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search houseKeepingStatut by a specific criteria")
    @PostMapping("/search")
    public List<HouseKeepingStatutVo> findByCriteria(@RequestBody HouseKeepingStatutVo houseKeepingStatutVo){
        return houseKeepingStatutConverter.toVo(houseKeepingStatutService.findByCriteria(houseKeepingStatutVo));
        }

            @ApiOperation("Finds a houseKeepingStatut by id")
            @GetMapping("/id/{id}")
            public HouseKeepingStatutVo findById(@PathVariable Long id){
            return houseKeepingStatutConverter.toVo(houseKeepingStatutService.findById(id));
            }

            @ApiOperation("Saves the specified  houseKeepingStatut")
            @PostMapping("/")
            public HouseKeepingStatutVo save(@RequestBody HouseKeepingStatutVo houseKeepingStatutVo){
            HouseKeepingStatut houseKeepingStatut = houseKeepingStatutConverter.toItem(houseKeepingStatutVo);
            houseKeepingStatut = houseKeepingStatutService.save(houseKeepingStatut);
            return houseKeepingStatutConverter.toVo(houseKeepingStatut);
            }

            @ApiOperation("Delete the specified houseKeepingStatut")
            @DeleteMapping("/")
            public int delete(@RequestBody HouseKeepingStatutVo houseKeepingStatutVo){
            HouseKeepingStatut houseKeepingStatut = houseKeepingStatutConverter.toItem(houseKeepingStatutVo);
            return houseKeepingStatutService.delete(houseKeepingStatut);
            }

            @ApiOperation("Deletes a houseKeepingStatut by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return houseKeepingStatutService.deleteById(id);
            }




            @PutMapping("/archiver/")
            public HouseKeepingStatutVo archiver(@RequestBody HouseKeepingStatutVo houseKeepingStatutVo){
                HouseKeepingStatut houseKeepingStatut = houseKeepingStatutService.archiver(houseKeepingStatutConverter.toItem(houseKeepingStatutVo));
                return houseKeepingStatutConverter.toVo(houseKeepingStatut);
                }

            @PutMapping("/desarchiver/")
            public HouseKeepingStatutVo desarchiver(@RequestBody HouseKeepingStatutVo houseKeepingStatutVo){
                HouseKeepingStatut houseKeepingStatut = houseKeepingStatutService.desarchiver(houseKeepingStatutConverter.toItem(houseKeepingStatutVo));
                return houseKeepingStatutConverter.toVo(houseKeepingStatut);}
            }
