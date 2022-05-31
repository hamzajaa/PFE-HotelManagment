package  com.ird.faa.ws.rest.provided.facade.chercheur;

import com.ird.faa.service.chercheur.facade.FloorChercheurService;

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
import com.ird.faa.bean.Floor;
import com.ird.faa.ws.rest.provided.converter.FloorConverter;
import com.ird.faa.ws.rest.provided.vo.FloorVo;

@Api("Manages floor services")
@RestController
@RequestMapping("api/chercheur/floor")
public class FloorRestChercheur {

@Autowired
private FloorChercheurService floorService;

@Autowired
private FloorConverter floorConverter;


            @ApiOperation("Updates the specified  floor")
            @PutMapping("/")
            public  FloorVo update(@RequestBody  FloorVo  floorVo){
            Floor floor = floorConverter.toItem(floorVo);
            floor = floorService.update(floor);
            return floorConverter.toVo(floor);
            }

    @ApiOperation("Finds a list of all floors")
    @GetMapping("/")
    public List<FloorVo> findAll(){
        return floorConverter.toVo(floorService.findAll());
    }

    @ApiOperation("Finds a floor with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public FloorVo findByIdWithAssociatedList(@PathVariable Long id){
    return floorConverter.toVo(floorService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search floor by a specific criteria")
    @PostMapping("/search")
    public List<FloorVo> findByCriteria(@RequestBody FloorVo floorVo){
        return floorConverter.toVo(floorService.findByCriteria(floorVo));
        }

            @ApiOperation("Finds a floor by id")
            @GetMapping("/id/{id}")
            public FloorVo findById(@PathVariable Long id){
            return floorConverter.toVo(floorService.findById(id));
            }

            @ApiOperation("Saves the specified  floor")
            @PostMapping("/")
            public FloorVo save(@RequestBody FloorVo floorVo){
            Floor floor = floorConverter.toItem(floorVo);
            floor = floorService.save(floor);
            return floorConverter.toVo(floor);
            }

            @ApiOperation("Delete the specified floor")
            @DeleteMapping("/")
            public int delete(@RequestBody FloorVo floorVo){
            Floor floor = floorConverter.toItem(floorVo);
            return floorService.delete(floor);
            }

            @ApiOperation("Deletes a floor by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return floorService.deleteById(id);
            }




            }
