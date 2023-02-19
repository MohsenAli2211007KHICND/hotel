package gul.hotel;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    
    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel createNewHotel(@RequestBody Hotel hotel){
        return hotelService.saveHotel(hotel);
    }
    @GetMapping("/all")
    public List<Hotel> getAllHotels(){
        return hotelService.getAllHotels();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getAHotel(@PathVariable Long id ){
        Hotel hotel = hotelService.getHotel(id);
        if(hotel.getId() != null){
            return ResponseEntity.status(HttpStatus.OK).body(hotel);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable("id") Long id, @RequestBody Hotel hotel){
        hotel.setId(id);
        try{
            hotelService.updateHotel(hotel);
            return ResponseEntity.status(HttpStatus.OK).body(hotel);
        }
        catch(Exception error){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Hotel());
        }
}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable("id") Long id){
        hotelService.deleteHotel(id);
        return new ResponseEntity<String>("Hotel deleted successfully!", HttpStatus.OK);
        
    }
    
}

