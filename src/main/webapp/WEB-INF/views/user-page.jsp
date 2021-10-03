<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>



<h2>Witaj: ${user.userEmail}<br/><br/></h2>
<h2>Twoje haslo: ${user.userPassword}<br/><br/></h2>

<a href="/userEdit/${user.id}">Edit User</a> <br/>
<a href="/userDelete/${user.id}">Delete User</a> <br/>





</body>

</html>