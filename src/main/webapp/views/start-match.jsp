
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Tennis Scoreboard — New Match</title>
    <style>
        :root{
            --bg:#0b0f17;--panel:#111827;--text:#e5e7eb;--muted:#9ca3af;--divider:#1f2937;--radius:12px;--gap:14px;--shadow:0 4px 16px rgba(0,0,0,.25);
            --accent:#22d3ee;--danger:#ef4444;
        }
        html,body{height:100%;margin:0;background:var(--bg);color:var(--text);font:16px/1.45 ui-sans-serif,system-ui,-apple-system,Segoe UI,Roboto,"Helvetica Neue",Arial}

        .page{min-height:100%;display:grid;grid-template-rows:auto 1fr}

        /* ===== HEADER (как на прошлой странице) ===== */
        .header{background:var(--panel);border-bottom:1px solid var(--divider)}
        .header__inner{max-width:960px;margin:0 auto;padding:16px;text-align:center;font-weight:700;font-size:20px}

        /* ===== CONTENT ===== */
        .content{display:grid;place-items:center;padding:32px}
        .card{width:min(520px,100%);background:var(--panel);border:1px solid var(--divider);border-radius:var(--radius);box-shadow:var(--shadow);padding:24px}

        .form{display:grid;gap:14px}
        .field{display:grid;gap:6px}
        .label{color:var(--muted);font-size:14px}
        .input{width:100%;padding:12px 14px;border-radius:10px;border:1px solid var(--divider);background:#0f1522;color:var(--text)}
        .input:focus{outline:none;border-color:rgba(34,211,238,.5);box-shadow:0 0 0 3px rgba(34,211,238,.25)}

        .actions{margin-top:4px;display:grid;gap:10px}
        .btn{appearance:none;border:1px solid rgba(34,211,238,.55);background:rgba(34,211,238,.08);color:var(--text);padding:12px 18px;border-radius:12px;font-weight:700;letter-spacing:.4px;text-transform:uppercase;cursor:pointer;text-align:center}
        .btn:hover{background:rgba(34,211,238,.14)}

        .error{min-height:20px;color:var(--danger);font-size:14px}

        .footer{max-width:960px;margin:18px auto 28px;color:var(--muted);font-size:14px;text-align:center}
    </style>
</head>
<body>
<div class="page">
    <header class="header">
        <div class="header__inner">Tennis Scoreboard</div>
    </header>

    <main class="content">
        <section class="card" aria-label="Новый матч">
            <form class="form" id="newMatchForm" novalidate>
                <div class="field">
                    <label class="label" for="p1">Имя игрока A</label>
                    <input class="input" id="p1" name="p1" type="text" autocomplete="off" placeholder="Напр. Novak" required />
                </div>
                <div class="field">
                    <label class="label" for="p2">Имя игрока B</label>
                    <input class="input" id="p2" name="p2" type="text" autocomplete="off" placeholder="Напр. Carlos" required />
                </div>
                <div class="actions">
                    <button type="submit" class="btn">Запустить игру</button>
                    <div id="error" class="error" aria-live="polite"></div>
                </div>
            </form>
        </section>
    </main>

    <footer class="footer">© Твоё приложение Tennis Scoreboard</footer>
</div>

<script>
    (function(){
        const form = document.getElementById('newMatchForm');
        const p1 = document.getElementById('p1');
        const p2 = document.getElementById('p2');
        const errorBox = document.getElementById('error');

        form.addEventListener('submit', function(e){
            e.preventDefault();
            errorBox.textContent = '';
            const a = (p1.value || '').trim();
            const b = (p2.value || '').trim();

            if (!a || !b) {
                errorBox.textContent = 'Пожалуйста, введите имена обоих игроков.';
                return;
            }
            if (a.toLowerCase() === b.toLowerCase()) {
                errorBox.textContent = 'Имена игроков не должны совпадать.';
                return;
            }
            // Если всё ок — можно перейти на табло и передать имена.
            const params = new URLSearchParams({ player1: a, player2: b });
            window.location.href = 'scoreboard.html?' + params.toString();
        });
    })();
</script>
</body>
</html>

