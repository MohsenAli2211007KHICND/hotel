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
    public void canPostANewHotel() throws Exception{
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
                .andExpect(jsonPath("$.price", is(hotel.getPrice())))  
                .andExpect(jsonPath("$.id", is(hotel.getId())))
                .andExpect(jsonPath("$.pool", is(hotel.getPool())))  
                .andExpect(jsonPath("$.experienceLevel", is(hotel.getExperienceLevel())))
                .andExpect(jsonPath("$.img", is(hotel.getImg())))  
                .andExpect(jsonPath("$.location", is(hotel.getLocation())))
                .andExpect(jsonPath("$.longDescription", is(hotel.getLongDescription())))  
                .andExpect(jsonPath("$.shortDescription", is(hotel.getShortDescription())));
    }

}
