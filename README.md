# awesome-dashboard

Et awesome dashboard

##Bygg
mvn clean install

##Konfigurasjon

### dashboard.properties

Denne fila må inneholde:

* jenkins.username=brukernavn
* jenkins.password=passord

##Start
java -jar filnavn.jar -Ddashboard.properties=/path/to/dashboard.properties

## API
### Jenkins
* /api/jenkins/{jobbnavn} 

### JIRA
* 

##Backlog
* Dokumentere API
* ~~Jenkins-integrasjon~~
* JIRA-integrasjon
* GITLAB
* Logging
