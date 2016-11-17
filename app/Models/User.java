package Models;

import com.avaje.ebean.Model;
import org.hibernate.validator.constraints.Length;
import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by lawrence on 16/11/2016.
 */
@Entity
@Table(name="user")
public class User extends Model {
    @Id
    public UUID id;

    @Length(max=40)
    public String name;

    @Constraints.Email
    public String email;


    public String hashedPass;

    public static Finder<Integer, User> find =
            new Finder<>(Integer.class, User.class);

    public User(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
    }

    public static User getUserByUserName(final String email) {
        return User.find
                .where()
                .eq("email", email)
                .findUnique();
    }

}
