<#import "blocks/template.ftlh" as t>

<@t.template user>
    <h1 style="text-align: center">Доступные ивенты</h1>
    <hr>
    <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Добавить новый ивент
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
        <form action="/event/create" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="eventName">Название Ивента</label>
                <input type="text" required class="form-control"
                       id="eventName" name="name">
            </div>
            <div class="form-group">
                <label for="eventDescription">Описание</label>
                <textarea required class="form-control" id="eventDescription" name="description"></textarea>
            </div>
            <div class="form-group">
                <label for="eventStartDate">Дата начала</label>
                <textarea required class="form-control" id="eventStartDate" name="startDate"></textarea>
            </div>
            <div class="form-group">
                <label for="eventNumberOfVisitors">Количество посетителей</label>
                <div class="input-group mb-3">
                    <input type="number" required class="form-control" id="eventNumberOfVisitors" name="numberOfVisitors">
                </div>
            </div>
            <div class="form-group">
                <label for="eventType">Тип</label>
                <select id="eventType" required name="type" class="form-control">
                    <option value="">Тип не выбран</option>
                    <option value="Концерт">Концерт</option>
                    <option value="Фестиваль">Фестиваль</option>
                    <option value="Выставка">Выставка</option>
                </select>
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
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
        </form>
    </div>
    <div class="card-columns">
        <#list events as event>
            <a href="/event/${event.id}" class="event-link">
                <div class="card text-white bg-dark my-3 event">
                    <div class="card-top">
                        <form action="/event/delete/${event.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-danger m-3">
                                Удалить
                            </button>
                            <a href="/event/edit/${event.id}">
                                Редактировать ивент
                            </a>
                        </form>
                    </div>
                    <img src="/images/${event.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${event.name}</h5>
                        <p class="card-text">${event.type} </p>
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>