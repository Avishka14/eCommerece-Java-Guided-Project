package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hibernate.HibernateUtil;
import hibernate.User;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Mail;
import model.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        JsonObject user = gson.fromJson(request.getReader(), JsonObject.class);

        String firstName = user.get("firstName").getAsString();
        String lastName = user.get("lastName").getAsString();
        final String email = user.get("email").getAsString();
        String password = user.get("password").getAsString();

        JsonObject responseObject = new JsonObject();
        responseObject.addProperty("status", false);

        if (firstName.isEmpty()) {
            responseObject.addProperty("message", "First Name can not be Empty !");

        } else if (lastName.isEmpty()) {
            responseObject.addProperty("message", "Last Name can not be Empty !");

        } else if (email.isEmpty()) {
            responseObject.addProperty("message", "E-Mail can not be Empty !");

        } else if (!Util.isEmailValid(email)) {
            responseObject.addProperty("message", "Please Enter valid E-Mail !");

        } else if (password.isEmpty()) {
            responseObject.addProperty("message", "Password can not be Empty !");

        } else if (!Util.isPasswordValid(password)) {
            responseObject.addProperty("message", "Password Must include atleast uppercase , lowercase , "
                    + " number , Special character and to be 8 characters long !");

        } else {

            //Save
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session s = sf.openSession();

            Criteria criteria = s.createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));

            if (!criteria.list().isEmpty()) {
                responseObject.addProperty("message", "User with this E-Mail already Extists!");

            } else {
                User u = new User();
                u.setFirst_name(firstName);
                u.setLast_name(lastName);
                u.setEmail(email);
                u.setPassword(password);

                //generate verification
                final String verificationCode = Util.generateCode();
                u.setVerification(verificationCode);

                u.setCreated_at(new Date());

                //hibernate save
                s.save(u);
                s.beginTransaction().commit();

                //send email
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Mail.sendMail(email, "Smart Trade Verify ", "<h1>" + verificationCode + "</h1>");
                    }
                }).start();
                
                //create a session
                HttpSession ses = request.getSession();
                ses.setAttribute("email", email);
                
                responseObject.addProperty("status", true);
                responseObject.addProperty("message", "Registration Success ! Please Check Your "
                        + "E-Mail for the Verification Code");
            }

            s.close();
        }

        String responseText = gson.toJson(responseObject);
        response.setContentType("application/json");
        response.getWriter().write(responseText);

    }

}
