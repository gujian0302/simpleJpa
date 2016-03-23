package zookeeper;

/**
 * Created by gj on 16/3/19.
 */
public class DefaultZookeeperLockPathWrapper implements ZookeeperLockPathWrapper {
    public String wrap(String path, String locknode, LockMode mode) {
        if(!path.startsWith("/")){
            path += "/" + path;
        }
        StringBuilder sb = new StringBuilder(path);
        sb.append("/").append("_").append(locknode).append("_").append("/").append(mode.toString().toLowerCase()).append("-");

        return sb.toString();
    }

    public String unwrap(String path) {
        //TODO
        //unwrap string path
        return null;
    }

    public String wrap(String path, String locknode) {
        path = uniform(path);
        StringBuilder sb = new StringBuilder(path);

        sb.append("/").append("_").append(locknode).append("_").append("/");


        return sb.toString();
    }

    private String uniform(String path){
        if(!path.startsWith("/")){
            path = "/"+ path;
        }

        return path;
    }
}
