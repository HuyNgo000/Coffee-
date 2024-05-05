package Controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/admin"})
public class AdminPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jspPath = "/views/admin/adminPage.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(jspPath);
        dispatcher.forward(req, resp);
        
    }
    
}