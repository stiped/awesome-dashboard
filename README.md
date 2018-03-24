# awesome-dashboard

Et awesome dashboard!

## Bygg
mvn clean install

## Konfigurasjon

### dashboard.properties

Denne fila må inneholde:

* jenkins.username=brukernavn
* jenkins.password=passord

## Start
java -jar filnavn.jar -Ddashboard.properties=/path/to/dashboard.properties

Starter applikasjon på http://localhost:8080

## API
### Jenkins
* /api/jenkins/{jobbnavn} 

### JIRA
* 

## Backlog
* Dokumentere API
* ~~Jenkins-integrasjon~~
* JIRA-integrasjon / widget
* GITLAB-integrasjon / widget
* SONAR-integrasjon / widget
* Jerre.no-integrasjon/kaffe-widget ? :D
* Logging
* Frontend
