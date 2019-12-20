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
import energy.viridis.exercise.model.dto.EquipmentDTO;
import energy.viridis.exercise.service.EquipmentService;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService;

	@GetMapping
	public ResponseEntity<List<Equipment>> getAll() {
		return ResponseEntity.ok().body(equipmentService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Equipment> get(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(equipmentService.get(id));
	}
	
	@PostMapping
	public ResponseEntity<Equipment> create(@RequestBody EquipmentDTO equipmentDTO) {
		
		Equipment equipment = converter(equipmentDTO);
		return ResponseEntity.ok().body(equipmentService.create(equipment));
		
	}
	
	@PutMapping
	public ResponseEntity<Equipment> update(@RequestBody EquipmentDTO equipmentDTO) {
		
		Equipment equipment = converter(equipmentDTO);
		return ResponseEntity.ok().body(equipmentService.update(equipment));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		
		Equipment equipment = equipmentService.get(id);
		equipmentService.delete(equipment);
		return ResponseEntity.ok().body("Equipment deleted.");
		
	}
	
	private Equipment converter(EquipmentDTO equipmentDTO) {
		return Equipment.builder()
				.id(equipmentDTO.getId())
				.name(equipmentDTO.getName()).build();
	}
	
}
