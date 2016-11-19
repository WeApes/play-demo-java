package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by lawrence on 19/11/2016.
 */
@Entity
public class OrderDescription extends Model {
    @Id
    public String id;

    public int num;

    @OneToOne
    public Book book;
}
