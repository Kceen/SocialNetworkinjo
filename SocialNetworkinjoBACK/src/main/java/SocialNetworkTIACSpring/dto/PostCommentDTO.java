package SocialNetworkTIACSpring.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PostCommentDTO {
	private int idPostComment;
	private String content;
	private Date createdOn;
	private String creatorUsername;
}