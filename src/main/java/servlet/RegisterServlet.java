package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.User;
import service.UserService;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirmPassword");

        if(!password.equals(confirm)){
            request.setAttribute("error",
            "パスワードが一致しません。もう一度確認してください。");
            request.getRequestDispatcher("register.jsp")
            .forward(request,response);
            return;
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        UserService service = new UserService();

        if(service.register(user)){
            response.sendRedirect("mypage.jsp");
        }else{
            request.setAttribute("error","会員加入失敗");
            request.getRequestDispatcher("register.jsp")
            .forward(request,response);
        }
    }
}