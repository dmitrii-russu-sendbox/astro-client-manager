@param String error
@param com.example.customers.entity.Person person

@template.customers.layout.main(content = @`

@if (error != null) <p>${error}</p> @endif

<h2>${person.getName() != null ? "Редактировать" : "Создать"} клиента</h2>

<form action="/customers/save" method="post">

    @if (person.getId() != null)
        <input type="hidden" name="id" value="${person.getId()}">
    @endif

    <label>Имя:</label><br>
    <input type="text" name="name" value="${person.getName()}" required><br>

    <label>Дата рождения:</label><br>
    <input type="date" name="birthDate"
           value="${person.getBirthDate() != null ? person.getBirthDate().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE) : ""}"><br>

    <label>Время рождения:</label><br>
    <input type="time"
           name="birthTime"
           step="1"
           value="${person.getBirthTime() != null ? person.getBirthTime().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")) : ""}"><br>

    <label>Место рождения:</label><br>
    <input type="text" name="birthPlace" value="${person.getBirthPlace()}"><br>

    <label>Категория:</label><br>
    <input type="text" name="category" value="${person.getCategory()}"><br>

    <label>Доп. информация:</label><br>
    <textarea name="extraInfo">${person.getExtraInfo()}</textarea><br>

    <button type="submit">Сохранить</button>
</form>

    <hr>
    <form action="/searchForm" method="get">
        <button type="submit">Перейти в рабочий кабинет астролога</button>
    </form>

<hr>
<form action="/" method="get">
    <button type="submit">На главную страницу</button>
</form>

`,birthdays = null
)