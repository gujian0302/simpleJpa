package webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.dao.user.UserRepository;

/**
 * Created by gj on 16/4/1.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


}
