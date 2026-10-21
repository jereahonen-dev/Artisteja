package artisteja.service;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import artisteja.domain.Application_user;
import artisteja.domain.Application_userRepository;


@Service
public class UserDetailServiceImp implements UserDetailsService {

    private final Application_userRepository repository;

    public UserDetailServiceImp(Application_userRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
    	Application_user curruser = repository.findByUsername(username);
        System.out.println("Username: " + username);
        System.out.println("Password hash: [" + curruser.getPasswordHash() + "]");
        System.out.println("Length: " + curruser.getPasswordHash().length());
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }
}
