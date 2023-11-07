<!DOCTYPE html>
<html>
<head>
    <title>Ивенты Беларуси</title>
</head>
<body>
<h1>Ивенты Беларуси</h1>
<h3>Редактирование пользователя</h3>
<form action="/admin/user/edit" method = "post">
    <#list roles as role>
        <div>
            <label><input type="checkbox" name="selectedRoles" value="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}</label>
        </div>
    </#list>
    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" name="_csrf" value="${_csrf.token}" name ="_csrf">
    <button type="submit">Сохранить</button>
</form>
<a href="/registration">Зарегистрироваться</a>
</body>
</html>