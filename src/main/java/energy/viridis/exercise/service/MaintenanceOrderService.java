package energy.viridis.exercise.service;

import java.util.List;

import energy.viridis.exercise.model.MaintenanceOrder;

public interface MaintenanceOrderService {

	MaintenanceOrder get(Long id);

	List<MaintenanceOrder> getAll();
	
	MaintenanceOrder create(MaintenanceOrder maintenanceOrder);
	
	MaintenanceOrder update(MaintenanceOrder maintenanceOrder);
	
	void delete(MaintenanceOrder maintenanceOrder);
	
	void validateId(MaintenanceOrder maintenanceOrder);

	void validateMaintenanceOrder(MaintenanceOrder maintenanceOrder);

}
