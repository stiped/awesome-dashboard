# awesome-dashboard

Et awesome dashboard!

## Bygg
mvn clean install

## Konfigurasjon

### dashboard.properties

Denne fila må inneholde:

#### Jenkins
* jenkins.baseUrl=baseUrl til Jenkins
* jenkins.username=brukernavn
* jenkins.password=passord

#### Jira
* jira.baseUrl=baseUrl til Jira
* jira.username=brukernavn
* jira.password=passord

## Start
java -jar awesome-backend-0.1.jar -Ddashboard.properties=/path/to/dashboard.properties

Starter applikasjon på http://localhost:8080

## API
### Jenkins
* /api/jenkins/{jobbnavn} 

### JIRA
* /api/jira/sprint/aktiv
* /api/jira/sprint/{sprintId}

## Backlog
* Dokumentere API
* ~~Jenkins-integrasjon~~
* ~~JIRA-integrasjon~~ / widget
* GITLAB-integrasjon / widget
* SONAR-integrasjon / widget
* Jerre.no-integrasjon / widget ? :D
* ~~Logging~~
* Frontend
