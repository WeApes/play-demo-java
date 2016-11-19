package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by lawrence on 19/11/2016.
 */
@Entity
public class CartDescription extends Model {
    @Id
    public String id;

    @Constraints.Min(0)
    public int num;

    @OneToOne
    public Book book;

    @ManyToOne
    public Cart cart;

}
