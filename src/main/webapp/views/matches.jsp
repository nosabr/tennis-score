<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Tennis Scoreboard — Matches</title>
    <style>
        :root{
            --bg:#0b0f17;--panel:#111827;--text:#e5e7eb;--muted:#9ca3af;
            --divider:#1f2937;--radius:12px;--shadow:0 4px 16px rgba(0,0,0,.25);
            --accent:#22d3ee;--danger:#ef4444;--success:#10b981;
        }
        html,body{
            height:100%;margin:0;background:var(--bg);color:var(--text);
            font:16px/1.45 ui-sans-serif,system-ui,-apple-system,Segoe UI,Roboto,"Helvetica Neue",Arial;
        }
        .page{min-height:100%;display:grid;grid-template-rows:auto 1fr auto;}
        .header{background:var(--panel);border-bottom:1px solid var(--divider);}
        .header__inner{max-width:1200px;margin:0 auto;padding:16px;text-align:center;font-weight:700;font-size:20px;}
        .content{display:grid;place-items:start center;padding:32px;}
        .card{
            width:min(1200px,100%);
            background:var(--panel);
            border:1px solid var(--divider);
            border-radius:var(--radius);
            box-shadow:var(--shadow);
            padding:24px;
        }
        .title{font-weight:800;font-size:18px;margin-bottom:16px;}
        .breadcrumbs{color:var(--muted);font-size:14px;margin-bottom:14px;}
        .breadcrumbs a{color:var(--muted);text-decoration:none;}
        .breadcrumbs a:hover{color:var(--accent);}

        .search-form{
            display:flex;gap:10px;margin-bottom:20px;flex-wrap:wrap;
            padding:16px;background:#0f1522;border-radius:var(--radius);
            border:1px solid var(--divider);
        }
        .search-form input[type="text"]{
            flex:1;min-width:200px;
            appearance:none;border:1px solid var(--divider);
            background:var(--panel);color:var(--text);
            padding:10px 14px;border-radius:8px;font-size:15px;
            transition:border-color .15s;
        }
        .search-form input[type="text"]:focus{
            outline:none;border-color:var(--accent);
        }
        .btn{
            appearance:none;border:1px solid rgba(34,211,238,.55);
            background:rgba(34,211,238,.08);color:var(--text);
            padding:10px 18px;border-radius:8px;font-weight:600;
            cursor:pointer;transition:all .15s;text-decoration:none;
            display:inline-block;font-size:15px;
        }
        .btn:hover{background:rgba(34,211,238,.14);}
        .btn-ghost{border-color:var(--divider);background:#0f1522;}
        .btn-ghost:hover{background:#101520;}

        .info-text{
            color:var(--muted);font-size:14px;margin-bottom:16px;
        }
        .info-text strong{color:var(--text);}

        /* Таблица матчей */
        .matches-table-wrapper{
            overflow-x:auto;
            margin-bottom:20px;
            border-radius:var(--radius);
            border:1px solid var(--divider);
        }
        .matches-table{
            width:100%;
            border-collapse:collapse;
            background:#0f1522;
        }
        .matches-table th{
            background:var(--panel);
            color:var(--muted);
            text-align:left;
            padding:14px 16px;
            font-weight:600;
            font-size:14px;
            text-transform:uppercase;
            letter-spacing:0.5px;
            border-bottom:1px solid var(--divider);
        }
        .matches-table th:first-child{
            border-top-left-radius:var(--radius);
        }
        .matches-table th:last-child{
            border-top-right-radius:var(--radius);
        }
        .matches-table td{
            padding:16px;
            border-bottom:1px solid var(--divider);
            font-size:15px;
        }
        .matches-table tr:last-child td{
            border-bottom:none;
        }
        .matches-table tr:hover{
            background:rgba(34,211,238,.04);
        }
        .match-id-cell{
            color:var(--muted);
            font-weight:600;
            white-space:nowrap;
        }
        .player-cell{
            font-weight:600;
        }
        .winner{
            color:var(--success);
        }
        .loser{
            color:var(--danger);
        }
        .vs-separator{
            color:var(--muted);
            padding:0 8px;
        }

        .pagination{
            display:flex;justify-content:center;align-items:center;
            gap:8px;margin-top:24px;flex-wrap:wrap;
        }
        .pagination a,
        .pagination span{
            padding:8px 12px;border:1px solid var(--divider);
            border-radius:8px;text-decoration:none;color:var(--text);
            transition:all .15s;min-width:40px;text-align:center;
            background:#0f1522;font-size:14px;
        }
        .pagination a:hover{
            border-color:var(--accent);background:rgba(34,211,238,.08);
        }
        .pagination .current{
            background:rgba(34,211,238,.12);border-color:var(--accent);
            color:var(--accent);font-weight:700;
        }
        .pagination .disabled{
            opacity:.4;cursor:not-allowed;
        }
        .pagination .dots{border:none;background:transparent;}

        .no-matches{
            text-align:center;padding:48px 20px;
            color:var(--muted);font-size:16px;
        }

        .footer{max-width:1200px;margin:18px auto 28px;color:var(--muted);font-size:14px;text-align:center;}

        @media (max-width: 768px) {
            .search-form{flex-direction:column;}
            .search-form input[type="text"]{width:100%;}
            .matches-table{font-size:14px;}
            .matches-table th,
            .matches-table td{padding:10px 12px;}
        }
    </style>
</head>
<body>
<div class="page">
    <header class="header">
        <div class="header__inner">Tennis Scoreboard</div>
    </header>

    <main class="content">
        <section class="card" aria-label="Список матчей">
            <div class="breadcrumbs">
                <a href="${pageContext.request.contextPath}/home">Home</a> ·
                <span>Matches</span>
            </div>

            <div class="title">🎾 Сыгранные матчи</div>

            <!-- Форма поиска -->
            <form class="search-form" action="${pageContext.request.contextPath}/matches" method="GET">
                <input
                        type="text"
                        name="filter_by_player_name"
                        placeholder="Введите имя игрока..."
                        value="${filterByPlayerName != null ? filterByPlayerName : ''}"
                >
                <button type="submit" class="btn">🔍 Искать</button>
                <c:if test="${filterByPlayerName != null && !filterByPlayerName.isEmpty()}">
                    <a href="${pageContext.request.contextPath}/matches" class="btn btn-ghost">Сбросить</a>
                </c:if>
            </form>

            <!-- Информация о результатах -->
            <c:if test="${totalMatches > 0}">
                <p class="info-text">
                    <c:choose>
                        <c:when test="${filterByPlayerName != null && !filterByPlayerName.isEmpty()}">
                            Найдено матчей для игрока "<strong>${filterByPlayerName}</strong>": ${totalMatches}
                        </c:when>
                        <c:otherwise>
                            Всего матчей: <strong>${totalMatches}</strong>
                        </c:otherwise>
                    </c:choose>
                </p>
            </c:if>

            <!-- Таблица матчей -->
            <c:choose>
                <c:when test="${matches != null && matches.size() > 0}">
                    <div class="matches-table-wrapper">
                        <table class="matches-table">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Игрок 1</th>
                                <th>Игрок 2</th>
                                <th>Победитель</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="match" items="${matches}">
                                <tr>
                                    <td class="match-id-cell">#${match.id}</td>
                                    <td class="player-cell">
                                        <span class="${match.winner.id == match.player1.id ? 'winner' : 'loser'}">
                                                ${match.player1.name}
                                        </span>
                                    </td>
                                    <td class="player-cell">
                                        <span class="${match.winner.id == match.player2.id ? 'winner' : 'loser'}">
                                                ${match.player2.name}
                                        </span>
                                    </td>
                                    <td class="player-cell winner">
                                        🏆 ${match.winner.name}
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <!-- Пагинация -->
                    <c:if test="${totalPages > 1}">
                        <div class="pagination">
                            <!-- Предыдущая страница -->
                            <c:choose>
                                <c:when test="${currentPage > 1}">
                                    <a href="?page=${currentPage - 1}<c:if test='${filterByPlayerName != null}'>&filter_by_player_name=${filterByPlayerName}</c:if>">
                                        ← Назад
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <span class="disabled">← Назад</span>
                                </c:otherwise>
                            </c:choose>

                            <!-- Номера страниц -->
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <c:choose>
                                    <c:when test="${i == currentPage}">
                                        <span class="current">${i}</span>
                                    </c:when>
                                    <c:when test="${i == 1 || i == totalPages || (i >= currentPage - 2 && i <= currentPage + 2)}">
                                        <a href="?page=${i}<c:if test='${filterByPlayerName != null}'>&filter_by_player_name=${filterByPlayerName}</c:if>">
                                                ${i}
                                        </a>
                                    </c:when>
                                    <c:when test="${i == currentPage - 3 || i == currentPage + 3}">
                                        <span class="dots">...</span>
                                    </c:when>
                                </c:choose>
                            </c:forEach>

                            <!-- Следующая страница -->
                            <c:choose>
                                <c:when test="${currentPage < totalPages}">
                                    <a href="?page=${currentPage + 1}<c:if test='${filterByPlayerName != null}'>&filter_by_player_name=${filterByPlayerName}</c:if>">
                                        Вперёд →
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <span class="disabled">Вперёд →</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <div class="no-matches">
                        <c:choose>
                            <c:when test="${filterByPlayerName != null && !filterByPlayerName.isEmpty()}">
                                🔍 Матчи для игрока "${filterByPlayerName}" не найдены
                            </c:when>
                            <c:otherwise>
                                📭 Пока не сыграно ни одного матча
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:otherwise>
            </c:choose>
        </section>
    </main>

    <footer class="footer">by Syrym Sabyrzhan</footer>
</div>
</body>
</html>