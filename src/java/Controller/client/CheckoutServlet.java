/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import Model.Item;
import Model.Order;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");

        // Kiểm tra xem order có tồn tại không
        if (order != null) {
            List<Item> items = order.getItems();
            StringBuilder content = new StringBuilder();

            for (Item item : items) {
                String type = (item.getCoffee().getMaLoai() == 1) ? "Hot" : "Cold";
                content.append("Product: ").append(item.getCoffee().getTen()).append("\n");
                content.append("Type: ").append(type).append("\n");
                content.append("Quantity: ").append(item.getQuantity()).append("\n");
                content.append("Price: $").append(item.getCoffee().getGia()).append("\n");
                //content.append("Total: $").append(item.getCoffee().getGia() * item.getQuantity()).append("\n");
                content.append("\n");
            }

            double totalPrice = 0.0;
            for (Item item : items) {
                totalPrice += item.getCoffee().getGia() * item.getQuantity();
            }

            // Tạo file notepad và ghi vào trong thư mục WEB-INF
            String appPath = request.getServletContext().getRealPath("/");
            String filePath = appPath + "WEB-INF/checkout_items.txt";
            File file = new File(filePath);

            try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
                out.println("Shopping Cart Items:\n");
                out.println(content.toString());
                out.println("Total Price: $" + totalPrice);
            } catch (IOException e) {
                e.printStackTrace();
            }
             // Trả về file cho người dùng để tải xuống
            response.setContentType("text/plain");
            response.setHeader("Content-disposition", "attachment; filename=checkout_items.txt");

            // Đọc file và ghi nội dung vào HttpServletResponse
            try (PrintWriter writer = response.getWriter()) {
                writer.write("Shopping Cart Items:\n");
                writer.write(content.toString());
                writer.write("Total Price: $" + totalPrice);
            } catch (IOException e) {
                e.printStackTrace();
            }
           
           
        } else {
            // Redirect hoặc thông báo nếu giỏ hàng trống
            response.sendRedirect("index#menu");
        }
    }
}

