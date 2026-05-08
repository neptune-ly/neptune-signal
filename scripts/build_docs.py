#!/usr/bin/env python3
from __future__ import annotations

import html
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
DOCS = ROOT / "docs"

STANDARD_VERSION = "0.2.0"

NAV = [
    ("Overview", "index.html"),
    ("Standard 0.2", "standard/index.html"),
    ("M3 Extension", "standard/m3-extension.html"),
    ("Philosophy", "standard/philosophy.html"),
    ("Information", "standard/information-presentation.html"),
    ("Principles", "standard/principles.html"),
    ("Compatibility", "standard/compatibility.html"),
    ("Versioning", "standard/versioning.html"),
    ("Tokens", "foundations/tokens.html"),
    ("Color Roles", "foundations/color-roles.html"),
    ("Typography", "foundations/typography.html"),
    ("Layout", "foundations/layout.html"),
    ("Surfaces", "foundations/surfaces.html"),
    ("Shape", "foundations/shape.html"),
    ("Iconography", "foundations/iconography.html"),
    ("Components", "components/index.html"),
    ("Component Gallery", "components/gallery.html"),
    ("Anatomy", "components/anatomy.html"),
    ("App Shell", "components/app-shell.html"),
    ("Navigation", "components/navigation.html"),
    ("Buttons", "components/buttons.html"),
    ("Forms", "components/forms.html"),
    ("Banking Row", "components/banking-row.html"),
    ("Accounts", "components/account.html"),
    ("Status Result", "components/status-result.html"),
    ("SDK Model", "sdk/index.html"),
    ("Platforms", "platforms/index.html"),
    ("KMP", "kmp/index.html"),
    ("Motion", "motion/index.html"),
    ("Banking Patterns", "patterns/banking.html"),
    ("App Structure", "patterns/app-structure.html"),
    ("Identity Consent", "patterns/identity-consent.html"),
    ("Accessibility", "accessibility/index.html"),
    ("White Label", "white-label/index.html"),
    ("Figma", "figma/index.html"),
    ("Mobile Demo", "demos/mobile/prototype.html"),
]

STYLE = """
:root{
  --npt-navy:#07315F;
  --npt-teal:#00A8AE;
  --npt-coral:#EB4E4D;
  --npt-beige:#F0CE9D;
  --npt-sky:#3BC1EE;
  --primary:#07315F;
  --on-primary:#FFFFFF;
  --primary-container:#E8F1FF;
  --on-primary-container:#061D38;
  --secondary:#006D72;
  --secondary-container:#D8F6F7;
  --tertiary:#EB4E4D;
  --surface:#FAFCFC;
  --surface-container-low:#F4F8F8;
  --surface-container:#EEF5F5;
  --surface-container-high:#E7F0F1;
  --surface-container-highest:#DDE8EA;
  --on-surface:#071C2E;
  --on-surface-variant:#60747C;
  --outline:#C8D8DD;
  --outline-variant:#E0EAED;
  --code:#09213D;
  --shadow:0 22px 72px rgba(7,49,95,.10);
}
*{box-sizing:border-box}
body{
  margin:0;
  color:var(--on-surface);
  background:
    radial-gradient(circle at 82% 8%, rgba(0,168,174,.12), transparent 0 24rem),
    linear-gradient(180deg,#fff 0%,var(--surface) 36%,#F1F6F7 100%);
  font-family:Inter,ui-sans-serif,system-ui,-apple-system,BlinkMacSystemFont,"Segoe UI",sans-serif;
}
a{color:inherit}
.shell{min-height:100vh;display:grid;grid-template-columns:304px minmax(0,1fr)}
aside{
  position:sticky;
  top:0;
  height:100vh;
  padding:24px 18px;
  background:rgba(255,255,255,.86);
  border-right:1px solid var(--outline-variant);
  backdrop-filter:blur(22px);
  overflow:auto;
}
.logo{width:214px;height:auto;display:block;margin:0 0 24px}
nav{display:grid;gap:4px}
nav a{
  min-height:40px;
  display:flex;
  align-items:center;
  padding:0 14px;
  color:var(--on-surface-variant);
  border-radius:20px;
  font-size:14px;
  font-weight:780;
  text-decoration:none;
}
nav a:hover{color:var(--primary);background:var(--surface-container-low)}
nav a.active{
  color:var(--primary);
  background:var(--primary-container);
  box-shadow:inset 4px 0 0 var(--npt-coral);
}
.content{min-width:0;padding:56px clamp(24px,6vw,104px) 88px}
article{max-width:1120px}
.kicker{
  display:inline-flex;
  min-height:36px;
  align-items:center;
  gap:8px;
  padding:0 14px;
  color:var(--on-primary);
  background:var(--npt-coral);
  border-radius:999px;
  font-size:13px;
  font-weight:900;
}
.doc-card{
  margin-top:18px;
  padding:clamp(24px,4vw,42px);
  background:rgba(255,255,255,.94);
  border:1px solid var(--outline-variant);
  border-radius:28px;
  box-shadow:var(--shadow);
}
h1{
  margin:0 0 20px;
  color:var(--primary);
  font-size:clamp(40px,6vw,72px);
  line-height:.96;
  letter-spacing:0;
}
h2{margin:42px 0 14px;color:var(--primary);font-size:32px;line-height:1.08}
h3{margin:30px 0 10px;color:var(--primary);font-size:22px}
p{color:var(--on-surface-variant);font-size:16px;line-height:1.66;font-weight:650}
strong,b{color:var(--primary)}
ul,ol{color:var(--on-surface-variant);font-size:16px;line-height:1.6;font-weight:650;padding-left:24px}
li{margin:7px 0}
pre{
  overflow:auto;
  padding:18px 20px;
  color:#EAF7F8;
  background:var(--code);
  border-radius:22px;
  border:1px solid rgba(255,255,255,.08);
}
code{font-family:"SFMono-Regular",Consolas,monospace;font-size:.92em}
p code,li code,td code{
  padding:2px 7px;
  color:var(--primary);
  background:var(--primary-container);
  border-radius:9px;
}
table{
  width:100%;
  margin:20px 0;
  border-collapse:separate;
  border-spacing:0;
  overflow:hidden;
  background:#fff;
  border:1px solid var(--outline-variant);
  border-radius:22px;
}
th,td{padding:15px 16px;text-align:left;border-top:1px solid var(--outline-variant);vertical-align:top}
th{color:var(--primary);background:var(--surface-container);font-weight:920}
td{color:var(--on-surface-variant);font-weight:650}
tr:first-child th,tr:first-child td{border-top:0}
.footer-links{display:flex;flex-wrap:wrap;gap:10px;margin-top:28px}
.btn{
  min-height:44px;
  display:inline-grid;
  place-items:center;
  padding:0 18px;
  color:#fff;
  background:var(--primary);
  border-radius:999px;
  text-decoration:none;
  font-weight:900;
}
.btn.secondary{color:var(--primary);background:var(--primary-container)}
@media(max-width:900px){
  .shell{grid-template-columns:1fr}
  aside{position:static;height:auto}
  .content{padding:30px 16px 60px}
  .doc-card{padding:22px}
  h1{font-size:40px}
}
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
        <span class="kicker">Neptune. Signal Standard {STANDARD_VERSION}</span>
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
