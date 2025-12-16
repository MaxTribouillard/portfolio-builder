package alt.portfolio.builder.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alt.portfolio.builder.entities.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

	public Profile findProfileById(UUID id);

	public List<Profile> findProfilesByOwnerIdOrderByName(UUID owner_id);

}
