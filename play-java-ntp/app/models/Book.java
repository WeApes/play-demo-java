package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Created by lawrence on 19/11/2016.
 */
@Entity
public class Book extends Model {
    @Id
    public String id;

    @NotNull
    public String name;

    @Max(value = 1000)
    public int price;

    public String description;

    public String author;

    @Lob
    public byte[] image;

    @Constraints.Min(0)
    @NotNull
    public int stock;

    @NotNull
    @ManyToOne
    public Category category;
    
}
