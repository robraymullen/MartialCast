REM ----------------------------------------------
set folder=D:\Movies
set vframes=10
set width=384
set height=216
    rem w = h*16/9
set filetypes=*.mkv
REM ----------------------------------------------
setlocal EnableDelayedExpansion

pushd "%folder%"
if not exist preview md preview
for /f "usebackq delims=" %%f in (`dir /b %filetypes%`) do (
    if not exist "preview\%%~nf.mp4" (  
        for /f %%i in ('ffprobe -v error -show_entries format^=duration "%%f" -of default^=noprint_wrappers^=1:nokey^=1') do set length=%%i
        set /a length=!length!+0
        set /a fps=!length!/%vframes%
        ffmpeg -threads 2 -i "%%f" -an -qscale:v 1 -vf "fps=1/!fps!, scale=iw*min(%width%/iw\,%height%/ih):ih*min(%width%/iw\,%height%/ih):flags=lanczos, pad=%width%:%height%:(%width%-iw*min(%width%/iw\,%height%/ih))/2:(%height%-ih*min(%width%/iw\,%height%/ih))/2, unsharp=5:5:0.5:5:5:0.5" -vframes %vframes% -f image2pipe -vcodec ppm - ^
        | ffmpeg -y -threads 2 -framerate 1 -i pipe:0 -c:v libx264 -profile:v baseline -level 3.0 -tune stillimage -r 30 -pix_fmt yuv420p "preview\%%~nf.mp4"
    )
cls
)