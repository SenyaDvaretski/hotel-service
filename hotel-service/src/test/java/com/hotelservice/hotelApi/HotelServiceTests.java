package com.hotelservice.hotelApi;

import com.hotelservice.hotelApi.DTO.HotelDTO;
import com.hotelservice.hotelApi.constant.HotelType;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.mappers.HotelListMapper;
import com.hotelservice.hotelApi.mappers.HotelListMapperImpl;
import com.hotelservice.hotelApi.mappers.HotelMapper;
import com.hotelservice.hotelApi.mappers.HotelMapperImpl;
import com.hotelservice.hotelApi.model.Hotel;
import com.hotelservice.hotelApi.repository.HotelRepository;
import com.hotelservice.hotelApi.service.HotelService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class HotelServiceTests {

	//inject all repositories as mocks (@BeforeEach) and test service
	@Mock
	HotelRepository hotelRepository;

	@Autowired
	HotelListMapper hotelListMapper = new HotelListMapperImpl();
	@Autowired
	HotelMapper hotelMapper = new HotelMapperImpl();

	MockitoSession session;

	@BeforeEach
	public void beforeEach(){
		session = Mockito.mockitoSession()
				.initMocks(this)
				.startMocking();
	}

	@Test
	public void addHotelTest() {

		Hotel hotel = new Hotel();
		hotel.setId(UUID.randomUUID()).setName("Hotel").setType(HotelType.DEFAULT)
				.setDescription("description").setAddress("address");
		when(hotelRepository.save(argThat(arg -> arg.getName().equals("Hotel")))).thenReturn(hotel);
		HotelService hotelService = new HotelService(hotelRepository, hotelListMapper, hotelMapper);
		ResponseEntity<HotelDTO> respHotel = hotelService.addHotel(hotelMapper.toDTO(hotel));

		assertEquals(hotelMapper.toDTO(hotel), respHotel.getBody());
	}

	@Test
	public void getHotelTest() throws CommonException {
		Hotel hotel = new Hotel();
		hotel.setId(UUID.randomUUID()).setName("Hotel").setType(HotelType.DEFAULT)
				.setDescription("description").setAddress("address");
		Optional<Hotel> optHotel = Optional.of(hotel);
		when(hotelRepository.findHotelByName("Hotel")).thenReturn(optHotel);
		HotelService hotelService = new HotelService(hotelRepository, hotelListMapper, hotelMapper);
		ResponseEntity<HotelDTO> respHotel = hotelService.getHotel("Hotel");

		assertEquals(hotelMapper.toDTO(hotel), respHotel.getBody());
	}

	@Test
	public void getHotelThrowExceptionWhenHotelNotFoundTest() {
		CommonException ex = assertThrows(CommonException.class,
			() -> {
				when(hotelRepository.findHotelByName("Hotel")).thenReturn(Optional.empty());
				HotelService hotelService = new HotelService(hotelRepository, hotelListMapper, hotelMapper);
				ResponseEntity<HotelDTO> respHotel = hotelService.getHotel("Hotel");
			}
		);
		assertNotNull(ex);
	}

	@Test
	public void getAllHotelsTest() throws CommonException {
		Hotel hotel = new Hotel();
		hotel.setId(UUID.randomUUID()).setName("Hotel").setType(HotelType.DEFAULT)
				.setDescription("description").setAddress("address");
		List<Hotel> hotelList = new ArrayList<Hotel>(Arrays.asList(hotel));
		when(hotelRepository.findAll()).thenReturn(hotelList);
		HotelService hotelService = new HotelService(hotelRepository, hotelListMapper, hotelMapper);
		ResponseEntity<List<HotelDTO>> respHotel = hotelService.getAllHotels();

		assertEquals(hotelListMapper.toDTOList(hotelList), respHotel.getBody());
	}

	@Test
	public void getAllHotelsThrowsExceptionWhenNoHotelsFoundTest() {
		CommonException ex = assertThrows(CommonException.class,
				() -> {
					when(hotelRepository.findAll()).thenReturn(new ArrayList<>());
					HotelService hotelService = new HotelService(hotelRepository, hotelListMapper, hotelMapper);
					ResponseEntity<List<HotelDTO>> respHotel = hotelService.getAllHotels();
				}
		);
		assertNotNull(ex);
	}

	@Test
	public void getAllHotelsByAddressTest() throws CommonException {
		Hotel hotel = new Hotel();
		hotel.setId(UUID.randomUUID()).setName("Hotel").setType(HotelType.DEFAULT)
				.setDescription("description").setAddress("address");
		List<Hotel> hotelList = new ArrayList<Hotel>(Arrays.asList(hotel));
		when(hotelRepository.findAllHotelsByAddress("address")).thenReturn(hotelList);
		HotelService hotelService = new HotelService(hotelRepository, hotelListMapper, hotelMapper);
		ResponseEntity<List<HotelDTO>> respHotel = hotelService.getAllHotelsByAddress("address");

		assertEquals(hotelListMapper.toDTOList(hotelList), respHotel.getBody());
	}

	@Test
	public void getAllHotelsByAddressThrowsExceptionWhenNoHotelsFoundTest() {
		CommonException ex = assertThrows(CommonException.class,
				() -> {
					when(hotelRepository.findAllHotelsByAddress("address")).thenReturn(new ArrayList<>());
					HotelService hotelService = new HotelService(hotelRepository, hotelListMapper, hotelMapper);
					ResponseEntity<List<HotelDTO>> respHotel = hotelService.getAllHotelsByAddress("address");
				}
		);
		assertNotNull(ex);
	}

	@Test
	public void deleteHotelTest() throws CommonException {
		Hotel hotel = new Hotel();
		hotel.setId(UUID.randomUUID()).setName("Hotel").setType(HotelType.DEFAULT)
				.setDescription("description").setAddress("address");
		doNothing().when(hotelRepository).delete(hotel);
		when(hotelRepository.findHotelByName("Hotel")).thenReturn(Optional.of(hotel));
		HotelService hotelService = new HotelService(hotelRepository, hotelListMapper, hotelMapper);
		ResponseEntity<HotelDTO> respHotel = hotelService.deleteHotel("Hotel");

		assertEquals(hotelMapper.toDTO(hotel), respHotel.getBody());
	}

	@Test
	public void deleteHotelThrowsExceptionWhenHotelNotFoundTest() {
		CommonException ex = assertThrows(CommonException.class,
				() -> {
					when(hotelRepository.findHotelByName("Hotel")).thenReturn(Optional.empty());
					HotelService hotelService = new HotelService(hotelRepository, hotelListMapper, hotelMapper);
					hotelService.deleteHotel("Hotel");
				}
		);
		assertNotNull(ex);
	}

	@Test
	public void updateHotelTest() throws CommonException {
		Hotel hotel = new Hotel();
		hotel.setId(UUID.randomUUID()).setName("Hotel").setType(HotelType.DEFAULT)
				.setDescription("description").setAddress("address");
		HotelDTO hotelDTO = hotelMapper.toDTO(hotel);
		doNothing().when(hotelRepository).delete(hotel);
		when(hotelRepository.save(hotel)).thenReturn(hotel);
		when(hotelRepository.findHotelByName("Hotel")).thenReturn(Optional.of(hotel));
		HotelService hotelService = new HotelService(hotelRepository, hotelListMapper, hotelMapper);
		ResponseEntity<HotelDTO> respHotel = hotelService.updateHotel(hotelDTO);

		assertEquals(hotelDTO, respHotel.getBody());
	}

	@Test
	public void updateHotelThrowsExceptionWhenHotelNotFoundTest() {
		CommonException ex = assertThrows(CommonException.class,
				() -> {
					Hotel hotel = new Hotel();
					hotel.setId(UUID.randomUUID()).setName("Hotel").setType(HotelType.DEFAULT)
							.setDescription("description").setAddress("address");
					when(hotelRepository.findHotelByName("Hotel")).thenReturn(Optional.empty());
					HotelService hotelService = new HotelService(hotelRepository, hotelListMapper, hotelMapper);
					hotelService.updateHotel(hotelMapper.toDTO(hotel));
				}
		);
		assertNotNull(ex);
	}

	@Test
	public void addTagToHotelTest() throws CommonException {
		Hotel hotel = new Hotel();
		hotel.setId(UUID.randomUUID()).setName("Hotel").setType(HotelType.DEFAULT)
				.setDescription("description").setAddress("address");
		Set<String> tags = new HashSet<>(Arrays.asList("tag"));
		doNothing().when(hotelRepository).addTagToHotel(hotel.getId(), "tag");
		when(hotelRepository.findHotelByName("Hotel")).thenReturn(Optional.of(hotel), Optional.of(hotel.setTags(tags)));
		HotelService hotelService = new HotelService(hotelRepository, hotelListMapper, hotelMapper);
		ResponseEntity<HotelDTO> respHotel = hotelService.addTagToHotel("Hotel", "tag");

	//	hotel.setTags(tags);
		assertEquals(hotelMapper.toDTO(hotel), respHotel.getBody());
	}

	@Test
	public void addTagToHotelThrowsExceptionWhenHotelNotFoundTest() {
		CommonException ex = assertThrows(CommonException.class,
				() -> {
					Hotel hotel = new Hotel();
					hotel.setId(UUID.randomUUID()).setName("Hotel").setType(HotelType.DEFAULT)
							.setDescription("description").setAddress("address");
					Set<String> tags = new HashSet<>(Arrays.asList("tag"));
					when(hotelRepository.findHotelByName("Hotel")).thenReturn(Optional.empty(), Optional.of(hotel.setTags(tags)));
					HotelService hotelService = new HotelService(hotelRepository, hotelListMapper, hotelMapper);
					ResponseEntity<HotelDTO> respHotel = hotelService.addTagToHotel("Hotel", "tag");
				}
		);
		assertNotNull(ex);
	}

	@Test
	public void getAllHotelsByTagsTest() throws CommonException {
		Set<String> tagSet = new HashSet<>(Arrays.asList("tag1", "tag2"));
		Hotel hotel = new Hotel();
		hotel.setId(UUID.randomUUID()).setName("Hotel").setType(HotelType.DEFAULT)
				.setDescription("description").setAddress("address").setTags(tagSet);
		List<Hotel> hotelList = new ArrayList<Hotel>(Arrays.asList(hotel));
		when(hotelRepository.getAllHotelsByTags(tagSet)).thenReturn(hotelList);
		HotelService hotelService = new HotelService(hotelRepository, hotelListMapper, hotelMapper);
		ResponseEntity<List<HotelDTO>> respHotel = hotelService.getAllHotelsByTags(tagSet);

		assertEquals(hotelListMapper.toDTOList(hotelList), respHotel.getBody());
	}

	@Test
	public void getAllHotelsByTagsThrowsExceptionWhenHotelNotFoundTest() {
		CommonException ex = assertThrows(CommonException.class,
				() -> {
					Set<String> tags = new HashSet<>(Arrays.asList("tag"));
					when(hotelRepository.getAllHotelsByTags(tags)).thenReturn(new ArrayList<Hotel>());
					HotelService hotelService = new HotelService(hotelRepository, hotelListMapper, hotelMapper);
					ResponseEntity<List<HotelDTO>> respHotel = hotelService.getAllHotelsByTags(tags);
				}
		);
		assertNotNull(ex);
	}

	@AfterEach
	void afterEach(){
		session.finishMocking();
	}
}
