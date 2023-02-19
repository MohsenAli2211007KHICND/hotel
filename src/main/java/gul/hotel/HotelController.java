package gul.hotel;

import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
}
