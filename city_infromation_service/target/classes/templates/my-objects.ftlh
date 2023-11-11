<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Мои объекты</h1>
    <hr>
    <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Добавить новый объект
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
        <form action="/object/create" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="objectName">Название Объекта</label>
                <input type="text" required class="form-control"
                       id="objectName" name="name">
            </div>
            <div class="form-group">
                <label for="address">Адрес</label>
                <textarea required class="form-control" id="address" name="address"></textarea>
            </div>
            <div class="form-group">
                <label for="openingDate">Дата открытия</label>
                <textarea required class="form-control" id="address" name="openingDate"></textarea>
            </div>
            <div class="form-group">
                <label for="workStatus">Время работы</label>
                <textarea required class="form-control" id="address" name="workStatus"></textarea>
            </div>
            <div class="form-group">
                <label for="popularity">Популярность</label>
                <div class="input-group mb-3">
                    <input type="number" required class="form-control" id="popularity" name="popularity">
                </div>
            </div>
            <div class="form-group">
                <label for="searchType">Тип</label>
                <select id="searchType" required name="type" class="form-control">
                    <option value="">Тип не выбран</option>

                    <option value="На улице">На улице</option>
                    <option value="В здании">В здании</option>
                    <option value="Не придумал">Не придумал</option>

                </select>
            </div>
            <div class="form-group">
                <label for="number_of_seats">Количество мест</label>
                <div class="input-group mb-3">
                    <input type="number" required class="form-control" id="number_of_seats" name="number_of_seats">
                    <div class="input-group-append">
                        <span class="input-group-text">мест</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input required type="file" name="file1" id="file1">
                    <label class="custom-file-label" for="file1">Первая фотография к товару</label>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file2" id="file2">
                    <label class="custom-file-label" for="file2">Вторая фотография к товару</label>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file3" id="file3">
                    <label class="custom-file-label" for="file3">Третья фотография к товару</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
        </form>
    </div>
    <div class="card-columns">
        <#list objects as object>
            <a href="/object/${object.id}" class="object-link">
                <div class="card text-white bg-dark my-3 event">
                    <div class="card-top">
                        <form action="/object/delete/${object.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-danger m-3">
                                Удалить
                            </button>
                                <a href="/object/edit/${object.id}">
                                    Редактировать объект
                                </a>
                            </p>
                        </form>
                    </div>
                    <img src="/images/${object.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${object.name}</h5>
                        <p class="card-text">${object.type} </p>
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>