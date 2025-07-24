package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hibernate.Color;
import hibernate.HibernateUtil;
import hibernate.Model;
import hibernate.Product;
import hibernate.Quality;
import hibernate.Status;
import hibernate.Storage;
import hibernate.User;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@MultipartConfig
@WebServlet(name = "SaveProduct", urlPatterns = {"/SaveProduct"})
public class SaveProduct extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String brandId = request.getParameter("brandId");
        String modelId = request.getParameter("modelId");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String storageid = request.getParameter("storageid");
        String colorid = request.getParameter("colorid");
        String conditionid = request.getParameter("conditionid");
        String price = request.getParameter("price");
        String qty = request.getParameter("qty");

        JsonObject responseObject = new JsonObject();
        responseObject.addProperty("status", false);
        
         SessionFactory sf = HibernateUtil.getSessionFactory();
         Session s = sf.openSession();

        //validation
        if (request.getSession().getAttribute("user") == null) {
            responseObject.addProperty("message", "Please sign in!");
      
        } else if(!Util.isInteger(modelId)) {
            responseObject.addProperty("message", "Invalid Model!");
 
        }else{
            Model model = (Model) s.get(Model.class, Integer.parseInt(modelId));
            
            if(model == null){
                 responseObject.addProperty("message", "Invalid Model!");
            }else{
                
                if(!String.valueOf(model.getBrand().getId()).equals(brandId)){
                    responseObject.addProperty("message", "Invalid Brand!");
            
                }else{
                    
                    
                    
                    
                }
                
                
            }
    
        }

        //validation
        
        
           

            
//            Storage storage = (Storage) s.load(Storage.class, Integer.parseInt(storageid));
//            Color color = (Color) s.load(Color.class, Integer.parseInt(colorid));
//            Quality quality = (Quality) s.load(Quality.class, Integer.parseInt(conditionid));
//            Status status = (Status) s.load(Status.class, 1);
//            User user = (User) request.getSession().getAttribute("user");
//
//            Product p = new Product();
//            p.setModel(model);
//            p.setTitle(title);
//            p.setDescription(description);
//            p.setStorage(storage);
//            p.setColor(color);
//            p.setQuality(quality);
//            p.setPrice(Double.parseDouble(price));
//            p.setQty(Integer.parseInt(qty));
//            p.setStatus(status);
//            p.setUser(user);
//            p.setCreated_at(new Date());
//
//            int id = (int) s.save(p);
//            s.beginTransaction().commit();
//            s.close();
//
//            //image uploading
//            Part part1 = request.getPart("image1");
//            Part part2 = request.getPart("image2");
//            Part part3 = request.getPart("image3");
//
//            String appPath = getServletContext().getRealPath(""); //Full path of the web pages folder
//
//            System.out.println(appPath);
//
//            String newPath = appPath.replace("build" + File.separator + "web", "web" + File.separator + "product-images");
//
//            File productFolder = new File(newPath, String.valueOf(id));
//            productFolder.mkdir();
//
//            File file1 = new File(productFolder, "image1.png");
//            Files.copy(part1.getInputStream(), file1.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//            File file2 = new File(productFolder, "image2.png");
//            Files.copy(part2.getInputStream(), file2.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//            File file3 = new File(productFolder, "image3.png");
//            Files.copy(part3.getInputStream(), file3.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//            //image uploading
//            //send response
//            Gson gson = new Gson();
//            response.setContentType("application/json");
//            response.getWriter().write(gson.toJson(responseObject));
        
        
    }

}
