
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@MultipartConfig
@WebServlet(name = "SaveProduct", urlPatterns = {"/SaveProduct"})
public class SaveProduct extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
           String brandId =  request.getParameter("brandId");
           String modelId =  request.getParameter("modelId");
           String title =  request.getParameter("title");
           String description =  request.getParameter("description");
           String storageid =  request.getParameter("storageid");
           String colorid =  request.getParameter("colorid");
           String conditionid =  request.getParameter("conditionid");
           String qty =  request.getParameter("qty");
           String image1 =  request.getParameter("image1");
           String image2 =  request.getParameter("image2");
           String image3 =  request.getParameter("image3");
           
           System.out.println(conditionid);
    

           
           
    }

   
}
