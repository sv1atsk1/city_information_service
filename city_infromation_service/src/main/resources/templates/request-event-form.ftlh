<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Запрос на ивент</h1>
    <hr>
    <form action="/my/events/request" method="post" enctype="multipart/form-data">
        <input type="hidden" name="objectId" value="${object.id}">
        <h5>Поля для Event</h5>
        <!-- Fields for Event -->
        <div class="form-group">
            <label for="eventName">Название ивента</label>
            <input type="text" class="form-control" id="eventName" name="eventName" required>
        </div>
        <div class="form-group">
            <label for="eventType">Тип ивента</label>
            <input type="text" class="form-control" id="eventType" name="eventType" required>
        </div>
        <div class="form-group">
            <label for="eventDescription">Описание ивента</label>
            <textarea class="form-control" id="eventDescription" name="eventDescription" required></textarea>
        </div>
        <div class="form-group">
            <label for="eventStartDate">Дата начала ивента</label>
            <input type="text" class="form-control" id="eventStartDate" name="eventStartDate" required>
        </div>
        <div class="form-group">
            <label for="eventNumberOfVisitors">Количество посетителей</label>
            <input type="number" class="form-control" id="eventNumberOfVisitors" name="eventNumberOfVisitors" required>
        </div>
        <div class="form-group">
        <label for="file1">Фотография 1</label>
        <input type="file" class="form-control" id="file1" name="file1">
        </div>
        <div class="form-group">
            <label for="file2">Фотография 2</label>
            <input type="file" class="form-control" id="file2" name="file2">
        </div>
        <div class="form-group">
            <label for="file3">Фотография 3</label>
            <input type="file" class="form-control" id="file3" name="file3">
        </div>
        <input type="hidden" name="objectId" value="${object.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    <h5>Поля для Request</h5>
        <!-- Fields for Request -->
        <div class="form-group">
            <label for="requestObjectName">Название объекта</label>
            <input type="text" class="form-control" id="requestObjectName" name="requestObjectName" required>
        </div>
        <div class="form-group">
            <label for="requestStartDate">Дата начала запроса</label>
            <input type="text" class="form-control" id="requestStartDate" name="requestStartDate" required>
        </div>
        <div class="form-group">
            <label for="requestEventName">Название запроса</label>
            <input type="text" class="form-control" id="requestEventName" name="requestEventName" required>
        </div>
        <div class="form-group">
            <label for="requestEventType">Тип запроса</label>
            <input type="text" class="form-control" id="requestEventType" name="requestEventType" required>
        </div>
        <div class="form-group">
            <label for="requestUserFullName">Полное имя пользователя</label>
            <input type="text" class="form-control" id="requestUserFullName" name="requestUserFullName" required>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Отправить запрос</button>
    </form>
</@t.template>