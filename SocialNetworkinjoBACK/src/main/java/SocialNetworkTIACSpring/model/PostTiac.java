package SocialNetworkTIACSpring.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the post_tiac database table.
 * 
 */
@Entity
@Table(name="post_tiac")
@NamedQuery(name="PostTiac.findAll", query="SELECT p FROM PostTiac p")
public class PostTiac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idpost_tiac")
	private int idpostTiac;

	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	//bi-directional many-to-one association to PostCommentTiac
	@OneToMany(mappedBy="postTiac")
	private List<PostCommentTiac> postCommentTiacs;

	//bi-directional many-to-one association to PostGradeTiac
	@OneToMany(mappedBy="postTiac")
	private List<PostGradeTiac> postGradeTiacs;

	//bi-directional many-to-one association to UserTiac
	@ManyToOne
	@JoinColumn(name="user_tiac_iduser_tiac")
	private UserTiac userTiac;

	public PostTiac() {
	}

	public int getIdpostTiac() {
		return this.idpostTiac;
	}

	public void setIdpostTiac(int idpostTiac) {
		this.idpostTiac = idpostTiac;
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

	public List<PostCommentTiac> getPostCommentTiacs() {
		return this.postCommentTiacs;
	}

	public void setPostCommentTiacs(List<PostCommentTiac> postCommentTiacs) {
		this.postCommentTiacs = postCommentTiacs;
	}

	public PostCommentTiac addPostCommentTiac(PostCommentTiac postCommentTiac) {
		getPostCommentTiacs().add(postCommentTiac);
		postCommentTiac.setPostTiac(this);

		return postCommentTiac;
	}

	public PostCommentTiac removePostCommentTiac(PostCommentTiac postCommentTiac) {
		getPostCommentTiacs().remove(postCommentTiac);
		postCommentTiac.setPostTiac(null);

		return postCommentTiac;
	}

	public List<PostGradeTiac> getPostGradeTiacs() {
		return this.postGradeTiacs;
	}

	public void setPostGradeTiacs(List<PostGradeTiac> postGradeTiacs) {
		this.postGradeTiacs = postGradeTiacs;
	}

	public PostGradeTiac addPostGradeTiac(PostGradeTiac postGradeTiac) {
		getPostGradeTiacs().add(postGradeTiac);
		postGradeTiac.setPostTiac(this);

		return postGradeTiac;
	}

	public PostGradeTiac removePostGradeTiac(PostGradeTiac postGradeTiac) {
		getPostGradeTiacs().remove(postGradeTiac);
		postGradeTiac.setPostTiac(null);

		return postGradeTiac;
	}

	public UserTiac getUserTiac() {
		return this.userTiac;
	}

	public void setUserTiac(UserTiac userTiac) {
		this.userTiac = userTiac;
	}

}