@param String error
@param String message
@param com.example.customers.entity.Person person

@template.customers.layout.main(content = @`

<h2>Рабочий кабинет всемирно-известного астролога и коуча Руссу Анастасии Павловны</h2>

<h2>Поиск клиента по имени</h2>

<form action="/search" method="get">
    <label for="name">Name:</label>
    <input id="name" type="text" name="name"/>
    <button type="submit">Найти</button>
</form>

@if (error != null) <p style="color: red;">${error}</p> @endif
@if (message != null)<p style="color: green;">${message}</p>@endif

<h2>Просмотреть всех клиентов</h2>

<hr>
<form action="/search" method="get">
    <button type="submit">Просмотреть всех клиентов</button>
</form>
<hr>

<h2>Найти клиента по месяцу рождения</h2>

<form action="/customers/searchByMonth" method="get">
    <label for="month">Выберите месяц рождения:</label>
    <select name="month" id="month" required>
        <option value="1">Январь</option>
        <option value="2">Февраль</option>
        <option value="3">Март</option>
        <option value="4">Апрель</option>
        <option value="5">Май</option>
        <option value="6">Июнь</option>
        <option value="7">Июль</option>
        <option value="8">Август</option>
        <option value="9">Сентябрь</option>
        <option value="10">Октябрь</option>
        <option value="11">Ноябрь</option>
        <option value="12">Декабрь</option>
    </select>
    <button type="submit">Найти</button>
</form>
<hr>



<h2>Внести нового клиента в базу данных</h2>

<hr>
<form action="/newOrEdit" method="get">
    <button type="submit">Внести нового клиента в базу данных!</button>
</form>
<hr>

    <h2>На главную страницу</h2>
    <form action="/" method="get">
        <button type="submit">Вернуться на главную страницу</button>
    </form>

`)