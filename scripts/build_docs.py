#!/usr/bin/env python3
from __future__ import annotations

import html
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
DOCS = ROOT / "docs"

NAV = [
    ("Overview", "index.html"),
    ("Standard 0.1", "standard/index.html"),
    ("Philosophy", "standard/philosophy.html"),
    ("Information", "standard/information-presentation.html"),
    ("Principles", "standard/principles.html"),
    ("Compatibility", "standard/compatibility.html"),
    ("Versioning", "standard/versioning.html"),
    ("Foundations", "foundations/tokens.html"),
    ("Typography", "foundations/typography.html"),
    ("Layout", "foundations/layout.html"),
    ("Components", "components/index.html"),
    ("Component Gallery", "components/gallery.html"),
    ("Buttons", "components/buttons.html"),
    ("Forms", "components/forms.html"),
    ("Banking Row", "components/banking-row.html"),
    ("Accounts", "components/account.html"),
    ("SDK Model", "sdk/index.html"),
    ("Platforms", "platforms/index.html"),
    ("Motion", "motion/index.html"),
    ("Banking Patterns", "patterns/banking.html"),
    ("Identity Consent", "patterns/identity-consent.html"),
    ("Accessibility", "accessibility/index.html"),
    ("White Label", "white-label/index.html"),
    ("Figma", "figma/index.html"),
    ("KMP", "kmp/index.html"),
    ("Mobile Demo", "demos/mobile/prototype.html"),
]

STYLE = """
:root{--navy:#1A335E;--coral:#EA4E4E;--teal:#00A8AE;--paper:#F7FAF9;--ink:#071C2E;--muted:#60747C;--line:#D7E5E8;--soft:#EEF6F6;--code:#0B213F}
*{box-sizing:border-box}
body{margin:0;color:var(--ink);background:var(--paper);font-family:Inter,ui-sans-serif,system-ui,-apple-system,BlinkMacSystemFont,"Segoe UI",sans-serif}
a{color:inherit}.shell{min-height:100vh;display:grid;grid-template-columns:280px minmax(0,1fr)}
aside{position:sticky;top:0;height:100vh;padding:28px 22px;background:#fff;border-right:1px solid var(--line);overflow:auto}
.logo{width:210px;height:auto;display:block;margin-bottom:28px}
nav{display:grid;gap:6px}nav a{padding:10px 12px;color:var(--muted);border-radius:12px;font-size:14px;font-weight:820;text-decoration:none}
nav a:hover,nav a.active{color:var(--navy);background:var(--soft)}
.content{min-width:0;padding:56px clamp(24px,6vw,96px) 80px}
.kicker{display:inline-flex;min-height:34px;align-items:center;padding:0 13px;color:#fff;background:var(--coral);border-radius:999px;font-size:13px;font-weight:950}
article{max-width:1050px}.doc-card{padding:34px;background:#fff;border:1px solid var(--line);border-radius:28px}
h1{margin:18px 0 20px;color:var(--navy);font-size:clamp(42px,6vw,74px);line-height:.94;letter-spacing:0}
h2{margin:40px 0 14px;color:var(--navy);font-size:32px;line-height:1.05}h3{margin:28px 0 10px;color:var(--navy);font-size:22px}
p{color:var(--muted);font-size:16px;line-height:1.62;font-weight:650}strong,b{color:var(--navy)}
ul,ol{color:var(--muted);font-size:16px;line-height:1.58;font-weight:650;padding-left:24px}li{margin:6px 0}
pre{overflow:auto;padding:18px 20px;color:#EAF6F7;background:var(--code);border-radius:18px;border:1px solid rgba(255,255,255,.08)}
code{font-family:"SFMono-Regular",Consolas,monospace;font-size:.92em}p code,li code,td code{padding:2px 6px;color:var(--navy);background:var(--soft);border-radius:8px}
table{width:100%;margin:18px 0;border-collapse:separate;border-spacing:0;overflow:hidden;background:#fff;border:1px solid var(--line);border-radius:18px}
th,td{padding:14px 16px;text-align:left;border-top:1px solid var(--line);vertical-align:top}th{color:var(--navy);background:var(--soft);font-weight:950}td{color:var(--muted);font-weight:650}tr:first-child th,tr:first-child td{border-top:0}
.footer-links{display:flex;flex-wrap:wrap;gap:10px;margin-top:28px}.btn{min-height:42px;display:inline-grid;place-items:center;padding:0 16px;color:#fff;background:var(--navy);border-radius:999px;text-decoration:none;font-weight:950}.btn.secondary{color:var(--navy);background:var(--soft)}
@media(max-width:900px){.shell{grid-template-columns:1fr}aside{position:static;height:auto}.content{padding:32px 18px}.doc-card{padding:24px}h1{font-size:42px}}
"""


def rel(from_file: Path, target: str) -> str:
    return Path(target).as_posix() if from_file.parent == DOCS else Path(
        Path(*([".."] * len(from_file.relative_to(DOCS).parent.parts))) / target
    ).as_posix()


def inline_md(text: str, source: Path) -> str:
    text = html.escape(text)
    text = re.sub(r"`([^`]+)`", r"<code>\1</code>", text)

    def link(match: re.Match[str]) -> str:
        label = match.group(1)
        href = match.group(2)
        if href.endswith(".md"):
            href = href[:-3] + ".html"
        return f'<a href="{html.escape(href)}">{label}</a>'

    return re.sub(r"\[([^\]]+)\]\(([^)]+)\)", link, text)


def parse_table(lines: list[str], i: int, source: Path) -> tuple[str, int] | None:
    if i + 1 >= len(lines):
        return None
    if "|" not in lines[i] or not re.match(r"^\s*\|?\s*:?-{3,}:?\s*(\|\s*:?-{3,}:?\s*)+\|?\s*$", lines[i + 1]):
        return None
    rows = []
    while i < len(lines) and "|" in lines[i] and lines[i].strip():
        cells = [c.strip() for c in lines[i].strip().strip("|").split("|")]
        rows.append(cells)
        i += 1
    header = rows[0]
    body = rows[2:]
    out = ["<table><thead><tr>"]
    out.extend(f"<th>{inline_md(c, source)}</th>" for c in header)
    out.append("</tr></thead><tbody>")
    for row in body:
        out.append("<tr>")
        out.extend(f"<td>{inline_md(c, source)}</td>" for c in row)
        out.append("</tr>")
    out.append("</tbody></table>")
    return "".join(out), i


def markdown_to_html(markdown: str, source: Path) -> tuple[str, str]:
    lines = markdown.splitlines()
    out: list[str] = []
    title = source.stem
    i = 0
    in_code = False
    code_lang = ""
    code_lines: list[str] = []
    list_open = False
    ordered_open = False

    def close_lists() -> None:
        nonlocal list_open, ordered_open
        if list_open:
            out.append("</ul>")
            list_open = False
        if ordered_open:
            out.append("</ol>")
            ordered_open = False

    while i < len(lines):
        line = lines[i]
        stripped = line.strip()

        if stripped.startswith("```"):
            if in_code:
                out.append(f'<pre><code class="language-{html.escape(code_lang)}">{html.escape(chr(10).join(code_lines))}</code></pre>')
                in_code = False
                code_lang = ""
                code_lines = []
            else:
                close_lists()
                in_code = True
                code_lang = stripped[3:].strip()
            i += 1
            continue

        if in_code:
            code_lines.append(line)
            i += 1
            continue

        table = parse_table(lines, i, source)
        if table:
            close_lists()
            out.append(table[0])
            i = table[1]
            continue

        if not stripped:
            close_lists()
            i += 1
            continue

        if stripped.startswith("#"):
            close_lists()
            level = len(stripped) - len(stripped.lstrip("#"))
            content = stripped[level:].strip()
            if level == 1:
                title = content
            out.append(f"<h{min(level, 3)}>{inline_md(content, source)}</h{min(level, 3)}>")
        elif re.match(r"^[-*]\s+", stripped):
            if ordered_open:
                out.append("</ol>")
                ordered_open = False
            if not list_open:
                out.append("<ul>")
                list_open = True
            item_text = re.sub(r"^[-*]\s+", "", stripped)
            out.append(f"<li>{inline_md(item_text, source)}</li>")
        elif re.match(r"^\d+\.\s+", stripped):
            if list_open:
                out.append("</ul>")
                list_open = False
            if not ordered_open:
                out.append("<ol>")
                ordered_open = True
            item_text = re.sub(r"^\d+\.\s+", "", stripped)
            out.append(f"<li>{inline_md(item_text, source)}</li>")
        else:
            close_lists()
            out.append(f"<p>{inline_md(stripped, source)}</p>")
        i += 1

    close_lists()
    return title, "\n".join(out)


def render_page(source: Path) -> None:
    target = source.with_suffix(".html")
    title, body = markdown_to_html(source.read_text(), source)
    depth = len(target.relative_to(DOCS).parent.parts)
    prefix = "../" * depth
    active = target.relative_to(DOCS).as_posix()
    nav = "\n".join(
        f'<a class="{"active" if href == active else ""}" href="{prefix}{href}">{label}</a>'
        for label, href in NAV
    )
    html_doc = f"""<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>{html.escape(title)} | Neptune. Signal</title>
  <style>{STYLE}</style>
</head>
<body>
  <div class="shell">
    <aside>
      <a href="{prefix}index.html"><img class="logo" src="{prefix}assets/logo/neptune-logo-color.png" alt="Neptune."></a>
      <nav aria-label="Documentation">{nav}</nav>
    </aside>
    <main class="content">
      <article>
        <span class="kicker">Neptune. Signal Standard 0.1.1</span>
        <div class="doc-card">{body}</div>
        <div class="footer-links">
          <a class="btn" href="{prefix}demos/mobile/prototype.html">Open mobile demo</a>
          <a class="btn secondary" href="{prefix}components/index.html">Components</a>
          <a class="btn secondary" href="{prefix}sdk/index.html">SDK model</a>
        </div>
      </article>
    </main>
  </div>
</body>
</html>
"""
    target.write_text(html_doc)


def update_index_links() -> None:
    index = DOCS / "index.html"
    text = index.read_text()
    text = text.replace(".md", ".html")
    index.write_text(text)


def main() -> None:
    for source in sorted(DOCS.rglob("*.md")):
        render_page(source)
    update_index_links()


if __name__ == "__main__":
    main()
