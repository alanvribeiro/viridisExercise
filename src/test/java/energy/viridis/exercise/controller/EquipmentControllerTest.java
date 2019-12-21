package energy.viridis.exercise.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import energy.viridis.exercise.error.BusinessRuleException;
import energy.viridis.exercise.model.Equipment;
import energy.viridis.exercise.model.dto.EquipmentDTO;
import energy.viridis.exercise.service.EquipmentService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = EquipmentController.class)
@AutoConfigureMockMvc
public class EquipmentControllerTest {
	
	static final String API = "/api/equipment";
	static final MediaType JSON = MediaType.APPLICATION_JSON;
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	EquipmentService equipmentService;
	
	@Test
	public void createEquipmentStatusOk() throws Exception {
		
		//scenario
		
		EquipmentDTO equipmentDTO = EquipmentDTO.builder().id(1l).name("name").build();
		
		Equipment equipment = Equipment.builder().id(equipmentDTO.getId()).name(equipmentDTO.getName()).build();
		
		String jsonEquipmentDTO = new ObjectMapper().writeValueAsString(equipmentDTO);
		
		Mockito.when(equipmentService.create(Mockito.any(Equipment.class))).thenReturn(equipment);
		
		//action 
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API).accept(JSON).contentType(JSON).content(jsonEquipmentDTO);
		
		//verification
		
		mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("id").value(equipment.getId()))
			.andExpect(MockMvcResultMatchers.jsonPath("name").value(equipment.getName()));
		
	}
	
	@Test
	public void createEquipmentStatusBadRequest() throws Exception {
		
		//scenario
		
		EquipmentDTO equipmentDTO = EquipmentDTO.builder().id(1l).name("name").build();
		
		String jsonEquipmentDTO = new ObjectMapper().writeValueAsString(equipmentDTO);
		
		Mockito.when(equipmentService.create(Mockito.any(Equipment.class))).thenThrow(BusinessRuleException.class);
		
		//action 
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API).accept(JSON).contentType(JSON).content(jsonEquipmentDTO);
		
		//verification
		
		mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	public void updateEquipmentStatusOk() throws Exception {
		
		//scenario
		
		EquipmentDTO equipmentDTO = EquipmentDTO.builder().id(1l).name("name").build();
		
		Equipment equipment = Equipment.builder().id(equipmentDTO.getId()).name(equipmentDTO.getName()).build();
		
		String jsonEquipmentDTO = new ObjectMapper().writeValueAsString(equipmentDTO);
		
		Mockito.when(equipmentService.update(Mockito.any(Equipment.class))).thenReturn(equipment);
		
		//action 
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(API).accept(JSON).contentType(JSON).content(jsonEquipmentDTO);
		
		//verification
		
		mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("id").value(equipment.getId()))
			.andExpect(MockMvcResultMatchers.jsonPath("name").value(equipment.getName()));
		
	}
	
	@Test
	public void updateEquipmentStatusBadRequest() throws Exception {
		
		//scenario
		
		EquipmentDTO equipmentDTO = EquipmentDTO.builder().id(1l).name("name").build();
		
		String jsonEquipmentDTO = new ObjectMapper().writeValueAsString(equipmentDTO);
		
		Mockito.when(equipmentService.update(Mockito.any(Equipment.class))).thenThrow(BusinessRuleException.class);
		
		//action 
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(API).accept(JSON).contentType(JSON).content(jsonEquipmentDTO);
		
		//verification
		
		mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	public void deleteEquipmentStatusOk() throws Exception {
		
		//scenario

		Equipment equipment = Equipment.builder().id(1l).name("name").build();
		
		Mockito.doNothing().when(equipmentService).delete(equipment);
		
		//action 
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(API+"/1").accept(JSON).contentType(JSON);
		
		//verification
		
		mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void deleteEquipmentStatusBadRequest() throws Exception {
		
		//scenario
		
		Equipment equipment = Equipment.builder().id(null).name("name").build();
		
		Mockito.doThrow(BusinessRuleException.class).when(equipmentService).delete(equipment);
		
		//action 
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(API+"/name").accept(JSON).contentType(JSON);
		
		//verification
		
		mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
}
