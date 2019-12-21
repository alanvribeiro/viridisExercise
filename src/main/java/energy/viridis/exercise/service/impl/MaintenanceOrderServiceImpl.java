package energy.viridis.exercise.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import energy.viridis.exercise.error.BusinessRuleException;
import energy.viridis.exercise.model.MaintenanceOrder;
import energy.viridis.exercise.repository.MaintenanceOrderRepository;
import energy.viridis.exercise.service.MaintenanceOrderService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MaintenanceOrderServiceImpl implements MaintenanceOrderService {

	@Autowired
	private MaintenanceOrderRepository maintenanceOrderRepository;
	
	@Override
	public MaintenanceOrder get(Long id) {

		log.info("Retrieving Maintenance Order - id: {}", id);
		return maintenanceOrderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Maintenance Order not found."));

	}

	@Override
	public List<MaintenanceOrder> getAll() {

		log.info("Listing all Maintenance Orders...");
		return maintenanceOrderRepository.findAll();

	}

	@Override
	public MaintenanceOrder create(MaintenanceOrder maintenanceOrder) {
		
		validateMaintenanceOrder(maintenanceOrder);
		
		log.info("Creating the Maintenance Order for the Equipment - name: {}", maintenanceOrder.getEquipment().getName());
		return maintenanceOrderRepository.save(maintenanceOrder);
		
	}

	@Override
	public MaintenanceOrder update(MaintenanceOrder maintenanceOrder) {
		
		validateId(maintenanceOrder);
		validateMaintenanceOrder(maintenanceOrder);
		get(maintenanceOrder.getId());
		
		log.info("Updating the Maintenance Order - id: {}", maintenanceOrder.getId());
		return maintenanceOrderRepository.save(maintenanceOrder);
		
	}

	@Override
	public void delete(MaintenanceOrder maintenanceOrder) {
		
		validateId(maintenanceOrder);
		get(maintenanceOrder.getId());
		
		log.info("Deleting the Maintenance Order - id: {}", maintenanceOrder.getId());
		maintenanceOrderRepository.delete(maintenanceOrder);
		
	}
	
	@Override
	public void validateId(MaintenanceOrder maintenanceOrder) {
		if(maintenanceOrder.getId() == null) {
			log.info("Maintenance Order id not informed.");
			throw new BusinessRuleException("Enter the Maintenance Order id.");
		}
	}

	@Override
	public void validateMaintenanceOrder(MaintenanceOrder maintenanceOrder) {
		
		if(maintenanceOrder.getEquipment() == null) {
			log.info("Equipment not informed.");
			throw new BusinessRuleException("Enter Equipment for Maintenance Order.");
		}
		
		if(maintenanceOrder.getScheduledDate() == null) {
			log.info("Date schedule not informed.");
			throw new BusinessRuleException("Enter the date schedule.");
		}
		
	}
	
}
