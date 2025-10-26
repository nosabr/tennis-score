<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Tennis Scoreboard — Match</title>
    <style>
        .match-finished-overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.8);
            display: flex;
            justify-content: center;
            align-items: center;
            z-index: 1000;
        }

        .match-result-card {
            background: white;
            padding: 40px;
            border-radius: 15px;
            text-align: center;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.3);
            max-width: 500px;
            animation: slideIn 0.5s ease-out;
        }

        @keyframes slideIn {
            from {
                transform: translateY(-50px);
                opacity: 0;
            }
            to {
                transform: translateY(0);
                opacity: 1;
            }
        }

        .match-result-card h2 {
            color: #2ecc71;
            margin-bottom: 20px;
            font-size: 2em;
        }

        .match-result-card h3 {
            color: #333;
            margin-bottom: 15px;
        }

        .match-result-card p {
            color: #666;
            font-size: 1.2em;
            margin-bottom: 30px;
        }

        .btn-home {
            display: inline-block;
            padding: 12px 30px;
            background: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: background 0.3s;
        }

        .btn-home:hover {
            background: #2980b9;
        }
        :root{
            --bg:#0b0f17;--panel:#111827;--text:#e5e7eb;--muted:#9ca3af;
            --divider:#1f2937;--radius:12px;--shadow:0 4px 16px rgba(0,0,0,.25);
            --accent:#22d3ee;--danger:#ef4444;
        }
        html,body{
            height:100%;margin:0;background:var(--bg);color:var(--text);
            font:16px/1.45 ui-sans-serif,system-ui,-apple-system,Segoe UI,Roboto,"Helvetica Neue",Arial;
        }
        .page{min-height:100%;display:grid;grid-template-rows:auto 1fr auto;}
        .header{background:var(--panel);border-bottom:1px solid var(--divider);}
        .header__inner{max-width:960px;margin:0 auto;padding:16px;text-align:center;font-weight:700;font-size:20px;}
        .content{display:grid;place-items:start center;padding:32px;}
        .card{
            width:min(960px,100%);
            background:var(--panel);
            border:1px solid var(--divider);
            border-radius:var(--radius);
            box-shadow:var(--shadow);
            padding:24px;
        }
        .title{font-weight:800;font-size:18px;margin-bottom:6px;}
        .sub{color:var(--muted);font-size:13px;margin-bottom:16px;}
        .sets{display:flex;gap:8px;flex-wrap:wrap;margin-bottom:12px;}
        .set{
            padding:6px 10px;border-radius:8px;
            border:1px solid var(--divider);
            background:#0f1522;color:var(--muted);font-size:13px;
        }
        .set.active{border-color:var(--accent);color:var(--accent);font-weight:700;}

        table{
            width:100%;border-collapse:collapse;background:#0f1522;
            border-radius:var(--radius);overflow:hidden;margin-bottom:16px;
        }
        th,td{
            padding:14px 12px;border-bottom:1px solid var(--divider);text-align:center;
        }
        th{color:var(--muted);font-weight:600;background:#101520;}
        td:first-child,th:first-child{text-align:left;}
        tr:hover td{background:#0d1320;}
        .player-name{font-weight:600;}

        .actions{
            display:flex;flex-wrap:wrap;gap:10px;justify-content:flex-end;
        }
        .btn{
            appearance:none;border:1px solid rgba(34,211,238,.55);
            background:rgba(34,211,238,.08);color:var(--text);
            padding:12px 18px;border-radius:12px;font-weight:700;
            cursor:pointer;transition:all .15s;
        }
        .btn:hover{background:rgba(34,211,238,.14);}
        .btn-ghost{border-color:var(--divider);background:#0f1522;}
        .btn-danger{border-color:#fecaca;background:#7f1d1d;color:#fee2e2;}
        .footer{max-width:960px;margin:18px auto 28px;color:var(--muted);font-size:14px;text-align:center;}
        code{background:#0f1522;border:1px solid var(--divider);padding:2px 6px;border-radius:6px;}
        .breadcrumbs{color:var(--muted);font-size:14px;margin-bottom:14px;}
        .breadcrumbs a{color:var(--muted);text-decoration:none;}
        .breadcrumbs a:hover{color:var(--accent);}
    </style>
</head>
<body>
<c:if test="${matchJustFinished}">
    <div class="match-finished-overlay">
        <div class="match-result-card">
            <h2>🏆 Матч завершён!</h2>
            <h3>Победитель:${match.winner.name}</h3>
            <a href="${pageContext.request.contextPath}/home" class="btn-home">На главную</a>
        </div>
    </div>
</c:if>
<div class="page">
    <header class="header">
        <div class="header__inner">Tennis Scoreboard</div>
    </header>

    <main class="content">
        <section class="card" aria-label="Текущий матч">
            <div class="breadcrumbs">
                <a href="${pageContext.request.contextPath}/home">Home</a> ·
                <a href="${pageContext.request.contextPath}/matches">Matches</a> ·
                <span>Match</span>
            </div>

            <div class="title">${match.player1.name} vs ${match.player2.name}</div>
            <div class="sub">Match ID: <code>${param.uuid}</code></div>

            <div class="sets">
                <div class="set ${match.score.currentSet == 0 ? 'active' : ''}">Set 1</div>
                <div class="set ${match.score.currentSet == 1 ? 'active' : ''}">Set 2</div>
                <div class="set ${match.score.currentSet == 2 ? 'active' : ''}">Set 3</div>
            </div>

            <table>
                <thead>
                <tr>
                    <th>Player</th>
                    <th>Points</th>
                    <th>Set 1</th>
                    <th>Set 2</th>
                    <th>Set 3</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="player-name">${match.player1.name}</td>
                    <td>${match.score.firstPlayerPoints}</td>
                    <td>${match.score.sets[0].first}</td>
                    <td>${match.score.sets[1].first}</td>
                    <td>${match.score.sets[2].first}</td>
                </tr>
                <tr>
                    <td class="player-name">${match.player2.name}</td>
                    <td>${match.score.secondPlayerPoints}</td>
                    <td>${match.score.sets[0].second}</td>
                    <td>${match.score.sets[1].second}</td>
                    <td>${match.score.sets[2].second}</td>
                </tr>
                </tbody>
            </table>

            <form class="actions" method="post" action="">
                <input type="hidden" name="uuid" value="${param.uuid}">
                <button class="btn" name="player" value="1">+ Point · ${match.player1.name}</button>
                <button class="btn" name="player" value="2">+ Point · ${match.player2.name}</button>
                <span style="flex:1"></span>
            </form>
        </section>
    </main>

    <footer class="footer">by Syrym Sabyrzhan</footer>
</div>
</body>
</html>
