set HUB_HOSTNAME=localhost
set GRID=selenium-server-standalone-3.4.0.jar
set HUB_URL=http://%HUB_HOSTNAME%:4444/grid/register
set BR_CHROME="browserName=chrome,maxInstances=3,platform=WINDOWS"
java -jar %GRID% -role node -hub %HUB_URL% -browser %BR_CHROME% -timeout 900000 -port 5555