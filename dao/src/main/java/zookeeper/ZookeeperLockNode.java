package zookeeper;

/**
 * Created by gj on 16/3/20.
 */
public class ZookeeperLockNode {


    private String node;

    private LockMode mode;

    private String serialNumber;

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public LockMode getMode() {
        return mode;
    }

    public void setMode(LockMode mode) {
        this.mode = mode;
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public ZookeeperLockNode(String node, LockMode mode, String serialNumber) {
        this.node = node;
        this.mode = mode;
        this.serialNumber = serialNumber;
    }


    public ZookeeperLockNode(String lockdir) {
        String[] modeAndDir = lockdir.split("-");

        if (modeAndDir.length != 2) {
            throw new ZookeeperErrorLockPathException();
        }
        this.mode = LockMode.valueOf(modeAndDir[0]);
        this.serialNumber = modeAndDir[1];

    }


    public ZookeeperLockNode() {
    }
}
