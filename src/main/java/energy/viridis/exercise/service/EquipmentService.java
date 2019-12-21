package energy.viridis.exercise.service;

import java.util.List;

import energy.viridis.exercise.model.Equipment;

public interface EquipmentService {

	Equipment get(Long id);

	List<Equipment> getAll();
	
	Equipment create(Equipment equipment);
	
	Equipment update(Equipment equipment);
	
	void delete(Equipment equipment);
	
	void validateId(Equipment equipment);
	
	void validateEquipment(Equipment equipment);
	
}
