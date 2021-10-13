<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<form:form method="post" action="/userPage" modelAttribute="newPlant">
    <form:input path="name"></form:input>

</form:form>


</body>

</html>