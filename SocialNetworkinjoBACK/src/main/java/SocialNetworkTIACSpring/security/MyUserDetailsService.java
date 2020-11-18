package SocialNetworkTIACSpring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import SocialNetworkTIACSpring.repository.UserTiacRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserTiacRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new MyUserDetails(userRepo.findByUsername(username));
		
	}
	
}