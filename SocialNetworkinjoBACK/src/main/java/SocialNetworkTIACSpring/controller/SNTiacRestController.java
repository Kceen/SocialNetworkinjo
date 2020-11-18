package SocialNetworkTIACSpring.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import SocialNetworkTIACSpring.dto.LoginUserRequestDTO;
import SocialNetworkTIACSpring.dto.LoginUserResponseDTO;
import SocialNetworkTIACSpring.dto.PostCommentDTO;
import SocialNetworkTIACSpring.dto.PostDTO;
import SocialNetworkTIACSpring.dto.UserDTO;
import SocialNetworkTIACSpring.model.PostCommentTiac;
import SocialNetworkTIACSpring.model.PostGradeTiac;
import SocialNetworkTIACSpring.model.PostTiac;
import SocialNetworkTIACSpring.model.UserTiac;
import SocialNetworkTIACSpring.repository.PostCommentRepository;
import SocialNetworkTIACSpring.repository.PostGradeTiacRepository;
import SocialNetworkTIACSpring.repository.PostTiacRepository;
import SocialNetworkTIACSpring.repository.UserTiacRepository;
import SocialNetworkTIACSpring.security.JwtUtil;
import SocialNetworkTIACSpring.security.MyUserDetailsService;


@RestController
@RequestMapping(value = "api")
public class SNTiacRestController {
	
	@Autowired
	PostTiacRepository postTiacRepo;
	
	@Autowired
	UserTiacRepository userTiacRepo;
	
	@Autowired
	PostCommentRepository postCommentTiacRepo;
	
	@Autowired
	PostGradeTiacRepository postGradeTiacRepo;
	
	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@GetMapping(value = "getPosts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PostDTO>> getPosts(HttpServletRequest request) {
		System.out.println(request.getHeader("Authorization"));
		
		List<PostTiac> posts = postTiacRepo.findAll();
		List<PostDTO> postsDTO = new ArrayList<PostDTO>();
		
		int gradesSum = 0;
		for (PostTiac post : posts) {
			PostDTO postDTO = new PostDTO();
			
			postDTO.setIdPost(post.getIdpostTiac());
			postDTO.setContent(post.getContent());
			postDTO.setCreatedOn(post.getCreatedOn());
			postDTO.setNumberOfComments(post.getPostCommentTiacs().size());
			
			gradesSum = 0;
			for (PostGradeTiac grade : post.getPostGradeTiacs()) {
				gradesSum += grade.getGrade();
			}
			
			List<PostCommentDTO> commentsDTO = new ArrayList<PostCommentDTO>();
			for (PostCommentTiac comment: post.getPostCommentTiacs()) {
				PostCommentDTO commentDTO = new PostCommentDTO();
				commentDTO.setContent(comment.getContent());
				commentDTO.setCreatedOn(comment.getCreatedOn());
				commentDTO.setCreatorUsername(comment.getUserTiac().getUsername());
				commentDTO.setIdPostComment(comment.getIdpostCommentTiac());
				
				commentsDTO.add(commentDTO);
			}
			if(gradesSum > 0) {
				postDTO.setGrade((double) gradesSum / post.getPostGradeTiacs().size());
			}
			else {
				postDTO.setGrade(0);
			}
			postDTO.setNumberOfGrades(post.getPostGradeTiacs().size());
			postDTO.setGradesSum(gradesSum);
			postDTO.setCreatorUsername(post.getUserTiac().getUsername());
			postDTO.setComments(commentsDTO);
			
			postsDTO.add(postDTO);
		}
		
		Collections.reverse(postsDTO);
		
		return ResponseEntity.ok(postsDTO);
	}
	
	@RequestMapping(value = "addComment/{idPost}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PostCommentDTO addComment(@PathVariable("idPost") int idPost, @RequestBody PostCommentDTO comment, @RequestHeader(value = "Authorization") String jwtHeader){
		PostTiac post = postTiacRepo.findById(idPost).get();
		PostCommentTiac commentToAdd = new PostCommentTiac();
		commentToAdd.setContent(comment.getContent());
		commentToAdd.setCreatedOn(comment.getCreatedOn());
		commentToAdd.setPostTiac(post);
		
		String username = jwtUtil.extractUsername(jwtHeader.substring(7));
		UserTiac user = userTiacRepo.findByUsername(username);
		commentToAdd.setUserTiac(user);
		
		comment.setIdPostComment(postCommentTiacRepo.save(commentToAdd).getIdpostCommentTiac());
		return comment;
	}
	
	@RequestMapping(value = "changePost", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void changePost(@RequestBody PostDTO post, @RequestHeader(value = "Authorization") String jwtHeader){
		PostTiac postToChange = postTiacRepo.findById(post.getIdPost()).get();
		postToChange.setContent(post.getContent());
		postTiacRepo.save(postToChange);
	}
	
	@RequestMapping(value = "changeComment", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void changeComment(@RequestBody PostCommentDTO comment, @RequestHeader(value = "Authorization") String jwtHeader){
		PostCommentTiac commentToChange = postCommentTiacRepo.findById(comment.getIdPostComment()).get();
		commentToChange.setContent(comment.getContent());
		postCommentTiacRepo.save(commentToChange);
	}
	
	@Transactional
	@RequestMapping(value = "deletePost/{idPost}", method = RequestMethod.DELETE)
	public void deletePost(@PathVariable("idPost") int idPost, @RequestHeader(value = "Authorization") String jwtHeader){
		PostTiac postToDelete = postTiacRepo.findById(idPost).get();
		postCommentTiacRepo.deleteByPostTiac(postToDelete);
		postGradeTiacRepo.deleteByPostTiac(postToDelete);
		postTiacRepo.delete(postToDelete);
	}
	
	@RequestMapping(value = "deleteComment/{idComment}", method = RequestMethod.DELETE)
	public void deleteComment(@PathVariable("idComment") int idComment, @RequestHeader(value = "Authorization") String jwtHeader){
		PostCommentTiac commentToDelete = postCommentTiacRepo.findById(idComment).get();
		postCommentTiacRepo.delete(commentToDelete);
	}
	
	@RequestMapping(value = "addPost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PostDTO addPost(@RequestBody PostDTO post, @RequestHeader(value = "Authorization") String jwtHeader){
		PostTiac postToAdd = new PostTiac();
		postToAdd.setContent(post.getContent());
		postToAdd.setCreatedOn(post.getCreatedOn());
		postToAdd.setPostCommentTiacs(new ArrayList<PostCommentTiac>());
		postToAdd.setPostGradeTiacs(new ArrayList<PostGradeTiac>());
		
		String username = jwtUtil.extractUsername(jwtHeader.substring(7));
		UserTiac user = userTiacRepo.findByUsername(username);
		postToAdd.setUserTiac(user);
		
		post.setIdPost(postTiacRepo.save(postToAdd).getIdpostTiac());
		return post;
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> register(@RequestBody LoginUserRequestDTO registerRequest) {
		String username = registerRequest.getUsername();
		String password = registerRequest.getPassword();
		
		if(userTiacRepo.findByUsername(username) == null) {
			UserTiac newUser = new UserTiac();
			newUser.setUsername(username);
			newUser.setPassword(password);
			
			userTiacRepo.save(newUser);
			
			return ResponseEntity.ok(true);
		}
		else {
			return ResponseEntity.ok(false);
		}
	}
	
	@RequestMapping(value = "authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginUserResponseDTO> createAuthenticationToken(@RequestBody LoginUserRequestDTO authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--------------------------------------------");
			System.out.println("USERNAME = " + authenticationRequest.getUsername());
			System.out.println("PASSWORD = " + authenticationRequest.getPassword());
			System.out.println("HTTP STATUS 401 - UNAUTHORIZED");
			System.out.println("--------------------------------------------");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);

		System.out.println("--------------------------------------------");
		System.out.println("USERNAME = " + authenticationRequest.getUsername());
		System.out.println("PASSWORD = " + authenticationRequest.getPassword());
		System.out.println("HTTP STATUS 200 - OK");
		System.out.println("--------------------------------------------");
		return ResponseEntity.ok(new LoginUserResponseDTO(jwt));
	}
	
	@GetMapping(value = "getUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getUser(@RequestHeader(value = "Authorization") String jwtHeader) {
		String username = jwtUtil.extractUsername(jwtHeader.substring(7));
		
		UserTiac user = userTiacRepo.findByUsername(username);
		UserDTO userDTO = new UserDTO();
		userDTO.setIdUser(user.getIduserTiac());
		userDTO.setUsername(user.getUsername());
		
		return ResponseEntity.ok(userDTO);
	}
	
	@GetMapping(value = "getNumberOfPosts")
	public ResponseEntity<Long> getNumberOfPosts() {
		return ResponseEntity.ok(postTiacRepo.count());
	}
}
