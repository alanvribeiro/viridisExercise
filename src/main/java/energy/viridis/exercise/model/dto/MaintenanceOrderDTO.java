package energy.viridis.exercise.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceOrderDTO {
	
	private Long id;
	private Long idEquipment;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
	private Date scheduledDate;
	
}
