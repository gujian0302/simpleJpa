package webapp.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gj on 16/3/6.
 */
@Transactional(propagation = Propagation.NOT_SUPPORTED )
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



   public List<UserEntity> findUserByName(String name){

        return userRepository.findByName(name);
    }


}
