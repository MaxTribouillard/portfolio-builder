package alt.portfolio.builder.entities;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rubrique {

	@Id
	@GeneratedValue
	private UUID id;

	@Column(length = 100)
	private String name;

	@Column(length = 255)
	private String description;

	@ManyToOne()
	private Profile profile;

	@ManyToOne()
	private Categorie categorie;

}
