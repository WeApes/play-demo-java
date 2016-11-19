package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Programmer on 2016/11/18.
 */
@Entity(name = "book_message")
@Table(name = "book_messages")
public class bookDetail  extends Model{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	
	@Column(name = "book_name")
	public String book_name;
	
	@Column(name = "book_url")
	public String book_url;
	
	@Column(name = "author")
	public String author;
	
	@Column(name = "book_price")
	public String book_price;
	
	
}
