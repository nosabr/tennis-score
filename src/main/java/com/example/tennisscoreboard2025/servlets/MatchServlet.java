package com.example.tennisscoreboard2025.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/match")
public class MatchServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().println(uuid);
    }
}
