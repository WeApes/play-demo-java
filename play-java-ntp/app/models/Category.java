package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by lawrence on 19/11/2016.
 */
@Entity
public class Category extends Model {
    @Id
    public String name;

    public List<Book> books;
}
