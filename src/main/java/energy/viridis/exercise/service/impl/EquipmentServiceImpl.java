package energy.viridis.exercise.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import energy.viridis.exercise.error.BusinessRuleException;
import energy.viridis.exercise.model.Equipment;
import energy.viridis.exercise.repository.EquipmentRepository;
import energy.viridis.exercise.service.EquipmentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentRepository equipmentRepository;

	@Override
	public Equipment get(Long id) {

		log.info("Retrieving Equipment - id: {}", id);
		return equipmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Equipment not found."));

	}

	@Override
	public List<Equipment> getAll() {

		log.info("Listing all Equipment");
		return equipmentRepository.findAll();

	}
	
	@Override
	@Transactional
	public Equipment create(Equipment equipment) {
		
		validateEquipment(equipment);
		
		log.info("Creating the Equipment - name: {}", equipment.getName());
		return equipmentRepository.save(equipment);
		
	}
	
	@Override
	@Transactional
	public Equipment update(Equipment equipment) {
		
		validateId(equipment);
		validateEquipment(equipment);
		get(equipment.getId());
		
		log.info("Updating the Equipment - id: {}", equipment.getId());
		return equipmentRepository.save(equipment);
		
	}
		
	@Override
	@Transactional
	public void delete(Equipment equipment) {
		
		validateId(equipment);
		get(equipment.getId());
		
		log.info("Deleting the Equipment - id: {}", equipment.getId());
		equipmentRepository.delete(equipment);
		
	}
	
	@Override
	public void validateId(Equipment equipment) {
		if(equipment.getId() == null) {
			log.info("Equipment id not informed.");
			throw new BusinessRuleException("Enter the Equipment id.");
		}
	}

	@Override
	public void validateEquipment(Equipment equipment) {
		if(equipment.getName() == null || equipment.getName().equals("")) {
			log.info("Equipment name not informed.");
			throw new BusinessRuleException("Enter the name of the Equipment.");
		}
	}

}
