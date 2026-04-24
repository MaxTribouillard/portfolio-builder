package alt.portfolio.builder.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import alt.portfolio.builder.entities.Rubrique;

@Entity
@Getter @Setter
public class Categorie {

	@Id
	private UUID id;

	@Column(length = 45)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categorie")
	private List<Rubrique> rubriques;

	
}
