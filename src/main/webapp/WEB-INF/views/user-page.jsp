<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>



<b>Witaj!</b> ${user.userEmail}<br/>
<a href="/userEdit/${user.id}">Edit User</a>
<a href="/userDelete/${user.id}">   Delete User</a><br/><br/>

<hr>
<a href="/">Strona glowna</a>
<hr><br/>

<c:forEach items="${plant}" var="plant">
    <a href="/addToCollection/${plant.id}">Dodaj</a><br/><br/>
    <u><b>${plant.name}</b></u>
    <b>ID:</b> ${plant.id}
    <br/>
    <b>OPIS:</b> ${plant.description}
    <br/>
    <b>NAWADNIANIE CO:</b> ${plant.wateringInterval} dni
    <br/>
    <b>TYP:</b> ${plant.type}
    <br/>
    <b>OKRES WEGETACJI:</b> ${plant.vegetationPeriod}
    <br/>
    <b>TYP GLEBY:</b> ${plant.soilType}
    <br/><hr><br/>

</c:forEach>





</body>

</html>