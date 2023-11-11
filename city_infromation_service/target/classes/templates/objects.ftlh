<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Доступные объекты</h1><hr>
    <form method="get" action="/">
        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="searchObject">Тип объекта</label>
                <select id="searchObject" class="form-control">
                    <option value="">Тип не выбран</option>
                    <option value="На улице">На улице</option>
                    <option value="В здании">В здании</option>
                    <option value="Не придумал">Не придумал</option>
                </select>
            </div>
            <div class="form-group col-md-10">
                <label for="searchWord">Поиск по объектам</label>
                <input type="text" name="searchWord" value="Введите название" class="form-control"
                        <#--                <input type="text" name="searchWord" <#if searchWord??>value="${searchWord}"</#if> class="form-control"-->
                       id="searchWord">
            </div>
            <button style="width: 100%" type="submit" class="btn btn-dark">Поиск</button>
        </div>
    </form>
    <hr>
    <div class="card-columns">
        <#list objects as object>
            <a href="/object/${object.id}" class="object-link">
                <div class="card text-white bg-dark my-3 event">
                    <div class="card-top">
                        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
                           href="/my/events/request?objectId=${object.id}">
                            Запрос на ивент
                        </a>
                    </div>
                    <img src="/images/${object.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${object.name}</h5>
                        <p class="card-text">${object.type}</p>
                    </div>
                    <div class="card-footer">
                        ${object.numberOfSeats} мест
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>