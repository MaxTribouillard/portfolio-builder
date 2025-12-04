package alt.portfolio.builder.dtos;
import alt.portfolio.builder.entities.*;
import java.util.UUID;

import lombok.Data;

@Data
public class UserRequestDto {

	private String firstname;
	private String lastname;
	private String username;
	private String email;
	
	public User toUser(User user) {

		user.setFirstname(firstname);
		user.setEmail(email);
		user.setUsername(username);
		user.setLastname(lastname);
		return user;
	}

}
