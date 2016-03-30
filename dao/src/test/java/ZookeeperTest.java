import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by gj on 16/3/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-zookeeper.xml")
public class ZookeeperTest {

    @Autowired
    private CuratorFramework c;

    private static Logger lg = LoggerFactory.getLogger(ZookeeperTest.class);

    @Test
    public void lock()throws Exception {
        String lockName = "/lock1";

        InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(c,lockName);


        InterProcessLock writeLock = readWriteLock.writeLock();
        InterProcessLock readLock  = readWriteLock.readLock();
        System.out.print(writeLock.acquire(1, TimeUnit.SECONDS));
        System.out.println(readLock.acquire(1,TimeUnit.SECONDS));




        writeLock.release();

        readLock.release();

    }
}
