@param String message
@param com.example.customers.entity.Person person

<h2>Клиент:</h2>

<p>
    <strong>ID:</strong> ${person.getId()}<br>
    <strong>Name:</strong> ${person.getName()}<br>
    <strong>Birth Date:</strong> @if(person.getBirthDate() != null)${person.getBirthDate().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE)}@else N/A@endif<br>
    <strong>Birth Time:</strong> @if(person.getBirthTime() != null)${person.getBirthTime().format(java.time.format.DateTimeFormatter.ISO_LOCAL_TIME)}@else N/A@endif<br>
    <strong>Birth Place:</strong> ${person.getBirthPlace()}<br>
    <strong>Category:</strong> ${person.getCategory()}<br>
    <strong>Extra Info:</strong> ${person.getExtraInfo()}
</p>

<h2>Обновить</h2>

<hr>
<form action="/persons/newOrEdit" method="get">
    <input type="hidden" name="name" value="${person.getName()}">
    <button type="submit">Редактировать</button>
</form>
<hr>

<h2>Удалить!</h2>
<hr>

<form action="/persons/confirm-delete" method="get">
    <input type="hidden" name="name" value="${person.getName()}">
    <button type="submit">Удалить</button>
</form>


<hr>

<h2>Вернуться на главную страницу</h2>

<hr>
<form action="/" method="get">
    <button type="submit">Вернуться на главную страницу</button>
</form>
<hr>