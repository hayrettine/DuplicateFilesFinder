# Detecting duplicate files in a given path

##Usage
```
* mvn clean package
* java -jar target/HW1-1.0.jar /hayrettine/Desktop/solr-6.4.1
```

Prints:

```
...
licenses/httpclient-NOTICE.txt licenses/httpcore-NOTICE.txt licenses/httpmime-NOTICE.txt  
...
server/solr-webapp/webapp/js/lib/highlight.js server/solr-webapp/webapp/libs/highlight.js  
... 
server/solr-webapp/webapp/WEB-INF/lib/httpmime-4.4.1.jar dist/solrj-lib/httpmime-4.4.1.jar 
...
docs/images/solr.svg server/solr-webapp/webapp/img/solr.svg  
...
```