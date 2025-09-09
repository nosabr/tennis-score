
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Tennis Scoreboard — Main Menu</title>
    <style>
        :root{
            --bg:#0b0f17;--panel:#111827;--accent:#22d3ee;--accent-2:#a78bfa;--text:#e5e7eb;--muted:#9ca3af;--divider:#1f2937;--radius:12px;--gap:14px;--shadow:0 4px 16px rgba(0,0,0,.25)
        }
        html,body{height:100%;margin:0;background:var(--bg);color:var(--text);font:16px/1.4 ui-sans-serif,system-ui,-apple-system,Segoe UI,Roboto,"Helvetica Neue",Arial}

        .page{min-height:100%;display:grid;grid-template-rows:auto 1fr;}

        /* ===== HEADER ===== */
        .header{background:var(--panel);border-bottom:1px solid var(--divider);}
        .header__inner{max-width:960px;margin:0 auto;padding:16px;text-align:center;font-weight:700;font-size:20px;}

        /* ===== MAIN ===== */
        .content{display:grid;place-items:center;padding:32px}
        .card{width:min(480px,100%);background:var(--panel);border:1px solid var(--divider);border-radius:var(--radius);box-shadow:var(--shadow);padding:28px;text-align:center}

        .menu{display:grid;gap:16px;justify-items:center}

        .btn{appearance:none;border:none;cursor:pointer;text-decoration:none;color:var(--text);background:rgba(255,255,255,.05);border:1px solid var(--divider);padding:14px 20px;border-radius:var(--radius);font-weight:700;letter-spacing:.5px;min-width:220px;text-align:center;transition:.18s all}
        .btn:hover{background:rgba(34,211,238,.1);border-color:rgba(34,211,238,.5)}
        .btn:focus-visible{outline:none;box-shadow:0 0 0 3px rgba(34,211,238,.35)}
        .btn--primary{border-color:rgba(34,211,238,.6)}
        .btn--secondary{border-color:rgba(167,139,250,.6)}

        /* ===== FOOTER ===== */
        .footer{max-width:960px;margin:18px auto 28px;color:var(--muted);font-size:14px;text-align:center}

        @media (max-width:520px){
            .btn{min-width:180px;padding:12px 16px;font-size:15px}
        }
    </style>
</head>
<body>
<div class="page">
    <header class="header">
        <div class="header__inner">Tennis Scoreboard</div>
    </header>

    <main class="content">
        <section class="card">
            <nav class="menu">
                <a class="btn btn--primary" href="/new-match1">New match</a>
                <a class="btn btn--secondary" href="scoreboard.html">Scoreboard</a>
            </nav>
        </section>
    </main>

    <footer class="footer">by Syrym Sabyrzhan</footer>
</div>
</body>
</html>


