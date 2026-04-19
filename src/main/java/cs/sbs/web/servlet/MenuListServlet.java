package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class MenuListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        String name = req.getParameter("name");
        List<MenuItem> menuItems = MenuItem.searchByName(name);

        if (menuItems.isEmpty()) {
            resp.getWriter().println("No menu item found");
            return;
        }

        resp.getWriter().println("Menu List:");
        resp.getWriter().println();

        int index = 1;
        for (MenuItem item : menuItems) {
            resp.getWriter().println(index + ". " + item.getName() + " - $" + item.getPrice());
            index++;
        }
    }
}
