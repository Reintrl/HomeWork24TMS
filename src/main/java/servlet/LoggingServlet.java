package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet("/logging")
public class LoggingServlet extends HttpServlet {
    private PrintWriter pr;
    private static final String LOG_FILE_PATH = "C:\\Users\\reinb\\IdeaProjects\\HomeWork24TMS\\log.txt";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logEvent("Обработка GET запроса ");
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head><title>Logging Servlet</title></head>");
        out.println("<body>");
        out.println("<h1>Логирование активно</h1>");
        out.println("<p>События записываются в файл: " + LOG_FILE_PATH + "</p>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            pr = new PrintWriter(new FileWriter(LOG_FILE_PATH, true));
            logEvent("Инициализация сервлета");
        } catch (IOException e) {
            throw new ServletException("Не удалось открыть файл логов: " + e.getMessage(), e);
        }
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logEvent("Начало обработки запроса: " + req.getMethod() + " " + req.getRequestURI());
        super.service(req, resp);
        logEvent("Завершение обработки запроса: " + req.getMethod() + " " + req.getRequestURI());
    }

    @Override
    public void destroy() {
        logEvent("Уничтожение сервлета");
        if (pr != null) {
            pr.close();
        }
        super.destroy();
    }

    private synchronized void logEvent(String message) {
        String logMessage = "[" + LocalDateTime.now() + "] " + message;

        pr.println(logMessage);
        pr.flush();

        System.out.println("LOG: " + logMessage);
    }
}
