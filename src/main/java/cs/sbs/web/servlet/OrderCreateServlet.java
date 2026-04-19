package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class OrderCreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        String customer = req.getParameter("customer");
        String food = req.getParameter("food");
        String quantityRaw = req.getParameter("quantity");

        if (isBlank(customer) || isBlank(food) || isBlank(quantityRaw)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Error: missing required parameters");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityRaw.trim());
        } catch (NumberFormatException ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Error: quantity must be a valid number");
            return;
        }

        Order order = Order.create(customer.trim(), food.trim(), quantity);
        resp.getWriter().println("Order Created: " + order.getId());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
