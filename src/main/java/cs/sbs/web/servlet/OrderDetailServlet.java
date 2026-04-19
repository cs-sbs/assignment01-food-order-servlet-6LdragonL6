package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class OrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.length() <= 1) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Error: order id is required");
            return;
        }

        String idRaw = pathInfo.substring(1);
        int orderId;
        try {
            orderId = Integer.parseInt(idRaw);
        } catch (NumberFormatException ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Error: order id must be a valid number");
            return;
        }

        Order order = Order.findById(orderId);
        if (order == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println("Error: Order not found");
            return;
        }

        resp.getWriter().println("Order Detail");
        resp.getWriter().println();
        resp.getWriter().println("Order ID: " + order.getId());
        resp.getWriter().println("Customer: " + order.getCustomer());
        resp.getWriter().println("Food: " + order.getFood());
        resp.getWriter().println("Quantity: " + order.getQuantity());
    }
}
