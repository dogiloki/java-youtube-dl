@echo off
setlocal

set "search_query=OpenAI GPT-3"

for /f "delims=" %%i in ('yt-dlp.exe --dump-json --flat-playlist "ytsearch:%search_query%"') do (
    echo %%i >> resultados.json
)

endlocal
pause