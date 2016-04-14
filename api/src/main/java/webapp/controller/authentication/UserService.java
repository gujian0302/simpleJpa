package webapp.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import webapp.dao.user.User;
import webapp.dao.user.UserProfile;
import webapp.dao.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gj on 16/4/12.
 */
@Service("userDetailService")
public class UserService  implements  UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findFirstByPhoneNumber(phoneNumber);

        if (user == null) {
            throw new UsernameNotFoundException(phoneNumber);
        }

        return new org.springframework.security.core.userdetails.User(phoneNumber,user.getPassword(),getGrantedAuthorities(user));

    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(UserProfile item : user.getUserProfiles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+item.getType()));
        }


        return authorities;
    }
}
