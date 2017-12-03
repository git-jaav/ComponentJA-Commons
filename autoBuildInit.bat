::AUTO BUILD COMPONENTES 
:: autoBuildInit.bat  OR run autoBuildInit.bat

REM ***INICIANDO BASH ****

cd JACommon-Utiles
call mvn clean install

cd .. && cd JACommon-UtilesJMail
call mvn clean install

cd .. && cd JACommon-UtilesJson
call mvn clean install

cd .. && cd JACommon-UtilesReport
call mvn clean install

cd .. && cd JASecurityCrypto-Utiles
call mvn clean install

REM ***FIN BASH***
REM ********************************************

pause