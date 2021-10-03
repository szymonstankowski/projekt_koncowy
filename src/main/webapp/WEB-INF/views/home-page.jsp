<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<body>

<br/>
<h2><a href="/newUser">Rejestracja</a>  <a href="/login">Login</a> <br/><br/>
<a href="/addNewPlant">Dodaj nowa rosline</a><br/><br/>
</h2>


<c:forEach items="${plant}" var="userPlant">
    <u><b>${userPlant.name}</b></u>   <a href="/addToCollection/${userPlant.id}">Dodaj</a><br/><br/>
    <b>ID:</b> ${userPlant.id}
    <br/>
    <b>OPIS:</b> ${userPlant.description}
    <br/>
    <b>NAWADNIANIE CO:</b> ${userPlant.wateringInterval} dni
    <br/>
    <b>TYP:</b> ${userPlant.type}
    <br/>
    <b>OKRES WEGETACJI:</b> ${userPlant.vegetationPeriod}
    <br/>
    <b>TYP GLEBY:</b> ${userPlant.soilType}
    <br/><hr><br/>

</c:forEach>

<br/>
</body>
</html>