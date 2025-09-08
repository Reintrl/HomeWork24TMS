package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@WebServlet(name = "TimeServlet", urlPatterns = {"/minsk", "/washington", "/beijing"})
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String path = request.getServletPath();
        String city = "";
        ZoneId zoneId = null;

        switch (path) {
            case "/minsk":
                city = "Минск";
                zoneId = ZoneId.of("Europe/Minsk");
                break;
            case "/washington":
                city = "Вашингтон";
                zoneId = ZoneId.of("America/New_York");
                break;
            case "/beijing":
                city = "Пекин";
                zoneId = ZoneId.of("Asia/Shanghai");
                break;
        }

        ZonedDateTime time = ZonedDateTime.now(zoneId);

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Время в " + city + "</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Текущее время в " + city + "</h1>");
        out.println("<div class=\"time\">" + time + "</div>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}