<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<body>

<br/>
<h2><a href="/newUser">Rejestracja</a><br/>
    <a href="/login">Login</a> <br/><br/>
    <a href="/addNewPlant">Dodaj nowa rosline</a><br/><br/>
</h2>


<c:forEach items="${plant}" var="plant">
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

<br/>
</body>
</html>