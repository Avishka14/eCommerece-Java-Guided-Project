
package controller;


import com.google.gson.Gson;
import hibernate.City;
import hibernate.HibernateUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Avishka Chamod
 */
@WebServlet(name = "CityData", urlPatterns = {"/CityData"})
public class CityData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
        
       Session s =  HibernateUtil.getSessionFactory().openSession();
       Criteria c = s.createCriteria(City.class);
       List<City> cityList = c.list();
       
        Gson gson = new Gson();
        String toJson = gson.toJson(cityList);
        response.setContentType("application/json");
        response.getWriter().write(toJson);
        s.close();
    }

    
   
}
