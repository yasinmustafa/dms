# README #

This README would normally document whatever steps are necessary to get your application up and running.

### TODO's ###
- Use Open NLP for data classification, hadoop/mahout probably too much for this
- primefaces filtering... partly done. look into later when everything is done

-- add integration with scanner hardware https://github.com/sjamesr/jfreesane
-- refactor NavigationBean

### Who do I talk to? ###

* Yasin Mustafa
* Other community or team contact



### CENTOS 7 12.12.16
Adding this to end of  /etc/httpd/conf/httpd.conf worked for httpd fronting
<Location /foo>
  ProxyPass ajp://localhost:8009/foo
  ProxyPassReverse ajp://localhost:8009/foo
</Location>