package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Book;
import models.Category;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Programmer on 2016/11/19.
 */
public class Books extends Controller {
	
	public Result DisplayBooks(String typeStr){
		
		String string;
		
		if (typeStr.equals("rec")){
			string=getFind(typeStr, 6);
		}
		else if (typeStr.equals("hot")){
			string=getFind(typeStr, 10);
		}
		else if (typeStr.equals("new")){
			string=getFind(typeStr, 10);
		}
		else {
			string=getFind(typeStr, 100);
		}
		
		return ok(string);
	}
	
	public static String getFind(String typeStr, int length){
		
		String str="[";
		ObjectNode jsonNodes = Json.newObject();
		List<Book> tests;
		Category categories;
		
		if (typeStr.equals("rec")){
			tests = Ebean.find(Book.class)
					.findList();
		}
		else if (typeStr.equals("hot")){
			tests = Ebean.find(Book.class)
					.where().select("access_count")
					.orderBy("access_count desc")
					.findList();
		}
		else if (typeStr.equals("new")){
			tests = Ebean.find(Book.class)
					.where().select("date")
					.orderBy("date desc")
					.findList();
		}
		else {
			categories = Ebean.find(Category.class, typeStr);
			tests = categories.books;
		}
		
		if (!tests.isEmpty()) {
			int i = 0;
			int j = 1;
			for (i = 0; i < (tests.size() - 1); i++, j++) {
				
				if (j>(length-1))break;
				
				jsonNodes.put("name", tests.get(i).name);
				jsonNodes.put("price", tests.get(i).price);
				jsonNodes.put("category", tests.get(i).category.name);
				jsonNodes.put("image", tests.get(i).image);
				jsonNodes.put("ID", tests.get(i).id);
				jsonNodes.put("description", tests.get(i).description);
				jsonNodes.put("author", tests.get(i).author);
				jsonNodes.put("stock", tests.get(i).stock);
				
				str += jsonNodes.toString();
				str += ",";
				
			}
			
			jsonNodes.put("name", tests.get(i).name);
			jsonNodes.put("price", tests.get(i).price);
			jsonNodes.put("category", tests.get(i).category.name);
			jsonNodes.put("image", tests.get(i).image);
			jsonNodes.put("ID", tests.get(i).id);
			jsonNodes.put("description", tests.get(i).description);
			jsonNodes.put("author", tests.get(i).author);
			jsonNodes.put("stock", tests.get(i).stock);
			
			str += jsonNodes.toString();
		}
		
		str += "]";
		return str;
	}
}
