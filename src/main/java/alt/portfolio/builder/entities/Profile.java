package alt.portfolio.builder.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

	@Column(length = 10400)
	private String description;

	@ManyToOne(optional = false)
	private User owner;

}
