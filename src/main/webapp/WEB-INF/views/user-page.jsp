<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>



<b>Witaj!</b> ${user.name}<br/>
<a href="/userEdit/${user.id}">Edit User</a>
<a href="/deleteUser"> Delete User</a><br/><br/>

<hr>
<a href="/">Strona glowna</a>
<hr><br/>
<a href="/plantList">Lista roslin do dodania</a>

<c:forEach items="${userPlants}" var="plant">
    <!--<u><b></b></u>-->
    <br/>
    <b>ID:</b> ${plant.id}
    <br/>


</c:forEach>

</body>
</html>