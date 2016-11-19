package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Programmer on 2016/11/17.
 */
@Entity(name = "cf_book")
@Table(name = "cf_books")
public class ebeanTest extends Model {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	
	@Column(name = "book_name")
	public String book_name;
	
	@Column(name = "book_url")
	public String book_url;
	
	@Column(name = "book_type")
	public String book_type;
	
	@Column(name = "book_price")
	public String book_price;
	
	public String getBook_name() {
		return book_name;
	}
	
	public String getBook_url() {
		return book_url;
	}
	
	public String getBook_type() {
		return book_type;
	}
	
	public String getBook_price() { return book_price; }
	
	public int getBook_id() { return id; }
	
	public static List<ebeanTest> get(){
		List<ebeanTest> tests = Ebean.find(ebeanTest.class).findList();
		return tests;
	}
	
	
}
