package alt.portfolio.builder.application;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import alt.portfolio.builder.entities.User;
import alt.portfolio.builder.exceptions.EntityNotFoundExceptions;

@ControllerAdvice
public class MainAdvice {

	@ModelAttribute("activeUser")
	public User getActiveUser(Authentication auth) {
		if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
			return null;
		}
		return (User) auth.getPrincipal();
	}

	@ExceptionHandler(exception = EntityNotFoundExceptions.class)
	public ModelAndView entityNotFound() {
		return new ModelAndView("/errors/404", HttpStatus.NOT_FOUND);
	}

}
