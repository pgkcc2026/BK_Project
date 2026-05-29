package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.User;
import service.UserService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserService service = new UserService();
        User user = service.login(email,password);

        if(user != null){

            HttpSession session =
                request.getSession();

            session.setAttribute("loginUser", user);

            response.sendRedirect("mypage.jsp");

        }else{

            request.setAttribute("error",
            "ログイン失敗");

            request.getRequestDispatcher("Login.jsp")
            .forward(request,response);
        }
    }
}