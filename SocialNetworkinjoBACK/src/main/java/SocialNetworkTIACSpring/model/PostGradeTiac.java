package SocialNetworkTIACSpring.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the post_grade_tiac database table.
 * 
 */
@Entity
@Table(name="post_grade_tiac")
@NamedQuery(name="PostGradeTiac.findAll", query="SELECT p FROM PostGradeTiac p")
public class PostGradeTiac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idpost_grade_tiac")
	private int idpostGradeTiac;

	private int grade;

	//bi-directional many-to-one association to PostTiac
	@ManyToOne
	@JoinColumn(name="post_tiac_idpost_tiac")
	private PostTiac postTiac;

	//bi-directional many-to-one association to UserTiac
	@ManyToOne
	@JoinColumn(name="user_tiac_iduser_tiac")
	private UserTiac userTiac;

	public PostGradeTiac() {
	}

	public int getIdpostGradeTiac() {
		return this.idpostGradeTiac;
	}

	public void setIdpostGradeTiac(int idpostGradeTiac) {
		this.idpostGradeTiac = idpostGradeTiac;
	}

	public int getGrade() {
		return this.grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
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