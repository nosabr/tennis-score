package com.example.tennisscoreboard2025.servlets;

import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.services.OngoingMatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/match")
public class MatchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();

    }
}
