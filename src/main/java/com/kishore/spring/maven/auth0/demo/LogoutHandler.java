package com.kishore.spring.maven.auth0.demo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class LogoutHandler extends SecurityContextLogoutHandler {

	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		super.logout(request, response, authentication);

		// Build the URL to log the user out of Auth0 and redirect them to the home
		// page.
		// URL will look like
		// https://YOUR-DOMAIN/v2/logout?clientId=YOUR-CLIENT-ID&returnTo=http://localhost:3000
		String issuer = (String) getClientRegistration().getProviderDetails().getConfigurationMetadata().get("issuer");
		String clientId = getClientRegistration().getClientId();
		String returnTo = ServletUriComponentsBuilder.fromCurrentContextPath().build().toString();

		String logoutUrl = UriComponentsBuilder
				.fromHttpUrl(issuer + "v2/logout?client_id={clientId}&returnTo={returnTo}").encode()
				.buildAndExpand(clientId, returnTo).toUriString();

		try {
			response.sendRedirect(logoutUrl);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Gets the Spring ClientRegistration, which we use to get the registered client
	 * ID and issuer for building the {@code returnTo} query parameter when calling
	 * the Auth0 logout API.
	 *
	 * @return the {@code ClientRegistration} for this application.
	 */
	private ClientRegistration getClientRegistration() {
		return this.clientRegistrationRepository.findByRegistrationId("auth0");
	}
}
