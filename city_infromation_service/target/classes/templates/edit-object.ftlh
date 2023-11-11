<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Редактирование объекта</h1>
    <hr>
    <form action="/object/update/${object.id}" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="objectName">Название Объекта</label>
            <input type="text" required class="form-control" id="objectName" name="name" value="${object.name}">
        </div>
        <div class="form-group">
            <label for="address">Адрес</label>
            <textarea required class="form-control" id="address" name="address">${object.address}</textarea>
        </div>
        <div class="form-group">
            <label for="openingDate">Дата открытия</label>
            <textarea required class="form-control" id="openingDate" name="openingDate">${object.openingDate}</textarea>
        </div>
        <div class="form-group">
            <label for="workStatus">Время работы</label>
            <textarea required class="form-control" id="workStatus" name="workStatus">${object.workStatus}</textarea>
        </div>
        <div class="form-group">
            <label for="popularity">Популярность</label>
            <div class="input-group mb-3">
                <input type="number" required class="form-control" id="popularity" name="popularity" value="${object.popularity}">
            </div>
        </div>
        <div class="form-group">
            <label for="type">Тип</label>
            <select id="type" required name="type" class="form-control">
                <option value="">Тип не выбран</option>
                <option value="На улице" <#if object.type == "На улице">selected</#if>>На улице</option>
                <option value="В здании" <#if object.type == "В здании">selected</#if>>В здании</option>
                <option value="Не придумал" <#if object.type == "Не придумал">selected</#if>>Не придумал</option>
            </select>
        </div>
        <div class="form-group">
            <label for="number_of_seats">Количество мест</label>
            <div class="input-group mb-3">
                <input type="number" required class="form-control" id="number_of_seats" name="number_of_seats" value="${object.numberOfSeats}">
                <div class="input-group-append">
                    <span class="input-group-text">мест</span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="custom-file">
                <input type="file" name="file1" id="file1">
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
        <button type="submit" style="width: 100%" class="btn btn-dark">Сохранить</button>
    </form>
</@t.template>