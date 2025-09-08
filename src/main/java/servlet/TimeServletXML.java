package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeServletXML extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String path = request.getServletPath();
        String city = "";
        ZoneId zoneId = null;

        switch (path) {
            case "/minsk-xml":
                city = "Минск";
                zoneId = ZoneId.of("Europe/Minsk");
                break;
            case "/washington-xml":
                city = "Вашингтон";
                zoneId = ZoneId.of("America/New_York");
                break;
            case "/beijing-xml":
                city = "Пекин";
                zoneId = ZoneId.of("Asia/Shanghai");
                break;
        }

        ZonedDateTime time = ZonedDateTime.now(zoneId);

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Время в " + city + " (XML)</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Текущее время в " + city + " (XML конфигурация)</h1>");
        out.println("<div class=\"time\">" + time + "</div>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}