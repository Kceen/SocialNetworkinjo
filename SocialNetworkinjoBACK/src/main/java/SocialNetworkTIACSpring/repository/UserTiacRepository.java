package SocialNetworkTIACSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import SocialNetworkTIACSpring.model.UserTiac;

public interface UserTiacRepository extends JpaRepository<UserTiac, Integer> {
	public UserTiac findByUsername(String username);
}
