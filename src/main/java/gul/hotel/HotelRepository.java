package gul.hotel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findByHotelName(String hotelName);
    
}
