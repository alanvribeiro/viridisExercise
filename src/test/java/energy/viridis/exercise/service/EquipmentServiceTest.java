package energy.viridis.exercise.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import energy.viridis.exercise.error.BusinessRuleException;
import energy.viridis.exercise.model.Equipment;
import energy.viridis.exercise.repository.EquipmentRepository;
import energy.viridis.exercise.service.impl.EquipmentServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EquipmentServiceTest {
	
	@SpyBean
	EquipmentServiceImpl equipmentServiceImpl;
	
	@MockBean
	EquipmentRepository equipmentRepository;
	
	@Test
	public void createEquipmentSuccessfully() {
		
		//scenario
		
		Mockito.doNothing().when(equipmentServiceImpl).validateEquipment(Mockito.any(Equipment.class));
		
		Equipment equipment = Equipment.builder().name("name").build();
		
		Mockito.when(equipmentRepository.save(Mockito.any(Equipment.class))).thenReturn(equipment);
		
		//action 
		
		Equipment criadedEquipment = equipmentRepository.save(equipment);
		
		//verification
		
		Assertions.assertThat(criadedEquipment).isNotNull();
		Assertions.assertThat(criadedEquipment.getName()).isEqualTo("name");
		
	}
	
	@Test
	public void createEquipmentError() {
		
		//scenario

		Equipment equipment = Equipment.builder().name(null).build();
		
		Mockito.doThrow(BusinessRuleException.class).when(equipmentServiceImpl).validateEquipment(equipment);

		//action 
		
		Equipment criadedEquipment = equipmentRepository.save(equipment);
		
		//verification
		
		Mockito.verify(equipmentRepository, Mockito.never()).save(criadedEquipment);
		
	}
	
	@Test
	public void updateEquipmentSuccessfully() {
		
		//scenario
		
		Mockito.doNothing().when(equipmentServiceImpl).validateId(Mockito.any(Equipment.class));
		
		Mockito.doNothing().when(equipmentServiceImpl).validateEquipment(Mockito.any(Equipment.class));

		Equipment equipment = Equipment.builder().id(1l).name("portable computer").build();
		
		Mockito.when(equipmentRepository.save(Mockito.any(Equipment.class))).thenReturn(equipment);
		
		//action 
		
		Equipment criadedEquipment = equipmentRepository.save(equipment);
		
		//verification
		
		Assertions.assertThat(criadedEquipment).isNotNull();
		Assertions.assertThat(criadedEquipment.getId()).isEqualTo(equipment.getId());
		Assertions.assertThat(criadedEquipment.getName()).isEqualTo(equipment.getName());
		
	}
	
	@Test
	public void updateEquipmentError() {
		
		//scenario
		
		Equipment equipment = Equipment.builder().id(null).name(null).build();

		Mockito.doThrow(BusinessRuleException.class).when(equipmentServiceImpl).validateId(equipment);
		
		Mockito.doThrow(BusinessRuleException.class).when(equipmentServiceImpl).validateEquipment(equipment);

		//action 
		
		Equipment updatedEquipment = equipmentRepository.save(equipment);
		
		//verification
		
		Mockito.verify(equipmentRepository, Mockito.never()).save(updatedEquipment);
		
	}
	
	@Test
	public void deveDeletarUmLancamento() {
		
		//scenario
		
		Mockito.doNothing().when(equipmentServiceImpl).validateId(Mockito.any(Equipment.class));
		
		Equipment equipment = Equipment.builder().id(1l).name("portable computer").build();
		
		Mockito.doNothing().when(equipmentServiceImpl).delete(equipment);
		
		//action 
		
		equipmentRepository.delete(equipment);
		
		//verification
		
		Mockito.verify(equipmentRepository).delete(equipment);
		
	}
	
	@Test
	public void deleteEquipmentError() {

		//scenario
		
		Equipment equipment = Equipment.builder().id(null).name(null).build();

		Mockito.doThrow(BusinessRuleException.class).when(equipmentServiceImpl).validateId(equipment);
		
		Mockito.doThrow(BusinessRuleException.class).when(equipmentServiceImpl).delete(equipment);
		
		//action 
		
		equipmentRepository.delete(equipment);
		
		//verification
		
		Mockito.verify(equipmentRepository).delete(equipment);
		
	}
	
}
