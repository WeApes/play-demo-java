package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.bookDetail;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Programmer on 2016/11/18.
 */
public class getDetail extends Controller {
	
	public Result detail(){
		JsonNode jsonNode = request().body().asJson();
		
		String id = jsonNode.findPath("id").textValue();
		int ID = Integer.valueOf(id);
		
		String string="[";
		
		ObjectNode jsonNodes = Json.newObject();
		
		bookDetail tests = Ebean.find(bookDetail.class, 2);
		
		jsonNodes.put("bookName", tests.book_name);
		jsonNodes.put("price", tests.book_price);
		jsonNodes.put("author", tests.author);
		jsonNodes.put("imageUrl", tests.book_url);
		jsonNodes.put("ID", tests.id);
		
		string += jsonNodes.toString();
		string += "]";
		
		return ok(string);
	}
}
