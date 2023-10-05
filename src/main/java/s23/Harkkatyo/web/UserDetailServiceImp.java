package s23.Harkkatyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import s23.Harkkatyo.model.AppUser;
import s23.Harkkatyo.model.AppUserRepository;

@Service
public class UserDetailServiceImp implements UserDetailsService  {
	
	private final AppUserRepository userRepository;

	@Autowired
	public UserDetailServiceImp(AppUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	AppUser curruser = userRepository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }   
} 