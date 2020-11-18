package SocialNetworkTIACSpring.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_tiac database table.
 * 
 */
@Entity
@Table(name="user_tiac")
@NamedQuery(name="UserTiac.findAll", query="SELECT u FROM UserTiac u")
public class UserTiac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="iduser_tiac")
	private int iduserTiac;

	private String password;

	private String username;

	//bi-directional many-to-one association to PostCommentTiac
	@OneToMany(mappedBy="userTiac")
	private List<PostCommentTiac> postCommentTiacs;

	//bi-directional many-to-one association to PostGradeTiac
	@OneToMany(mappedBy="userTiac")
	private List<PostGradeTiac> postGradeTiacs;

	//bi-directional many-to-one association to PostTiac
	@OneToMany(mappedBy="userTiac")
	private List<PostTiac> postTiacs;

	public UserTiac() {
	}

	public int getIduserTiac() {
		return this.iduserTiac;
	}

	public void setIduserTiac(int iduserTiac) {
		this.iduserTiac = iduserTiac;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<PostCommentTiac> getPostCommentTiacs() {
		return this.postCommentTiacs;
	}

	public void setPostCommentTiacs(List<PostCommentTiac> postCommentTiacs) {
		this.postCommentTiacs = postCommentTiacs;
	}

	public PostCommentTiac addPostCommentTiac(PostCommentTiac postCommentTiac) {
		getPostCommentTiacs().add(postCommentTiac);
		postCommentTiac.setUserTiac(this);

		return postCommentTiac;
	}

	public PostCommentTiac removePostCommentTiac(PostCommentTiac postCommentTiac) {
		getPostCommentTiacs().remove(postCommentTiac);
		postCommentTiac.setUserTiac(null);

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
		postGradeTiac.setUserTiac(this);

		return postGradeTiac;
	}

	public PostGradeTiac removePostGradeTiac(PostGradeTiac postGradeTiac) {
		getPostGradeTiacs().remove(postGradeTiac);
		postGradeTiac.setUserTiac(null);

		return postGradeTiac;
	}

	public List<PostTiac> getPostTiacs() {
		return this.postTiacs;
	}

	public void setPostTiacs(List<PostTiac> postTiacs) {
		this.postTiacs = postTiacs;
	}

	public PostTiac addPostTiac(PostTiac postTiac) {
		getPostTiacs().add(postTiac);
		postTiac.setUserTiac(this);

		return postTiac;
	}

	public PostTiac removePostTiac(PostTiac postTiac) {
		getPostTiacs().remove(postTiac);
		postTiac.setUserTiac(null);

		return postTiac;
	}

}