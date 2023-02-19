package gul.hotel;

import java.util.List;

public interface HotelService {
    Hotel saveHotel(Hotel hotel);
    Hotel getHotel(Long id);
    Hotel updateHotel(Hotel hotel);
    void deleteHotel(Long id);
    List<Hotel> getAllHotels();
    List<Hotel> getAllBySearchInputs(String location, String experienceLevel, String pool);
}
