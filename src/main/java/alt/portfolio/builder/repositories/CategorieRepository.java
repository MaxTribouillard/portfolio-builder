package alt.portfolio.builder.repositories;

import alt.portfolio.builder.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategorieRepository extends JpaRepository<Categorie, UUID> {
}
