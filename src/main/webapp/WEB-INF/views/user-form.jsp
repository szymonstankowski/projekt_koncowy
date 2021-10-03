<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>



<form:form method="post" action="/newUser" modelAttribute="user">
    Email: <form:input path="userEmail"/><br/>

    Haslo: <form:input type="password" path="userPassword"/><br/>
    <input type="submit" value="Save user">
    <br/><br/><br/>
</form:form>


</body>

</html>