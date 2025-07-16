package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.java.swing.plaf.windows.resources.windows;
import hibernate.HibernateUtil;
import hibernate.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

@WebServlet(name = "SignIn", urlPatterns = {"/SignIn"})
public class SignIn extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        JsonObject signIn = gson.fromJson(request.getReader(), JsonObject.class);

        String email = signIn.get("email").getAsString();
        String password = signIn.get("password").getAsString();

        JsonObject responseObject = new JsonObject();
        responseObject.addProperty("status", false);

        if (email.isEmpty()) {
            responseObject.addProperty("message", "E-Mail Can not be empty !");

        } else if (!Util.isEmailValid(email)) {
            responseObject.addProperty("message", "E-Mail is Invalid !");

        } else if (password.isEmpty()) {
            responseObject.addProperty("message", "Password Can not be empty !");

        } else {

            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session s = sf.openSession();

            Criteria criteria = s.createCriteria(User.class);

            Criterion crt1 = Restrictions.eq("email", email);
            Criterion crt2 = Restrictions.eq("password", password);

            criteria.add(crt1);
            criteria.add(crt2);

            if (criteria.list().isEmpty()) {
                responseObject.addProperty("message", "Invalid Credentials!");
            } else {

                User user = (User) criteria.list().get(0);
                HttpSession ses = request.getSession();
                responseObject.addProperty("status", true);

                if (!user.getVerification().equals("verified")) {
                    ses.setAttribute("email", email);
                    responseObject.addProperty("message", "1");

                } else {
                    ses.setAttribute("user", user);
                    responseObject.addProperty("message", "2");
                }
            }
            s.close();
        }

        String responseText = gson.toJson(responseObject);
        response.setContentType("application/json");
        response.getWriter().write(responseText);

    }

  

}
