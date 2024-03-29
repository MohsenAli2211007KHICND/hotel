package gul.hotel;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.Invocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

// import static org.mockito.ArgumentMatcher.*;
// import static org.mockito.BDDMockito.*;
// import static org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder.*;
// import static org.springframework.test.web.servlet.result.ContentResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class HotelControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HotelService hotelService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void canPostANewHotel() throws Exception {
        Hotel hotel = Hotel.builder()
                .hotelName("PC Hotel")
                .shortDescription("This is Pc hotel")
                .longDescription("it is long description of Pc hotel")
                .img("img")
                .location("Karchi")
                .experienceLevel("Budget")
                .pool("No")
                .price(2356L)
                .build();
        given(hotelService.saveHotel(any(Hotel.class))).willAnswer(invocation -> invocation.getArgument(0));
        ResultActions response = mockMvc.perform(post("/api/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hotel)));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.hotelName", is(hotel.getHotelName())))
                .andExpect(jsonPath("$.price", is(hotel.getPrice().intValue())))
                .andExpect(jsonPath("$.id", is(hotel.getId())))
                .andExpect(jsonPath("$.pool", is(hotel.getPool())))
                .andExpect(jsonPath("$.experienceLevel", is(hotel.getExperienceLevel())))
                .andExpect(jsonPath("$.img", is(hotel.getImg())))
                .andExpect(jsonPath("$.location", is(hotel.getLocation())))
                .andExpect(jsonPath("$.longDescription", is(hotel.getLongDescription())))
                .andExpect(jsonPath("$.shortDescription", is(hotel.getShortDescription())));
    }

    @Test
    public void canGetAllHotels() throws Exception {
        Hotel hotel1 = Hotel.builder()
                .hotelName("PC Hotel")
                .shortDescription("This is Pc hotel")
                .longDescription("it is long description of Pc hotel")
                .img("img")
                .location("Karchi")
                .experienceLevel("Budget")
                .pool("No")
                .price(2356L)
                .build();
        Hotel hotel2 = Hotel.builder()
                .hotelName("DC Hotel")
                .shortDescription("this is Dc hotel")
                .longDescription("This is long description of Dc hotel")
                .img("image")
                .location("Quetta")
                .experienceLevel("Luxury")
                .pool("Yes")
                .price(4568L)
                .build();
        Hotel hotel3 = Hotel.builder()
                .hotelName("GC hotel")
                .shortDescription("this is GC hotel")
                .longDescription("This is long description of GC hotel")
                .img("image")
                .location("Turbat")
                .experienceLevel("Luxury")
                .pool("No")
                .price(5555L)
                .build();
        Hotel hotel4 = Hotel.builder()
                .hotelName("Kaka")
                .shortDescription("This is Kaka hotel")
                .longDescription("This is long description")
                .img("image")
                .location("Buleda")
                .experienceLevel("budget")
                .pool("No")
                .price(3000L)
                .build();
        List<Hotel> hotels = List.of(hotel1, hotel2, hotel3, hotel4);
        given(hotelService.getAllHotels()).willReturn(hotels);
        ResultActions response = mockMvc.perform(get("/api/hotels/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hotels)));

        response.andExpect(status().isOk());
        response.andDo(print())
                .andExpect(jsonPath("$.size()", is(hotels.size())));
    }

    @Test
    public void canGetAHotel() throws Exception {
        Long hotelId = 1L;
        Hotel hotel = Hotel.builder()
                .id(hotelId)
                .hotelName("PC Hotel")
                .shortDescription("This is Pc hotel")
                .longDescription("it is long description of Pc hotel")
                .img("img")
                .location("Karchi")
                .experienceLevel("Budget")
                .pool("No")
                .price(2356L)
                .build();
        given(hotelService.getHotel(hotelId)).willReturn(hotel);
        ResultActions response = mockMvc.perform(get("/api/hotels/{id}", hotelId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hotel)));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(hotel.getId().intValue())))
                .andExpect(jsonPath("$.hotelName", is(hotel.getHotelName())))
                .andExpect(jsonPath("$.price", is(hotel.getPrice().intValue())))
                .andExpect(jsonPath("$.pool", is(hotel.getPool())))
                .andExpect(jsonPath("$.experienceLevel", is(hotel.getExperienceLevel())))
                .andExpect(jsonPath("$.img", is(hotel.getImg())))
                .andExpect(jsonPath("$.location", is(hotel.getLocation())))
                .andExpect(jsonPath("$.longDescription", is(hotel.getLongDescription())))
                .andExpect(jsonPath("$.shortDescription", is(hotel.getShortDescription())));
    }

    @Test
    public void cantGetAHotelIfItDoesNotExistInDataBase() throws Exception {
        Long hotelId = 1L;
        given(hotelService.getHotel(hotelId)).willReturn(new Hotel());
        ResultActions response = mockMvc.perform(get("/api/hotels/{id}", hotelId));
        response.andExpect(status().isNotFound());
    }

    @Test
    public void canUpdatAHotel() throws Exception {
        Long hotelId = 1L;
        Hotel savedHotel = Hotel.builder()
                .hotelName("PC Hotel")
                .shortDescription("This is Pc hotel")
                .longDescription("it is long description of Pc hotel")
                .img("img")
                .location("Karchi")
                .experienceLevel("Budget")
                .pool("No")
                .price(2356L)
                .build();
        Hotel updateHotel = Hotel.builder()
                .id(hotelId)
                .hotelName("GC hotel")
                .shortDescription("this is GC hotel")
                .longDescription("This is long description of GC hotel")
                .img("image")
                .location("Turbat")
                .experienceLevel("Luxury")
                .pool("No")
                .price(5555L)
                .build();
        given(hotelService.getHotel(hotelId)).willReturn(savedHotel);
        given(hotelService.updateHotel(any(Hotel.class))).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/api/hotels/update/{id}", hotelId, updateHotel)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateHotel)));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(updateHotel.getId().intValue())))
                .andExpect(jsonPath("$.hotelName", is(updateHotel.getHotelName())))
                .andExpect(jsonPath("$.price", is(updateHotel.getPrice().intValue())))
                .andExpect(jsonPath("$.pool", is(updateHotel.getPool())))
                .andExpect(jsonPath("$.experienceLevel", is(updateHotel.getExperienceLevel())))
                .andExpect(jsonPath("$.img", is(updateHotel.getImg())))
                .andExpect(jsonPath("$.location", is(updateHotel.getLocation())))
                .andExpect(jsonPath("$.longDescription", is(updateHotel.getLongDescription())))
                .andExpect(jsonPath("$.shortDescription", is(updateHotel.getShortDescription())));
    }

    @Test
    public void cantUpdateAHotelIfItDoesNotExist() throws Exception {
        Long hotelId = 1L;
        Hotel updateHotel = Hotel.builder()
                .id(hotelId)
                .hotelName("GC hotel")
                .shortDescription("this is GC hotel")
                .longDescription("This is long description of GC hotel")
                .img("image")
                .location("Turbat")
                .experienceLevel("Luxury")
                .pool("No")
                .price(5555L)
                .build();
        given(hotelService.getHotel(hotelId)).willReturn(new Hotel());
        given(hotelService.updateHotel(updateHotel))
                .willThrow(new InvalidConfigurationPropertyValueException("Name", updateHotel.getHotelName(),
                        "A hotel name" + updateHotel.getHotelName() + " does not exist in the database."));
        ResultActions response = mockMvc.perform(put("/api/hotels/update/{id}", hotelId, updateHotel)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateHotel)));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    public void canDeleteAHotel() throws Exception {
        Long hotelId = 1L;
        willDoNothing().given(hotelService).deleteHotel(hotelId);

        ResultActions response = mockMvc.perform(delete("/api/hotels/delete/{id}", hotelId));
        response.andExpect(status().isOk()).andDo(print());
        verify(hotelService,times(1)).deleteHotel(hotelId);
    }
    @Test
    public void canGetHotelBySearch() throws Exception{
        String location = "Karachi";
        String experienceLevel = "luxury";
        String pool = "yes";
        Hotel hotel1 = Hotel.builder()
                                .id(1L)
                                .hotelName("Kaka")
                                .shortDescription("This is Kaka hotel")
                                .longDescription("This is long description")
                                .img("image")
                                .location(location)
                                .experienceLevel(experienceLevel)
                                .pool(pool)
                                .price(3000L)
                                .build();
        Hotel hotel2 = Hotel.builder()
                                .id(1L)
                                .hotelName("ZJ")
                                .shortDescription("This is ZJ hotel")
                                .longDescription("This is long description")
                                .img("image")
                                .location(location)
                                .experienceLevel(experienceLevel)
                                .pool(pool)
                                .price(3000L)
                                .build();
        List<Hotel> hotels = List.of(hotel1, hotel2);
        given(hotelService.getAllBySearchInputs(location, experienceLevel, pool)).willReturn(hotels);
        ResultActions response = mockMvc.perform(get("/api/hotels/search", location, experienceLevel, pool)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hotels)));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(hotels.size())));

    }
} 
