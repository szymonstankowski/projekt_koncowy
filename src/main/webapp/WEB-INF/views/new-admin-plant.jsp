<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<a href="/adminDashboard">Wroc!</a><br/><br/>
<form:form method="post" action="/newPlantByAdmin" modelAttribute="plant">
    NAZWA: <form:input path="name"></form:input><br/><br/>
    TYP:
    <form:select path="type">
            <form:option value="-" label="Wybierz typ"/>
            <form:option value="Owoc"/>
            <form:option value="Ziolo"/>
            <form:option value="Warzywo"/>
    </form:select><br/>
    OPIS:
    <form:textarea path="description"/><<br/><br/>
    TYP GLEBY:
    <form:input path="soilType"/><br/><br/>
    MIEJSCE SLONECZNE?
    <form:select path="sunnySpot">
        <form:option value="-" label="TAK/NIE"/>
            <form:option value="TAK"/>
            <form:option value="NIE"/>
    </form:select><br/><br/>
    OKRES WEGETACJI: <form:input path="vegetationPeriod"></form:input><br/><br/>
    CO ILE DNI PODLEWAC?:
    <form:select path="wateringInterval">
            <form:option value="-" label="Dni"/>
            <form:option value="1"/>
            <form:option value="2"/>
            <form:option value="3"/>
            <form:option value="4"/>
            <form:option value="5"/>
            <form:option value="6"/>
            <form:option value="7"/>
            <form:option value="8"/>
            <form:option value="9"/>
            <form:option value="10"/>
    </form:select><br/><br/>
    <input type="submit" value="Dodaj nowa rosline"><br/>
    <hr>
</form:form>


</body>

</html>