<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<body>

<br/>
<h2>
    <a href="/addNewPlant">Dodaj wlasna rosline</a><br/><br/>
</h2>
<hr>
<a href="/dashboard">Wroc</a><br/><br/>
<hr>


<c:forEach items="${plants}" var="plant">
    <a href="/addPlant/${plant.id}">Dodaj</a>
    <br/>
    <u><b>${plant.name}</b></u>
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