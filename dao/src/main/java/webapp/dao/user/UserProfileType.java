package webapp.dao.user;

/**
 * Created by gj on 16/4/12.
 */
public enum UserProfileType {

    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    UserProfileType(String userProfileType) {
        this.userProfileType = userProfileType;
    }

    String userProfileType;

    public String getUserProfileType() {
        return userProfileType;
    }

    public void setUserProfileType(String userProfileType) {
        this.userProfileType = userProfileType;
    }
}
