package models;

import com.avaje.ebean.Model;
import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * Created by lawrence on 16/11/2016.
 */
@Entity
public class User extends Model {
    @Id
    public String id;

    @Size(max = 40)
    public String name;

    @Constraints.Email
    public String email;

    public String hashedPass;
    
    public static Finder<String, User> find =
            new Finder<>(String.class, User.class);

    public User(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
    }

    public static User getUserByEmail(final String email) {
        return User.find
                .where()
                .eq("email", email)
                .findUnique();
    }
}
