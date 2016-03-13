package webapp.dao.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by gj on 16/3/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-data.xml")
public class UserTest {

    @Autowired
    private UserRepository userRepository;

//    Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Test
    public void listUsers() {
//        logger.debug("start");
//       logger.debug("users:{}",
        userRepository.findAll();
//       );

//        logger.debug("end");
    }


}
