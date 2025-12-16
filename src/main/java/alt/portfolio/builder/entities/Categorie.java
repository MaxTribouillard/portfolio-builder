package alt.portfolio.builder.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Categorie {

	@Id
	private UUID id;
	
	
}
