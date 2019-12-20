package energy.viridis.exercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import energy.viridis.exercise.model.Equipment;
import energy.viridis.exercise.model.MaintenanceOrder;
import energy.viridis.exercise.model.dto.MaintenanceOrderDTO;
import energy.viridis.exercise.service.EquipmentService;
import energy.viridis.exercise.service.MaintenanceOrderService;

@RestController
@RequestMapping("/api/maintenance-order")
public class MaintenanceOrderController {

	@Autowired
	private MaintenanceOrderService maintenanceOrderService;
	
	@Autowired
	private EquipmentService equipmentService;
	
	@GetMapping
	public ResponseEntity<List<MaintenanceOrder>> getAll() {
		return ResponseEntity.ok().body(maintenanceOrderService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<MaintenanceOrder> get(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(maintenanceOrderService.get(id));
	}
	
	@PostMapping
	public ResponseEntity<MaintenanceOrder> create(@RequestBody MaintenanceOrderDTO maintenanceOrderDTO) {
		
		MaintenanceOrder maintenanceOrder = converter(maintenanceOrderDTO);
		return ResponseEntity.ok().body(maintenanceOrderService.create(maintenanceOrder));
		
	}
	
	@PutMapping
	public ResponseEntity<MaintenanceOrder> update(@RequestBody MaintenanceOrderDTO maintenanceOrderDTO) {
		
		MaintenanceOrder maintenanceOrder = converter(maintenanceOrderDTO);
		return ResponseEntity.ok().body(maintenanceOrderService.update(maintenanceOrder));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		
		MaintenanceOrder maintenanceOrder = maintenanceOrderService.get(id);
		maintenanceOrderService.delete(maintenanceOrder);
		return ResponseEntity.ok().body("Maintenance Order.");
		
	}
	
	private MaintenanceOrder converter(MaintenanceOrderDTO maintenanceOrderDTO) {
		
		Equipment equipment = null;
		
		if(maintenanceOrderDTO.getIdEquipment() != null) {
			equipment = equipmentService.get(maintenanceOrderDTO.getIdEquipment());
		}
		
		return MaintenanceOrder.builder()
				.id(maintenanceOrderDTO.getId())
				.equipment(equipment)
				.scheduledDate(maintenanceOrderDTO.getScheduledDate()).build();
	}
	
}
