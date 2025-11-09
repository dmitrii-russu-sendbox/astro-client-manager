@import java.util.List
@import com.example.customers.entity.Person

@param List<Person> persons
@param String searchTerm
@param String searchType

@template.customers.layout.main(content = @`

@if ("month".equals(searchType))
    @if (persons.isEmpty())
        <h2>Найдено 0 клиентов за ${searchTerm}</h2>
    @else
        <h2>Клиенты за ${searchTerm}</h2>
    @endif

@elseif ("all".equals(searchType))
    @if (persons.isEmpty())
        <h2>В базе пока нет клиентов</h2>
    @else
        <h2>Все клиенты:</h2>
    @endif

@else
    @if (persons.isEmpty())
        <h2>Клиент по имени "${searchTerm}" не найден!</h2>
    @else
        <h2>Результаты поиска клиента по имени: "${searchTerm}"</h2>
    @endif
@endif

@for (Person i : persons)
    <li>
        ID: ${i.getId()} — ${i.getName()}
        <a href="/customers/search?name=${i.getName()}">Выбрать</a>&nbsp;
        <a href="/customers/newOrEdit?name=${i.getName()}">Редактировать</a>
        <a href="/customers/confirm-delete?name=${i.getName()}">Удалить</a>

    </li>
@endfor

<hr>
<form action="/searchForm" method="get">
    <button type="submit">Перейти в рабочий кабинет астролога</button>
</form>
<hr>

    <hr>
    <form action="/" method="get">
        <button type="submit">Вернуться на главную страницу</button>
    </form>
    <hr>

`,
birthdays = null
)
