package  com.ird.faa.ws.rest.provided.facade.guest;

import com.ird.faa.service.guest.facade.RoomGuestService;

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
import com.ird.faa.bean.Room;
import com.ird.faa.ws.rest.provided.converter.RoomConverter;
import com.ird.faa.ws.rest.provided.vo.RoomVo;

@Api("Manages room services")
@RestController
@RequestMapping("api/guest/room")
public class RoomRestGuest {

@Autowired
private RoomGuestService roomService;

@Autowired
private RoomConverter roomConverter;


            @ApiOperation("Updates the specified  room")
            @PutMapping("/")
            public  RoomVo update(@RequestBody  RoomVo  roomVo){
            Room room = roomConverter.toItem(roomVo);
            room = roomService.update(room);
            return roomConverter.toVo(room);
            }

    @ApiOperation("Finds a list of all rooms")
    @GetMapping("/")
    public List<RoomVo> findAll(){
        return roomConverter.toVo(roomService.findAll());
    }

    @ApiOperation("Finds a room with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public RoomVo findByIdWithAssociatedList(@PathVariable Long id){
    return roomConverter.toVo(roomService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search room by a specific criteria")
    @PostMapping("/search")
    public List<RoomVo> findByCriteria(@RequestBody RoomVo roomVo){
        return roomConverter.toVo(roomService.findByCriteria(roomVo));
        }

            @ApiOperation("Finds a room by id")
            @GetMapping("/id/{id}")
            public RoomVo findById(@PathVariable Long id){
            return roomConverter.toVo(roomService.findById(id));
            }

            @ApiOperation("Saves the specified  room")
            @PostMapping("/")
            public RoomVo save(@RequestBody RoomVo roomVo){
            Room room = roomConverter.toItem(roomVo);
            room = roomService.save(room);
            return roomConverter.toVo(room);
            }

            @ApiOperation("Delete the specified room")
            @DeleteMapping("/")
            public int delete(@RequestBody RoomVo roomVo){
            Room room = roomConverter.toItem(roomVo);
            return roomService.delete(room);
            }

            @ApiOperation("Deletes a room by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return roomService.deleteById(id);
            }
        @ApiOperation("find by roomType shortCode")
        @GetMapping("/roomType/shortCode/{shortCode}")
        public List<Room> findByRoomTypeShortCode(@PathVariable String shortCode){
        return roomService.findByRoomTypeShortCode(shortCode);
        }

        @ApiOperation("delete by roomType shortCode")
        @DeleteMapping("/roomType/shortCode/{shortCode}")
        public int deleteByRoomTypeShortCode(@PathVariable String shortCode){
        return roomService.deleteByRoomTypeShortCode(shortCode);
        }

        @ApiOperation("find by roomType id")
        @GetMapping("/roomType/id/{id}")
        public List<Room> findByRoomTypeId(@PathVariable Long id){
        return roomService.findByRoomTypeId(id);
        }

        @ApiOperation("delete by roomType id")
        @DeleteMapping("/roomType/id/{id}")
        public int deleteByRoomTypeId(@PathVariable Long id){
        return roomService.deleteByRoomTypeId(id);
        }

        @ApiOperation("find by floor id")
        @GetMapping("/floor/id/{id}")
        public List<Room> findByFloorId(@PathVariable Long id){
        return roomService.findByFloorId(id);
        }

        @ApiOperation("delete by floor id")
        @DeleteMapping("/floor/id/{id}")
        public int deleteByFloorId(@PathVariable Long id){
        return roomService.deleteByFloorId(id);
        }

        @ApiOperation("find by houseKeeping id")
        @GetMapping("/houseKeeping/id/{id}")
        public List<Room> findByHouseKeepingId(@PathVariable Long id){
        return roomService.findByHouseKeepingId(id);
        }

        @ApiOperation("delete by houseKeeping id")
        @DeleteMapping("/houseKeeping/id/{id}")
        public int deleteByHouseKeepingId(@PathVariable Long id){
        return roomService.deleteByHouseKeepingId(id);
        }





            @PutMapping("/archiver/")
            public RoomVo archiver(@RequestBody RoomVo roomVo){
                Room room = roomService.archiver(roomConverter.toItem(roomVo));
                return roomConverter.toVo(room);
                }

            @PutMapping("/desarchiver/")
            public RoomVo desarchiver(@RequestBody RoomVo roomVo){
                Room room = roomService.desarchiver(roomConverter.toItem(roomVo));
                return roomConverter.toVo(room);}
            }
