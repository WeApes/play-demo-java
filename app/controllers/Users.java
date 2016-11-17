package controllers;

import Models.User;
import com.google.common.base.Strings;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;

import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.redirect;

/**
 * Created by lawrence on 16/11/2016.
 */
public class Users {
    public Result login() {
        final DynamicForm dynamicForm =
                Form.form().bindFromRequest(request());

        final String user_name   = dynamicForm.get("user_name");
        final String password    = dynamicForm.get("password");
        final String captcha     = dynamicForm.get("captcha");

        final String is_remember = dynamicForm.get("is_remember");
        final Boolean bIs_remember = Boolean.valueOf(is_remember);



    }

    public Result logout() {

    }



    public Result register() {
        final DynamicForm dynamicForm =
                Form.form().bindFromRequest(request());
        final String user_name = dynamicForm.get("user_name");
        final String password = dynamicForm.get("password");
        final String email = dynamicForm.get("email");


        if (Strings.isNullOrEmpty(user_name) ||
                user_name.length() > 40 || Strings.isNullOrEmpty(password)) {
            return badRequest("paras are wrong.");
        }

        final User userByUserName = User.getUserByUserName(email);
        if (userByUserName != null) {
            return badRequest("user is registered");
        }

        final User user = new User(user_name, email, password);

        user.save();
        return redirect("/");
    }
}
