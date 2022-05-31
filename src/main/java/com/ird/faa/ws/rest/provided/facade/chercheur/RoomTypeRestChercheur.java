package  com.ird.faa.ws.rest.provided.facade.chercheur;

import com.ird.faa.service.chercheur.facade.RoomTypeChercheurService;

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
import com.ird.faa.bean.RoomType;
import com.ird.faa.ws.rest.provided.converter.RoomTypeConverter;
import com.ird.faa.ws.rest.provided.vo.RoomTypeVo;

@Api("Manages roomType services")
@RestController
@RequestMapping("api/chercheur/roomType")
public class RoomTypeRestChercheur {

@Autowired
private RoomTypeChercheurService roomTypeService;

@Autowired
private RoomTypeConverter roomTypeConverter;


            @ApiOperation("Updates the specified  roomType")
            @PutMapping("/")
            public  RoomTypeVo update(@RequestBody  RoomTypeVo  roomTypeVo){
            RoomType roomType = roomTypeConverter.toItem(roomTypeVo);
            roomType = roomTypeService.update(roomType);
            return roomTypeConverter.toVo(roomType);
            }

    @ApiOperation("Finds a list of all roomTypes")
    @GetMapping("/")
    public List<RoomTypeVo> findAll(){
        return roomTypeConverter.toVo(roomTypeService.findAll());
    }

    @ApiOperation("Finds a roomType with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public RoomTypeVo findByIdWithAssociatedList(@PathVariable Long id){
    return roomTypeConverter.toVo(roomTypeService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search roomType by a specific criteria")
    @PostMapping("/search")
    public List<RoomTypeVo> findByCriteria(@RequestBody RoomTypeVo roomTypeVo){
        return roomTypeConverter.toVo(roomTypeService.findByCriteria(roomTypeVo));
        }

            @ApiOperation("Finds a roomType by id")
            @GetMapping("/id/{id}")
            public RoomTypeVo findById(@PathVariable Long id){
            return roomTypeConverter.toVo(roomTypeService.findById(id));
            }

            @ApiOperation("Saves the specified  roomType")
            @PostMapping("/")
            public RoomTypeVo save(@RequestBody RoomTypeVo roomTypeVo){
            RoomType roomType = roomTypeConverter.toItem(roomTypeVo);
            roomType = roomTypeService.save(roomType);
            return roomTypeConverter.toVo(roomType);
            }

            @ApiOperation("Delete the specified roomType")
            @DeleteMapping("/")
            public int delete(@RequestBody RoomTypeVo roomTypeVo){
            RoomType roomType = roomTypeConverter.toItem(roomTypeVo);
            return roomTypeService.delete(roomType);
            }

            @ApiOperation("Deletes a roomType by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return roomTypeService.deleteById(id);
            }




            }
