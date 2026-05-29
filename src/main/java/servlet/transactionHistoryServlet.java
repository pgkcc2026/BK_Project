package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Account;
import model.transaction;
import model.User;
import service.AccountService;
import service.transactionService;



@WebServlet("/transactionHistoryServlet")
public class transactionHistoryServlet extends HttpServlet  {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
            return;
        }

        AccountService accountService = new AccountService();
        Account account = accountService.getAccountByUserId(loginUser.getUserId());

        if (account == null) {
            request.setAttribute("error", "口座が作成されていません。");
            request.getRequestDispatcher("/history.jsp").forward(request, response);
            return;
        }

        transactionService transactionService = new transactionService();

        List<transaction> transactionList =
                transactionService.getTransactionList(account.getAccountId());

        request.setAttribute("transactionList", transactionList);

        request.getRequestDispatcher("/history.jsp").forward(request, response);
    }
}