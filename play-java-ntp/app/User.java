package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by lawrence on 16/11/2016.
 */
@Entity
public class User extends Model {
    @Id
    public Long id;

    @Size(max = 40)
    @NotNull
    public String name;

    @Constraints.Email
    @NotNull
    @Column(unique = true)
    public String email;

    @NotNull
    public String hashedPass;

    public String addr;

    @Lob
    public byte[] profile;


    public static Finder<String, User> find =
            new Finder<>(String.class, User.class);


    public static User getUserByEmail(final String email) {
        return User.find
                .where()
                .eq("email", email)
                .findUnique();
    }
}
