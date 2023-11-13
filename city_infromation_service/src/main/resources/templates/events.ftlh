<#import "blocks/template.ftlh" as t>

<@t.template user>
    <h1 style="text-align: center">Доступные ивенты</h1><hr>
    <form method="get" action="/events">
        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="searchEvent">Тип ивента</label>
                <select id="searchEvent" class="form-control">
                    <option value="">Тип не выбран</option>
                    <option value="Концерт">Концерт</option>
                    <option value="Фестиваль">Фестиваль</option>
                    <option value="Выставка">Выставка</option>
                </select>
            </div>
            <div class="form-group col-md-10">
                <label for="searchWord">Поиск по ивентам</label>
                <input type="text" name="searchWord" value="Введите название" class="form-control" id="searchWord">
            </div>
            <button style="width: 100%" type="submit" class="btn btn-dark">Поиск</button>
        </div>
    </form>
    <hr>
    <div class="card-columns">
        <#list events as event>
            <a href="/event/${event.id}" class="event-link">
                <div class="card text-white bg-dark my-3 event">
                    <div class="card-top">
                    </div>
                    <img src="/images/${event.previewImageId}" class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${event.name}</h5>
                        <p class="card-text">${event.type}</p>
                    </div>
                    <div class="card-footer">
                        ${event.numberOfVisitors} посетителей
                    </div>
                </div>
            </a>
        </#list>
    </div>
</@t.template>