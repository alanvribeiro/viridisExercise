package energy.viridis.exercise.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipment", schema = "viridis")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Equipment must have name.")
    @Column
    private String name;
    
    public Equipment(Long id) {
		super();
		this.id = id;
	}

    public Equipment withId(Long id) {
        this.id = id;
        return this;
    }

    public Equipment withName(String name) {
        this.name = name;
        return this;
    }
    
}
