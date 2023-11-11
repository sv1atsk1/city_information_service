<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Редактирование ивента</h1>
    <hr>
    <form action="/event/update/${event.id}" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="eventName">Название Ивента</label>
            <input type="text" required class="form-control" id="eventName" name="eventName" value="${event.name}">
        </div>
        <div class="form-group">
            <label for="eventDescription">Описание</label>
            <textarea required class="form-control" id="eventDescription" name="eventDescription">${event.description}</textarea>
        </div>
        <div class="form-group">
            <label for="eventStartDate">Дата начала</label>
            <textarea required class="form-control" id="eventStartDate" name="eventStartDate">${event.startDate}</textarea>
        </div>
        <div class="form-group">
            <label for="eventNumberOfVisitors">Количество посетителей</label>
            <div class="input-group mb-3">
                <input type="number" required class="form-control" id="eventNumberOfVisitors" name="eventNumberOfVisitors" value="${event.numberOfVisitors}">
            </div>
        </div>
        <div class="form-group">
            <label for="eventType">Тип</label>
            <select id="eventType" required name="eventType" class="form-control">
                <option value="">Тип не выбран</option>
                <option value="Концерт" <#if event.type == "Концерт">selected</#if>>Концерт</option>
                <option value="Фестиваль" <#if event.type == "Фестиваль">selected</#if>>Фестиваль</option>
                <option value="Выставка" <#if event.type == "Выставка">selected</#if>>Выставка</option>
            </select>
        </div>
        <div class="form-group">
            <div class="custom-file">
                <input type="file" name="file1" id="file1">
                <label class="custom-file-label" for="file1">Фотография 1</label>
            </div>
        </div>
        <div class="form-group">
            <div class="custom-file">
                <input type="file" name="file2" id="file2">
                <label class="custom-file-label" for="file2">Фотография 2</label>
            </div>
        </div>
        <div class="form-group">
            <div class="custom-file">
                <input type="file" name="file3" id="file3">
                <label class="custom-file-label" for="file3">Фотография 3</label>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" style="width: 100%" class="btn btn-dark">Сохранить</button>
    </form>
</@t.template>