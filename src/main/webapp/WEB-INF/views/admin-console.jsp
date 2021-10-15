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
    <b>${user.name}</b><br/>
    <b>${user.email}</b><br/>
    <b>${user.role}</b><br/>
    <a href="/deleteUserByAdmin">Usun tego uzytkownika</a>
    <hr>
</c:forEach>
<br/>
<h2>Wybierz rosline do usuniecia</h2>
<br/>
<c:forEach items="${plants}" var="plant">
    <b>${plant.name}</b><br/>
    <b>${plant.id}</b><br/>
    <a href="/deletePlantByAdmin/${plant.id}">Usun ta rosline</a>
    <hr>

</c:forEach>

</body>

</html>