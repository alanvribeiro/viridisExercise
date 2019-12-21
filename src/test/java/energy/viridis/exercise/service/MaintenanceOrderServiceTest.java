package energy.viridis.exercise.service;

import java.util.Date;

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
import energy.viridis.exercise.model.MaintenanceOrder;
import energy.viridis.exercise.repository.MaintenanceOrderRepository;
import energy.viridis.exercise.service.impl.MaintenanceOrderServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class MaintenanceOrderServiceTest {
	
	@SpyBean
	MaintenanceOrderServiceImpl maintenanceOrderServiceImpl;
	
	@MockBean
	MaintenanceOrderRepository maintenanceOrderRepository;
	
	@Test
	public void createMaintenanceOrderSuccessfully() {
		
		//scenario
		
		Mockito.doNothing().when(maintenanceOrderServiceImpl).validateId(Mockito.any(MaintenanceOrder.class));
		
		MaintenanceOrder maintenanceOrder = MaintenanceOrder.builder().equipment(new Equipment(1l)).scheduledDate(new Date()).build();
				
		Mockito.when(maintenanceOrderRepository.save(Mockito.any(MaintenanceOrder.class))).thenReturn(maintenanceOrder);
		
		//action 
		
		MaintenanceOrder criadedMaintenanceOrder = maintenanceOrderRepository.save(maintenanceOrder);
		
		//verification
		
		Assertions.assertThat(criadedMaintenanceOrder).isNotNull();
		Assertions.assertThat(criadedMaintenanceOrder.getEquipment()).isEqualTo(maintenanceOrder.getEquipment());
		Assertions.assertThat(criadedMaintenanceOrder.getScheduledDate()).isEqualTo(maintenanceOrder.getScheduledDate());
		
	}
	
	@Test
	public void createEquipmentError() {
		
		//scenario

		MaintenanceOrder maintenanceOrder = MaintenanceOrder.builder().equipment(null).scheduledDate(null).build();
		
		Mockito.doThrow(BusinessRuleException.class).when(maintenanceOrderServiceImpl).validateMaintenanceOrder(maintenanceOrder);

		//action 
		
		MaintenanceOrder criadedMaintenanceOrder = maintenanceOrderRepository.save(maintenanceOrder);
		
		//verification
		
		Mockito.verify(maintenanceOrderRepository, Mockito.never()).save(criadedMaintenanceOrder);
		
	}
	
	@Test
	public void updateEquipmentSuccessfully() {
		
		//scenario
		
		Mockito.doNothing().when(maintenanceOrderServiceImpl).validateId(Mockito.any(MaintenanceOrder.class));
		
		Mockito.doNothing().when(maintenanceOrderServiceImpl).validateMaintenanceOrder(Mockito.any(MaintenanceOrder.class));
		
		MaintenanceOrder maintenanceOrder = MaintenanceOrder.builder().id(1l).equipment(new Equipment(1l)).scheduledDate(new Date()).build();
		
		Mockito.when(maintenanceOrderRepository.save(Mockito.any(MaintenanceOrder.class))).thenReturn(maintenanceOrder);
		
		//action 
		
		MaintenanceOrder criadedMaintenanceOrder = maintenanceOrderRepository.save(maintenanceOrder);
		
		//verification
		
		Assertions.assertThat(criadedMaintenanceOrder).isNotNull();
		Assertions.assertThat(criadedMaintenanceOrder.getId()).isEqualTo(maintenanceOrder.getId());
		Assertions.assertThat(criadedMaintenanceOrder.getEquipment()).isEqualTo(maintenanceOrder.getEquipment());
		Assertions.assertThat(criadedMaintenanceOrder.getScheduledDate()).isEqualTo(maintenanceOrder.getScheduledDate());
		
	}
	
	@Test
	public void updateEquipmentError() {
		
		//scenario
		
		MaintenanceOrder maintenanceOrder = MaintenanceOrder.builder().id(null).equipment(null).scheduledDate(null).build();

		Mockito.doThrow(BusinessRuleException.class).when(maintenanceOrderServiceImpl).validateId(maintenanceOrder);
		
		Mockito.doThrow(BusinessRuleException.class).when(maintenanceOrderServiceImpl).validateMaintenanceOrder(maintenanceOrder);

		//action 
		
		MaintenanceOrder updatedMaintenanceOrder = maintenanceOrderRepository.save(maintenanceOrder);
		
		//verification
		
		Mockito.verify(maintenanceOrderRepository, Mockito.never()).save(updatedMaintenanceOrder);
		
	}
	
	@Test
	public void deveDeletarUmLancamento() {
		
		//scenario
		
		Mockito.doNothing().when(maintenanceOrderServiceImpl).validateId(Mockito.any(MaintenanceOrder.class));
		
		MaintenanceOrder maintenanceOrder = MaintenanceOrder.builder().id(1l).equipment(new Equipment(1l)).scheduledDate(new Date()).build();
		
		Mockito.doNothing().when(maintenanceOrderServiceImpl).delete(maintenanceOrder);
		
		//action 
		
		maintenanceOrderRepository.delete(maintenanceOrder);
		
		//verification
		
		Mockito.verify(maintenanceOrderRepository).delete(maintenanceOrder);
		
	}
	
	@Test
	public void deleteEquipmentError() {

		//scenario
		
		MaintenanceOrder maintenanceOrder = MaintenanceOrder.builder().id(null).equipment(null).scheduledDate(null).build();

		Mockito.doThrow(BusinessRuleException.class).when(maintenanceOrderServiceImpl).validateId(maintenanceOrder);
		
		Mockito.doThrow(BusinessRuleException.class).when(maintenanceOrderServiceImpl).delete(maintenanceOrder);
		
		//action 
		
		maintenanceOrderRepository.delete(maintenanceOrder);
		
		//verification
		
		Mockito.verify(maintenanceOrderRepository).delete(maintenanceOrder);
		
	}
	
}
