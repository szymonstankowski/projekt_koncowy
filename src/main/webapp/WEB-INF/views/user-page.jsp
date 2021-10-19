<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<b>Witaj!</b> ${user.name}<br/>
<a href="/deleteUser">Usun uzytkownika!</a>
<hr>
<a href="/userPlantList">Lista roslin do dodania</a>
<hr>
<a href="/addNewPlant">Dodaj wlasna rosline</a>
<hr>
<a href="/logout">Wyloguj</a>
<hr>

<c:forEach items="${userPlants}" var="plant">
    <!--<u><b></b></u>-->
    <br/>
    <b>NAME:</b> ${plant.plant.name}<br/><br/>
    <b>DATA NASADZENIA:</b>${plant.localDate}  <a href="/resetPlantClock/${plant.id}/${plant.plant.id}">Podlano!</a><br/><br/>
    <b>DATA KOLEJNEGO PODLANIA:</b> ${plant.dataKolejnegoPodlania}<br/><br/>
    <b>OPIS:</b> ${plant.plant.description}<br/><br/>
    <b>TYP GLEBY:</b> ${plant.plant.soilType}<br/><br/>
    <b>OKRES WEGETACJI:</b> ${plant.plant.vegetationPeriod} DNI<br/><br/>
    <b>MIEJSCE SLONECZNE?:</b> ${plant.plant.sunnySpot}<br/><br/>
    <b>PODLEWAC CO:</b> ${plant.plant.wateringInterval} DNI<br/><br/><br/>

    <b><a href="/deleteUserPlant/${plant.id}">USUN ROSLINE</a></b><br/>
    <hr>

</c:forEach>


</body>
</html>