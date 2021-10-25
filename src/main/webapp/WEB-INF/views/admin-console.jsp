<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
Witaj! Admin
<b><a href="/logout">Wyloguj!</a> </b>
<hr>
<h2>Wybierz uzytkownika do usuniecia</h2>
<hr>
<c:forEach items="${users}" var="user">
    <a href="/deleteUserByAdmin/${user.id}">Usun tego uzytkownika</a><br/>
    <b>${user.name}</b><br/>
    <b>${user.email}</b><br/>
    <b>${user.role}</b><br/>

    <hr>
</c:forEach>
<br/>
<hr>
<h2>Roslina do usuniecia</h2>
<hr>
<br/>
<c:forEach items="${plants}" var="plant">
    <a href="/markPlant/${plant.id}/{0}">Roslina do usuniecia </a><br/>
    <a href="/markPlant/${plant.id}/{1}">Aktywuj rosline </a><br/>
    <b>${plant.name}</b><br/>
    <b>${plant.id}</b><br/><br/>
    <b>${plant.active}</b><br/>
    <hr>
</c:forEach>

<br/>
<hr>
<a href="/newPlantByAdmin">Dodaj nowa rosline</a>

</body>

</html>