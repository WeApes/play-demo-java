package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.ebeanTest;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Programmer on 2016/11/17.
 */
public class getBooks extends Controller {
	
	
	public Result books(){
		
		String string="[";
		ObjectNode jsonNodes = Json.newObject();
		
		List<ebeanTest> tests = Ebean.find(ebeanTest.class)
				.where().like("book_type", "hot")
				.orderBy("book_type desc")
				.findList();
		
		if (!tests.isEmpty()) {
			int i = 0;
			int j=1;
			for (i = 0; i < (tests.size() - 1); i++) {
				
				if (j>11)break;
				
				jsonNodes.put("bookName", tests.get(i).book_name);
				jsonNodes.put("price", tests.get(i).book_price);
				jsonNodes.put("type", tests.get(i).book_type);
				jsonNodes.put("imageUrl", tests.get(i).book_url);
				jsonNodes.put("ID", tests.get(i).id);
				
				string += jsonNodes.toString();
				string += ",";
				
				j++;
			}
			
			jsonNodes.put("bookName", tests.get(i).book_name);
			jsonNodes.put("price", tests.get(i).book_price);
			jsonNodes.put("type", tests.get(i).book_type);
			jsonNodes.put("imageUrl", tests.get(i).book_url);
			jsonNodes.put("ID", tests.get(i).id);
			
			string += jsonNodes.toString();
			
			
		}
		string += "]";
		return ok(string);
		
	}

}
