<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<b>Witaj!</b> ${user.name}<br/>
<a href="/deleteUser">Usun uzytkownika!</a>
<hr>
<a href="/plantList">Lista roslin do dodania</a>
<hr>
<a href="/addNewPlant">Dodaj nowa rosline</a>
<hr>
<a href="/logout">Wyloguj</a>
<hr>

<c:forEach items="${userPlants}" var="plant">
    <!--<u><b></b></u>-->
    <br/>
    <b>NAME: </b> ${plant.plant.name}<br/><br/><a href="/deleteUserPlant/${plant.id}"><b>USUN ROSLINE<b/></a><br/><br/>
    <b>DATA NASADZENIA: </b>${plant.localDate}<br/><br/>
    <b>DESCRIPTION: </b>${plant.plant.description}<br/><br/>
    <b>TYP GLEBY: </b>${plant.plant.soilType}<br/><br/>
    <b>OKRES WEGETACJI: </b>${plant.plant.vegetationPeriod}<br/><br/>
    <b>MIEJSCE SLONECZNE?: </b>${plant.plant.sunnySpot}<br/><br/>
    <b>PPODLEWAC CO: </b>${plant.plant.wateringInterval} DNI<br/><br/>
    <hr>

</c:forEach>


</body>
</html>