<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<b><a href="/logout">Wyloguj!</a> </b>

<h2>Wybierz rosline do usuniecia</h2>
<form:form method="post" action="/adminDashboard" modelAttribute="plants">
    <br/>
    <b>NAME: </b> ${plant.name}<br/><br/><a href="/adminDashboard/${plant.id}"><b>USUN ROSLINE<b/></a><br/><br/>
    <b>DESCRIPTION: </b>${plant.description}<br/><br/>
    <b>TYP GLEBY: </b>${plant.soilType}<br/><br/>
    <b>OKRES WEGETACJI: </b>${plant.vegetationPeriod}<br/><br/>
    <b>MIEJSCE SLONECZNE?: </b>${plant.sunnySpot}<br/><br/>
    <b>PODLEWAC CO: </b>${plant.wateringInterval} DNI<br/><br/>
    <hr>
</form:form>
<hr>



</body>

</html>