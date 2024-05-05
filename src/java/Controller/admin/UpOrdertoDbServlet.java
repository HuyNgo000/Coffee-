package Controller.admin;

import DAO.SQLDataAccess;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/up-order-toDb"})
public class UpOrdertoDbServlet extends HttpServlet {

    public void addOrder(String name, String email, String phone, String date, String time, String guests) {
        SQLDataAccess db = new SQLDataAccess();
        String sql = "INSERT INTO customer (name, email, phone, date, time, guests) VALUES (?, ?, ?, ?, ?, ?)";
        Object[] param = {name, email, phone, date, time, guests};
        db.ExecuteSQL(sql, param);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String guests = request.getParameter("guests");

        addOrder(name, email, phone, date, time, guests);

        response.sendRedirect("index#booking");
    }
}
