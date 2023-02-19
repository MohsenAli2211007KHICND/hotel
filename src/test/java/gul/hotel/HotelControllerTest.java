package gul.hotel;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.Invocation;
import org.springframework.beans.factory.annotation.Autowired;
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
                .id(1L)
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
                .id(2L)
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
                .id(3L)
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
                .id(4L)
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

}
