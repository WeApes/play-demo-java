package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by lawrence on 19/11/2016.
 */
@Entity
public class BookOrder extends Model {
    @Id
    public String id;

    @ManyToOne(cascade = CascadeType.ALL)
    public User user;

    public List<OrderDescription> orderItems;

    public int priceSum;

    public Date date;
}
