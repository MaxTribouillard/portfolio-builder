package alt.portfolio.builder.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alt.portfolio.builder.entities.Profile;
import alt.portfolio.builder.entities.User;
import alt.portfolio.builder.repositories.ProfileRepository;
import alt.portfolio.builder.repositories.UserRepository;

@Service
public class ProfileService {

	@Autowired
	ProfileRepository profilRepository;

	@Autowired
	UserRepository userRepository;

	public Profile createProfile(Profile profile, UUID userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		profile.setOwner(user);
		return profilRepository.save(profile);
	}

	public List<Profile> showUserProfiles(UUID owner_id) {
		List<Profile> profiles = profilRepository.findProfilesByOwnerIdOrderByName(owner_id);
		return profiles;
	}

	public void deleteProfile(UUID profileId) {
		profilRepository.deleteById(profileId);
	}

	public Profile editProfile(UUID profileId, Profile profile) {
		Profile profileToEdit = profilRepository.findById(profileId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		profileToEdit.setDescription(profile.getDescription());
		profileToEdit.setName(profile.getName());
		return profilRepository.save(profileToEdit);
	}

	public Profile getProfileById(UUID profileId) {
		Profile profil = profilRepository.findProfileById(profileId);
		return profil;
	}

//	public List<Profile> showProfiles(UUID id) {
//		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
//		user.getProfiles();
//		return user.getProfiles();
//	}

}
