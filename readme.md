Trying to solve issue when *enumeration* parameters are passed to rsql parser as strings but hibernate does not translate them to enumerations.   

to get correct result use:
```http://localhost:8080/rest-sql?q=id==1```  
 
to get exception use:
```http://localhost:8080/rest-sql?q=simpleEnum==ENUM1```