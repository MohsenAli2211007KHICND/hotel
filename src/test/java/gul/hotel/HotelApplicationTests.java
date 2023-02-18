package gul.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.support.Repositories;

@SpringBootTest
class HotelApplicationTests {

	@Mock
	private HotelRepository hotelRepository;

	@InjectMocks
	private HotelServiceImpl hotelServiceImpl;



	/*
	 * Stories
	 * 1) As a traveler, I can enter my search criteria and see a list of hotels
	 * that match my preferences so that I can book a stay for my trip.
	 * a) AC1: The search form should have a drop down for the city (Karachi,
	 * Islamabad,
	 * Lahore), a drop down for the experience level (Budget, Business, Luxury),and
	 * a
	 * check box for “Pool required”. The form should have a button that says
	 * “Search”
	 * that will display a list of matching hotels.
	 * b) AC2: Each hotel should be displayed on its own line. Each line should have
	 * a
	 * thumbnail image of the hotel, the name of the hotel, the short description of
	 * the
	 * hotel, the city of the hotel, whether it has a pool, its experience level,
	 * the price of
	 * the hotel per night and a “Book Now” button.
	 * c) AC3: The product name will be linked to a Product Detail page that shows
	 * more
	 * information about the product.
	 * d) AC4: The search fields will remain at the top of the page so I can change
	 * their
	 * values and update my results.
	 * 2) As a traveler, when I click the name of a hotel on the search results page
	 * I will be
	 * redirected to the Hotel Detail page for that hotel.
	 * a) AC1: Clicking the name of the hotel redirects the user to the hotel detail
	 * page.
	 * b) AC2: The hotel detail page will show a large hotel image, the name of the
	 * hotel,
	 * its long description, the city it is in, its experience level, whether it has
	 * a pool and
	 * its price per night, and a “Book Now” button.
	 * c) AC3: The hotel detail page will have a “Return to Results” button that
	 * will take me
	 * back to my list of results.
	 * 3) As a traveler, when I click the “Book Now” button on the search results or
	 * Hotel Detail
	 * page, the booking form is displayed where the traveler can enter their
	 * information,
	 * review the price and book their stay..
	 * a) AC1: Clicking “Book Now” will display a form where the user can enter
	 * their
	 * name, address and email address, and the dates of their stay.
	 * b) AC2: When the traveler enters the dates of their stay the page will
	 * display the
	 * “Total Price” which is equal to the daily room rate times the number of
	 * nights in
	 * their stay times 12% for tax. A separate “Tax” field will show the amount of
	 * the
	 * tax.
	 * c) AC3: A button on the booking form labeled “Book My Stay” will take the
	 * traveler
	 * to a confirmation page that says “Your stay has been booked.” and provides
	 * them
	 * with a summary of the important information.
	 * d) AC4: The booking form will have a “Cancel” button that will return me to
	 * the main
	 * search page.
	 * e) AC5: The booking confirmation page will have a “Start Over” button that
	 * will
	 * return me to the main search page.
	 */
	@Test
	void contextLoads() {
	}

	@Test
	void getAndSetHotelId() {
		Hotel hotel = new Hotel();
		Long myId = 1L;
		hotel.setId(myId);
		assertEquals(myId, hotel.getId());
	}

	@Test
	void getAndSetHotelName() {
		Hotel hotel = new Hotel();
		String hotelName = "PC Hotel";
		hotel.setHotelName(hotelName);
		assertEquals(hotelName, hotel.getHotelName());
	}

	@Test
	void getAndSetHotelShortDescription() {
		Hotel hotel = new Hotel();
		String shortDescription = "This is short description";
		hotel.setShortDescription(shortDescription);
		assertEquals(shortDescription, hotel.getShortDescription());
	}

	@Test
	void getAndSetHotelLongDescription() {
		Hotel hotel = new Hotel();
		String longDescription = "This is long description";
		hotel.setLongDescription(longDescription);
		assertEquals(longDescription, hotel.getLongDescription());
	}

	@Test
	void getAndSetHotelImg() {
		Hotel hotel = new Hotel();
		String hotelImg = "this is hotel image";
		hotel.setImg(hotelImg);
		assertEquals(hotelImg, hotel.getImg());
	}

	@Test
	void getAndSetHotelLocation() {
		Hotel hotel = new Hotel();
		String hotelLocation = "Karachi";
		hotel.setLocation(hotelLocation);
		assertEquals(hotelLocation, hotel.getLocation());
	}

	@Test
	void getAndSetHotelExperience() {
		Hotel hotel = new Hotel();
		String hotelExperience = "Luxury";
		hotel.setExperienceLevel(hotelExperience);
		assertEquals(hotelExperience, hotel.getExperienceLevel());
	}

	@Test
	void getAndSetHotelPrice() {
		Hotel hotel = new Hotel();
		Long price = 5000L;
		hotel.setPrice(price);
		assertEquals(price, hotel.getPrice());
	}

	@Test
	void getAndSetHotelPool() {
		Hotel hotel = new Hotel();
		String hotelPool = "Yes";
		hotel.setPool(hotelPool);
		assertEquals(hotelPool, hotel.getPool());
	}

	@Test
	void getAllArgsConstructorHotel() {
		Long id = 1L;
		String hotleName = "PC hotel";
		String shortDescription = "this is short description";
		String longDescription = "this is long description";
		String img = "image of hotel";
		String location = "Karachi";
		String experienceLevel = "Luxury";
		String pool = "No";
		Long price = 4000L;
		Hotel hotel = new Hotel(id, hotleName, shortDescription, longDescription, img, location, experienceLevel, pool,
				price);
		assertEquals(id, hotel.getId());
		assertEquals(hotleName, hotel.getHotelName());
		assertEquals(shortDescription, hotel.getShortDescription());
		assertEquals(longDescription, hotel.getLongDescription());
		assertEquals(img, hotel.getImg());
		assertEquals(location, hotel.getLocation());
		assertEquals(experienceLevel, hotel.getExperienceLevel());
		assertEquals(pool, hotel.getPool());
		assertEquals(price, hotel.getPrice());
	}

	@Test
	void getBuilderHotel(){
		Long id = 1L;
		String hotleName = "PC hotel";
		String shortDescription = "this is short description";
		String longDescription = "this is long description";
		String img = "image of hotel";
		String location = "Karachi";
		String experienceLevel = "Luxury";
		String pool = "No";
		Long price = 4000L;
		Hotel hotel = Hotel.builder()
							.id(id)
							.hotelName(hotleName)
							.shortDescription(shortDescription)
							.longDescription(longDescription)
							.img(img)
							.location(location)
							.experienceLevel(experienceLevel)
							.pool(pool)
							.price(price)
							.build();
		assertEquals(id, hotel.getId());
		assertEquals(hotleName, hotel.getHotelName());
		assertEquals(shortDescription, hotel.getShortDescription());
		assertEquals(longDescription, hotel.getLongDescription());
		assertEquals(img, hotel.getImg());
		assertEquals(location, hotel.getLocation());
		assertEquals(experienceLevel, hotel.getExperienceLevel());
		assertEquals(pool, hotel.getPool());
		assertEquals(price, hotel.getPrice());							
	}

	@Test
	void canSaveHotelDetails(){
		Long id = 1L;
		String hotleName = "PC hotel";
		String shortDescription = "this is short description";
		String longDescription = "this is long description";
		String img = "image of hotel";
		String location = "Karachi";
		String experienceLevel = "Luxury";
		String pool = "No";
		Long price = 4000L;
		Hotel hotel = Hotel.builder()
							.id(id)
							.hotelName(hotleName)
							.shortDescription(shortDescription)
							.longDescription(longDescription)
							.img(img)
							.location(location)
							.experienceLevel(experienceLevel)
							.pool(pool)
							.price(price)
							.build();
		given(hotelRepository.findByHotelName(hotleName)).willReturn(Optional.empty());
		given(hotelRepository.save(hotel)).willReturn(hotel);
		Hotel saved = hotelServiceImpl.saveHotel(hotel);
		assertNotNull(saved);
	}
	@Test
	void canGetHotel(){
		Long id = 1L;
		String hotleName = "PC hotel";
		String shortDescription = "this is short description";
		String longDescription = "this is long description";
		String img = "image of hotel";
		String location = "Karachi";
		String experienceLevel = "Luxury";
		String pool = "No";
		Long price = 4000L;
		Hotel hotel = Hotel.builder()
							.id(id)
							.hotelName(hotleName)
							.shortDescription(shortDescription)
							.longDescription(longDescription)
							.img(img)
							.location(location)
							.experienceLevel(experienceLevel)
							.pool(pool)
							.price(price)
							.build();
		given(hotelRepository.getReferenceById(hotel.getId())).willReturn(hotel);
		Hotel gottenHotel = hotelServiceImpl.getHotel(hotel.getId());
		assertNotNull(gottenHotel);
		assertEquals(gottenHotel.getId(), id);
		

	}
}
