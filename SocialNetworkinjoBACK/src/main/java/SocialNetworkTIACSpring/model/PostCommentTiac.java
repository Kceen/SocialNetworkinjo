package SocialNetworkTIACSpring.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the post_comment_tiac database table.
 * 
 */
@Entity
@Table(name="post_comment_tiac")
@NamedQuery(name="PostCommentTiac.findAll", query="SELECT p FROM PostCommentTiac p")
public class PostCommentTiac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idpost_comment_tiac")
	private int idpostCommentTiac;

	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	//bi-directional many-to-one association to PostTiac
	@ManyToOne
	@JoinColumn(name="post_tiac_idpost_tiac")
	private PostTiac postTiac;

	//bi-directional many-to-one association to UserTiac
	@ManyToOne
	@JoinColumn(name="user_tiac_iduser_tiac")
	private UserTiac userTiac;

	public PostCommentTiac() {
	}

	public int getIdpostCommentTiac() {
		return this.idpostCommentTiac;
	}

	public void setIdpostCommentTiac(int idpostCommentTiac) {
		this.idpostCommentTiac = idpostCommentTiac;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public PostTiac getPostTiac() {
		return this.postTiac;
	}

	public void setPostTiac(PostTiac postTiac) {
		this.postTiac = postTiac;
	}

	public UserTiac getUserTiac() {
		return this.userTiac;
	}

	public void setUserTiac(UserTiac userTiac) {
		this.userTiac = userTiac;
	}

}