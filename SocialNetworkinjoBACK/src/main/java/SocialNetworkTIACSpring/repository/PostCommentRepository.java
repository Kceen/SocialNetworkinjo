package SocialNetworkTIACSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import SocialNetworkTIACSpring.model.PostCommentTiac;
import SocialNetworkTIACSpring.model.PostTiac;

public interface PostCommentRepository extends JpaRepository<PostCommentTiac, Integer> {
	void deleteByPostTiac(PostTiac post);
}
