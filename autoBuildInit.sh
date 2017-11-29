###AUTO BUILD COMPONENTES ####
#### ./autoBuildInit.sh  OR sh autoBuildInit.sh OR bash autoBuildInit.sh

echo "********************************************"
echo "***INICIANDO BASH ****"

cd JACommon-Utiles
mvn clean install

cd .. && cd JACommon-UtilesJMail
mvn clean install

cd .. && cd JACommon-UtilesJson
mvn clean install

cd .. && cd JACommon-UtilesReport
mvn clean install

echo "***FIN BASH ****"
echo "********************************************"
