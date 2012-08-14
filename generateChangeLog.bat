@echo off

if "%OS%" == "Windows_NT" setlocal

echo liquibase generate changelog using liquibase.properties
echo .

set LIQUIBASE_CMD="d:\resurse\liquibase\liquibase-2.0.5\liquibase.bat"

set MYSQL_CONNECTOR_JAR="d:\java_apps\M2Repository\mysql\mysql-connector-java\5.1.6\mysql-connector-java-5.1.6"

set CMD_LINE_ARGS=%1
if ""%1""=="""" goto done
shift
:setup
if ""%1""=="""" goto done
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto setup
:done

rem echo CMD_LINE_ARGS -%CMD_LINE_ARGS%-
if not ""%CMD_LINE_ARGS%""=="""" goto command 
echo fara argumete, set default argument: status
set CMD_LINE_ARGS=%CMD_LINE_ARGS%status

:command
echo using arguments %CMD_LINE_ARGS%

%LIQUIBASE_CMD% --classpath=%MYSQL_CONNECTOR_JAR%.jar %CMD_LINE_ARGS%

rem generateChangeLog