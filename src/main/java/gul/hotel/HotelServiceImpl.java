package gul.hotel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public Hotel updateHotel(Hotel hotel){
        Optional<Hotel> savedHotel = hotelRepository.findByHotelName(hotel.getHotelName());
        if(savedHotel.isEmpty()){
            throw new InvalidConfigurationPropertyValueException("Name",hotel.getHotelName(), "Hotel name"+hotel.getHotelName()+" is not exist in database");
        }
        return hotelRepository.save(hotel);

    }
    public void deleteHotel(Long id){
        hotelRepository.deleteById(id);
    }
    public List<Hotel> getAllHotels(){
        return hotelRepository.findAll();
    }
    public List<Hotel> getAllBySearchInputs(String location, String experienceLevel, String pool){
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream().filter(hotel -> hotel.getLocation().equalsIgnoreCase(location)
                    && hotel.getExperienceLevel().equalsIgnoreCase(experienceLevel) &&
                     hotel.getPool().equalsIgnoreCase(pool)).collect(Collectors.toList());
    

    }
    
}
