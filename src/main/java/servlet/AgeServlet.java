package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

@WebServlet("/adult")
public class AgeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int age = Integer.parseInt(req.getParameter("age"));
        Writer writer = resp.getWriter();

        if (age >= 18) {
            writer.append("Adult.");
        } else {
            writer.append("Not adult.");
        }
        writer.close();
    }
}
