package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.bookDetail;
import models.ebeanTest;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Programmer on 2016/11/19.
 */
public class Books extends Controller {
	
	//显示hot 的book数量
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
	
	//显示new 的book数量
	public Result getNew(){
		String string="[";
		ObjectNode jsonNodes = Json.newObject();
		
		List<ebeanTest> tests = Ebean.find(ebeanTest.class)
				.where().like("book_type", "new")
				.orderBy("book_type desc")
				.findList();
		
		if (!tests.isEmpty()) {
			int i = 0;
			int j=1;
			for (i = 0; i < (tests.size() - 1); i++) {
				
				if (j>5)break;
				
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
	
	//显示book的具体信息
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
