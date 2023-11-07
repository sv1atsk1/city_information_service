<!DOCTYPE html>
<html>
<head>
    <title>Ивенты Беларуси</title>
</head>
<body>
<h1>Ивенты Беларуси</h1><hr>
<h3>Подробная информация о владельце ${user.name}</h3>
<b>Номер телефона: </b> ${user.phoneNumber}<br>
<h4>Ивенты пользователя : ${user.name}</h4>
<#list events as event>
    <p><b>${event.description}</b><br> <b>${event.name} </b>| <a href = "/event/${event.id}">Подробнее...</a></p>
    <#else>
    <h3> Товаров нет</h3>
</#list>
<#if errorMessage??>
    <h2 style="color:red">${errorMessage}</h2>
</#if>
</body>
</html>