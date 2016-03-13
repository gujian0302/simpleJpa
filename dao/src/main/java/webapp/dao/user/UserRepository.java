package webapp.dao.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gj on 16/3/6.
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity,String>{

    List<UserEntity> findByName(String name);


}
