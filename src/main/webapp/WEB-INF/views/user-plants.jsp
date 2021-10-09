<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>





<hr>
<a href="/">Strona glowna</a>
<hr><br/>
<a href=""

<c:forEach items="${userPlants}" var="plant">
    <a href="/deleteUserPlant/${plant.id}">Usun</a>
    <br/><br/>
    <b>NAZWA:</b> <u><b>${plant.name}</b></u>
    <br/>
<b>DATA NASADZENIA: </b>> <u><b>${plant.localDate}</b></u></>
    <br/>
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