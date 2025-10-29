@param String error
@param String message
@param com.example.customers.entity.Person person

<h2>Main page</h2>

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

<h2>Внести нового клиента в базу данных</h2>

<hr>
<form action="/newOrEdit" method="get">
    <button type="submit">Внести нового клиента в базу данных!</button>
</form>
<hr>