/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import entity.City;
import entity.Country;
import facade.JPAFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nikolaj
 */
@WebServlet(name = "CountryServlet", urlPatterns = {"/api/country/*"})
public class CountryServlet extends HttpServlet {

    JPAFacade facade = new JPAFacade();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String[] split = request.getRequestURI().split("/");
            
            String returnJson = "";
            
            if (split.length >= 4){
                if (split[3].equals("greater")) {
                    returnJson = new Gson().toJson(facade.getCountriesGreaterThan(Long.parseLong(split[4])));
                } else if (split[3].equals("cities")) {
                    returnJson = new Gson().toJson(facade.getAllCities(split[4]));
                }
            } else {
                returnJson = new Gson().toJson(facade.getAllCountries());
            }
                                 
            out.println(returnJson);
        } finally {
            out.close();
        }
    }
    
    private JsonObject getJson(HttpServletRequest request) throws IOException, JsonSyntaxException {
        Scanner jsonScanner = new Scanner(request.getInputStream());
        String json = "";
        while (jsonScanner.hasNext()) {
            json += jsonScanner.nextLine();
        }
        //Get the quote text from the provided Json
        JsonObject newQuote = new JsonParser().parse(json).getAsJsonObject();
        return newQuote;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonObject json = getJson(request);
        City city = new City();
        
        city.setName(json.get("name").getAsString());
        city.setCountryCode(json.get("code").getAsString());
        facade.create(city);
    }
  
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
