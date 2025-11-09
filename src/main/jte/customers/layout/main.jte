@import java.util.List
@import com.example.customers.entity.Person

@param gg.jte.Content content
@param List<Person> birthdays


<a href="/searchForm" style="margin-right: 10px;">Перейти в рабочий кабинет астролога</a>

Лунный календарь (2025):
<a href="https://astrosfera.ru/moon-calender/lunnyj-kalendar-na-noyabr-2025-goda.html" target="_blank">ноябрь</a>

<a href="https://astrosfera.ru/moon-calender/lunnyj-kalendar-na-dekabr-2025-goda.html" target="_blank">декабрь</a>

<a href="https://astrosfera.ru/moon-calender/lunnyj-kalendar-na-yanvar-2026-goda.html" target="_blank">январь</a>

<a href="https://geocult.ru/solyarnyiy-goroskop-onlayn" target="_blank">Соляр</a>

${content}


@if (birthdays != null && !birthdays.isEmpty())
    <div style="margin-left: 20px;">
        <p><strong>День рождения у:</strong></p>
        <ul>
            @for (Person i : birthdays)
                <li>
                    @if(i.getBirthDate() != null)
                        ${i.getBirthDate().format(java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"))}
                    @else N/A @endif
                    — ${i.getName()}
                    <a href="/customers/search?name=${i.getName()}">Выбрать</a>&nbsp;
                    <a href="/customers/newOrEdit?name=${i.getName()}">Редактировать</a>
                    <a href="/customers/confirm-delete?name=${i.getName()}">Удалить</a>
                </li>
            @endfor
        </ul>
    </div>
@endif

<script>
    setInterval(() => fetch('/ping'), 3000);
</script>