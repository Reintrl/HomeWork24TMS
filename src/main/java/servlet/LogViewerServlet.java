package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/logs")
public class LogViewerServlet extends HttpServlet {
    private static final String LOG_FILE_PATH = "C:\\Users\\reinb\\IdeaProjects\\HomeWork24TMS\\log.txt";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Просмотр логов</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Логи сервлета</h1>");
            
            if (Files.exists(Paths.get(LOG_FILE_PATH))) {
                try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
                    String line;
                    out.println("<div class=\"logs-container\">");
                    while ((line = reader.readLine()) != null) {
                        out.println("<div class=\"log-entry\">");
                        out.println("<span class=\"timestamp\">" + line.substring(0, line.indexOf(']') + 1) + "</span>");
                        out.println("<span>" + line.substring(line.indexOf(']') + 1) + "</span>");
                        out.println("</div>");
                    }
                    out.println("</div>");
                }
            } else {
                out.println("<p>Файл логов не найден</p>");
            }

            out.println("</body>");
            out.println("</html>");
            
        } finally {
            out.close();
        }
    }
}