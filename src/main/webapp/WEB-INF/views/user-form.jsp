<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<form:form method="post" action="/registerNewUser" modelAttribute="user">
    Email: <form:input path="email"/><br/><br/>
    Login: <form:input path="name"/><br/><br/>
    Haslo: <form:input type="password" path="password"/><br/><br/>
    <input type="submit" value="Add User">
    <br/><br/><br/>
</form:form>

</body>
</html>