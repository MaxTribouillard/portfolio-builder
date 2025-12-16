package alt.portfolio.builder.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Rubrique {
	
	@Id
	private UUID id;
	
	@Column(length = 100)
	private String name;
	
	@Column(length = 255)
	private String description;
	
	

}
