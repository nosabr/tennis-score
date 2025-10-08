package com.example.tennisscoreboard2025.servlets;

import com.example.tennisscoreboard2025.models.Match;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/new-match.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p1 =  req.getParameter("p1");
        String p2 = req.getParameter("p2");
        UUID uuid =  UUID.randomUUID();
        resp.sendRedirect(req.getContextPath()+"/match?uuid=" + uuid);
    }
}
