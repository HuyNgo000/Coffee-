/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.admin;

import DAO.SQLDataAccess;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/del-prod"})
public class DeleteProdServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maSP =Integer.parseInt(req.getParameter("maSP"));
        SQLDataAccess con = new SQLDataAccess();
        String sql = "delete from tbMenu where maSP="+maSP;
        con.ExcuteSQL(sql);
        resp.sendRedirect("admin");
    }
    
}
