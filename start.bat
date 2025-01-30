@echo off
start cmd /k "docker-compose up --build"
timeout /t 30
start http://localhost:3000
