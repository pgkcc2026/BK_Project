package servlet;


import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.User;
import service.AccountService;

@WebServlet("/AccountCreateServlet")
public class AccountCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int userId = loginUser.getUserId();

        String accountNumber = "100" + new Random().nextInt(9000000);

        AccountService service = new AccountService();
        boolean result = service.createAccount(userId, accountNumber);

        if (result) {
            request.setAttribute("message", "口座開設が完了しました。口座番号：" + accountNumber);
        } else {
            request.setAttribute("error", "すでに口座が存在しています。");
        }

        request.getRequestDispatcher("/accountCreate.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/accountCreate.jsp");
    }
}
