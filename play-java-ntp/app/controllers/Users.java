package controllers;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import models.User;
import org.apache.commons.codec.digest.Md5Crypt;
import org.mindrot.jbcrypt.BCrypt;
import play.Logger;
import play.api.libs.mailer.MailerClient;
import play.cache.CacheApi;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.mailer.Email;
import play.mvc.Controller;
import play.mvc.Result;
import util.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by lawrence on 16/11/2016.
 */

public class Users extends Controller {
    private MailerClient emailClient;
    private CacheApi cache;

    @Inject
    public Users(CacheApi cache, MailerClient emailClient) {
        this.cache = cache;
        this.emailClient = emailClient;
    }

    public Result getResetPage() {
        final String hash = request().getQueryString("hash");
        final String uid = request().getQueryString("uid");
        final String date = request().getQueryString("date");

        if (!cache.get("resetToken" + uid).equals(hash)) {
            return badRequest();
        }

        session("resetToken", hash);
        session("uid", uid);
        session("date",  date);

        Utils.addCORS();
        return ok(); // TODO: return reset page
    }

    public Result resetPass() {
        final DynamicForm dynamicForm =
                Form.form().bindFromRequest(request());

        final String hash = dynamicForm.get("hash");
        final String uid = dynamicForm.get("uid");
        final String date = dynamicForm.get("date");

        if (!cache.get("resetToken" + uid).equals(hash)) {
            return badRequest();
        }

        Utils.addCORS();

        return ok();
    }

    public Result homePage() {
        final String name = session("name");
        if (Strings.isNullOrEmpty(name)) {
            return ok(new File("public/index.html"))
                    .as("text/html");
        }

        Utils.addCORS();
        return ok(new File("public/index.html"))
                .as("text/html"); //TODO Add Specific Render Or Not
    }

    public Result captcha(String email) {
        DefaultKaptcha captchaCreator = new DefaultKaptcha();
        captchaCreator.setConfig(new Config(new Properties()));
        String text = captchaCreator.createText();

        cache.set(email, text, 10 * 60);

        BufferedImage img = captchaCreator.createImage(text);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            ImageIO.write(img, "jpg", baos);
            baos.flush();
        } catch(IOException e) {
            Logger.debug(e.getMessage());
        }

        Utils.addCORS();
        return ok(baos.toByteArray()).as("image/jpg");
    }

    public Result login() {
        final DynamicForm dynamicForm =
                Form.form().bindFromRequest(request());

        final String user_name   = dynamicForm.get("user_name");
        final String email       = dynamicForm.get("email");
        final String password    = dynamicForm.get("password");
        final String captcha     = dynamicForm.get("captcha");

        final String is_remember = dynamicForm.get("is_remember");
        final boolean bIs_remember = Boolean.valueOf(is_remember);

        final String text = cache.get("email");
        if (!text.equals(captcha)) {
            return redirect("/login");
        }

        final User userByUserName = User.getUserByEmail(email);
        if (!userByUserName.name.equals(user_name) ||
                !BCrypt.checkpw(password, userByUserName.hashedPass)) {
            return badRequest("user is not existed or pass is wrong");
        }

        if (bIs_remember) {
            session("name", user_name);
        }
        Utils.addCORS();
        return redirect("/");
    }

    public Result logout() {
        session().clear();
        return redirect("/");
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

        final User userByUserName = User.getUserByEmail(email);

        if (userByUserName != null) {
            return badRequest("user is registered");
        }

        final User user = new User(user_name, email, password);
        user.save();

        Utils.addCORS();
        return redirect("/");
    }

    public Result forget() {
        final DynamicForm dynamicForm =
                Form.form().bindFromRequest(request());

        final String captcha = dynamicForm.get("captcha");

        final String text = cache.get("text");
        if (!text.equals(captcha)) {
            return badRequest("Wrong Captcha");
        }

        final String email = dynamicForm.get("email");

        final User lostUser = User.find
                .where().eq("email", email)
                .findUnique();

        if (lostUser == null) {
            return badRequest();
        }

        final Date date = new Date();
        final String uid = lostUser.id;
        final String hash = Md5Crypt.md5Crypt((date.toString() + uid).getBytes(),
                "i am a salt");

        cache.set("resetToken" + uid, hash, 15 * 60);

        final List<String> to = new ArrayList<>();

        to.add(email);
        final Email toSend = new Email()
                .setSubject("Reset Passcode")
                .setFrom("Shop <llpookk@163.com>")
                .setTo(to)
                .setBodyHtml("Click the link --> "
                        + "http://localhost:9000/resetpass?"
                        + "date=" + date.toString() + "&"
                        + "uid=" + uid + "&"
                        + "hash" + hash);
        emailClient.send(toSend);

        Utils.addCORS();
        return ok();
    }
}