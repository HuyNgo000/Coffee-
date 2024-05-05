package Controller.admin;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import DAO.SQLDataAccess;
import jakarta.servlet.ServletContext;

/**
 *
 * @author AD
 */
@WebServlet(urlPatterns = {"/up-prod-to-db"})
@MultipartConfig
public class UpProducttoDbServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String tenSP = request.getParameter("fTsp");
            String donGia = request.getParameter("fDonGia");
            Part filePart = request.getPart("fimg");
            String fileName = filePart.getSubmittedFileName();
            InputStream fileContent = filePart.getInputStream();
            String moTa = request.getParameter("fMota");
            int maLoai = Integer.parseInt(request.getParameter("fLoai"));
            
            ServletContext context = getServletContext();
            String realpath = context.getRealPath("/assets/img/" + fileName);

            SQLDataAccess con = new SQLDataAccess();
            String SQL = "INSERT INTO tbMenu(tenSP, donGia, hinhAnh, moTa, maLoai) VALUES (?, ?, ?, ?, ?)";
            Object[] param = {tenSP, donGia, fileName, moTa, maLoai};
            int k = con.ExecuteSQL(SQL, param);
            
            // Lưu file vào thư mục
            java.nio.file.Path filePath = java.nio.file.Paths.get(realpath);
            java.nio.file.Files.copy(fileContent, filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            
            response.sendRedirect("index#menu");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
