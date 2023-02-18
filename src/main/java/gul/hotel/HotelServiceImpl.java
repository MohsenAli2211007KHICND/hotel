package gul.hotel;

import java.util.Optional;

import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository){
        this.hotelRepository = hotelRepository;
    }

    public Hotel saveHotel(Hotel hotel) {
        Optional<Hotel> savedHotel = hotelRepository.findByHotelName(hotel.getHotelName());
        if(savedHotel.isPresent()){
            throw new InvalidConfigurationPropertyValueException("Name", hotel.getHotelName(), "Hotel name"+hotel.getHotelName()+" is already exist in database");
        }
        return hotelRepository.save(hotel);
    }

    public Hotel getHotel(Long id){
        return hotelRepository.getReferenceById(id);
    }

    
}
