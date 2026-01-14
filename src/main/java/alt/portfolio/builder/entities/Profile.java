package alt.portfolio.builder.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Profile {

	@Id
	@GeneratedValue
	private UUID id;

	@Column(length = 45, nullable = false)
	private String name;

	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(length = 10400)
	private String description;

	@ManyToOne(optional = false)
	private User owner;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profile")
	private List<Rubrique> rubriques;

}
