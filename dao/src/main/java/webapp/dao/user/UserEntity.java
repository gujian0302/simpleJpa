package webapp.dao.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

/**
 * Created by gj on 16/3/6.
 */
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "id" ,length = 64)
    private String id= UUID.randomUUID().toString();

    @Column(name = "name")
    private String name;


    @Column(name = "phone_number")
    private String phoneNumber;


    @Column(name="birth_day")
    private Date birthDay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
