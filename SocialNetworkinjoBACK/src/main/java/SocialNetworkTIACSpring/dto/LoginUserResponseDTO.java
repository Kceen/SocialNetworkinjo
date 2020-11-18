package SocialNetworkTIACSpring.dto;

public class LoginUserResponseDTO {
	private final String jwt;

	public LoginUserResponseDTO(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
}
