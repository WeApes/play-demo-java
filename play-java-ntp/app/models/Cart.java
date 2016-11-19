package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by lawrence on 19/11/2016.
 */
@Entity
public class Cart extends Model {
    @Id
    public String id;

    public List<CartDescription> cartDescriptions;
}
