@echo off
cd /d "%~dp0"
echo Compiling Java files...
javac -d out src\Main.java
echo.
start "SchoolExam" cmd /k "java -cp out Main"
