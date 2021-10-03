<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>



<b>Witaj!</b> ${user.userEmail}<br/>
<a href="/userEdit/${user.id}">Edit User</a>
<a href="/userDelete/${user.id}">   Delete User</a><br/><br/>
<hr>

<c:forEach items="${userPlants}" var="userPlant">
<u><b>${userPlant.name}</b></u>   <a href="/addToCollection/"${userPlant.id}>Dodaj</a><br/><br/>
<b>DATA NASADZENIA:</b> ${userPlant.plantDate}<br/>
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





</body>

</html>