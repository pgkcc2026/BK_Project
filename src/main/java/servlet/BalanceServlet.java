package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Account;
import model.User;
import service.AccountService;

@WebServlet("/BalanceServlet")
public class BalanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        AccountService service = new AccountService();
        Account account = service.getAccountByUserId(loginUser.getUserId());

        if (account == null) {
            request.setAttribute("error", "口座が開設されていません。");
        } else {
            request.setAttribute("account", account);
        }

        request.getRequestDispatcher("/balance.jsp").forward(request, response);
    }
}
