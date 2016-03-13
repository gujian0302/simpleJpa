package webapp.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gj on 16/3/6.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {

    List<UserEntity> findByName(String name);


}
