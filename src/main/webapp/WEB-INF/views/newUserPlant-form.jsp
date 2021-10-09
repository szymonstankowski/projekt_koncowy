<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>



<form:form method="post" action="/addNewUserPlant" modelAttribute="newPlant">
    Name: <form:input path="name"/><br/><br/>
    Description: <form:input path="description"/><br/><br/>
    Soil type: <form:input path="soilType"/><br/><br/>
    Sunny spot?:
    <form:select path="sunnySpot"/>
        <form:option value="true">Sloneczne</form:option>
        <form:option value="false">Cieniste></form:option>
    <br/><br/>
    Plant type: <form:input path="type"/><br/><br/>
    Czas wegetacji: <form:input path="vegetationPeriod"/><br/><br/>
    Co ile dni podlewac?: <form:input path="wateringInterval"/><br/><br/>
    Czas nasadzenia: <form:input path="localDate"/><br/><br/>
    <input type="submit" value="Add User">
    <br/><br/><br/>
</form:form>


</body>

</html>