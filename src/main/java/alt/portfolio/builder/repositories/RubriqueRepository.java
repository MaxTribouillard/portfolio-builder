package alt.portfolio.builder.repositories;

import alt.portfolio.builder.entities.Profile;
import alt.portfolio.builder.entities.Rubrique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RubriqueRepository extends JpaRepository<Rubrique, UUID> {

    public Rubrique findRubriqueByProfileId(UUID id);

}
