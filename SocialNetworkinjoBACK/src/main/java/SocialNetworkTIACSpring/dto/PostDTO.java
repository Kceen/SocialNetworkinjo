package SocialNetworkTIACSpring.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PostDTO {
	private int idPost;
	private String content;
	private Date createdOn;
	private int numberOfComments;
	private double grade;
	private int numberOfGrades;
	private int gradesSum;
	private String creatorUsername;
	private List<PostCommentDTO> comments;
}