@import java.util.List
@import com.example.customers.entity.Person

@param List<Person> persons
@param String searchTerm

@if (persons.isEmpty())<h2>Клиента по имени: "${searchTerm}" не найдено!</h2>
@else
@if ("(все клиенты)".equals(searchTerm))<h2>Все клиенты:</h2>
@else<h2>Результаты поиска клиента по имени: "${searchTerm}"</h2>@endif
@endif

@for (Person i : persons)
    <li>
        ID: ${i.getId()} — ${i.getName()}
        <a href="/persons/search?name=${i.getName()}">Выбрать</a>&nbsp;
        <a href="/persons/newOrEdit?name=${i.getName()}">Редактировать</a>
        <a href="/persons/confirm-delete?name=${i.getName()}">Удалить</a>

    </li>
@endfor

<hr>
<form action="/" method="get">
    <button type="submit">ToMainPage</button>
</form>
<hr>
