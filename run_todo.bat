@echo off
cd C:\Users\LENOVO\Documents\Mehmet\ai\TodoApp
javac -cp "lib/*" Todo.java TodoList.java Main.java
java -cp ".;lib/*" Main
pause 