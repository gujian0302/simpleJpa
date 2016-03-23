package zookeeper;

import sun.awt.AWTAccessor;

import java.util.Comparator;
import java.util.concurrent.locks.Lock;

/**
 * Created by gj on 16/3/19.
 */
public enum LockMode implements Comparator<LockMode> {
    READ,
    WRITE;


    public int compare(LockMode o1, LockMode o2) {
        if (o1.equals(o2)) {
            return 0;
        } else if (o1 == READ) {
            return -1;
        } else {
            return 1;
        }
    }
}
