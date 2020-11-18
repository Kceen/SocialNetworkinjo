package SocialNetworkTIACSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import SocialNetworkTIACSpring.model.PostGradeTiac;
import SocialNetworkTIACSpring.model.PostTiac;

public interface PostGradeTiacRepository extends JpaRepository<PostGradeTiac, Integer> {
	void deleteByPostTiac(PostTiac post);
}
