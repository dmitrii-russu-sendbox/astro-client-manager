@param com.example.customers.entity.Person person

@template.customers.layout.main(content = @`

<h2>Подтвердите удаление клиента</h2>

Удалить Клиента - «${person.getId()}. ${person.getName()}»?

<hr>
<h2>Удалить клиента</h2>
<form action="/customers/delete" method="post">
    <input type="hidden" name="name" value="${person.getName()}">
    <button type="submit">Удалить клиента</button>
</form>

<hr>
<h2>Вернуться к информации о клиенте</h2>
<form action="/search" method="get" style="display:inline;">
    <input type="hidden" name="name" value="${person.getName()}" />
    <button type="submit">Вернуться к информации о клиенте</button>
</form>
<hr>

    <form action="/searchForm" method="get">
        <button type="submit">Перейти в рабочий кабинет астролога</button>
    </form>
    <hr>

    <h2>На главную страницу</h2>
    <form action="/" method="get">
        <button type="submit">Вернуться на главную страницу</button>
    </form>

`)