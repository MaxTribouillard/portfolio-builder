package alt.portfolio.builder.entities;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Profile {

	@Id
	private UUID id = UUID.randomUUID();
	
	@Column(length = 45, nullable = false)
	private String name;
	
	@Column(length = 10400)
	private String description;
	
	@ManyToOne(optional = false, cascade = CascadeType.REMOVE)
	private User owner;
	
}
