Пример решенного тестового задания

Задача:
Имеется корневая папка. В этой папке могут находиться текстовые файлы, а также другие папки.
В других папках также могут находиться текстовые файлы и папки (уровень вложенности может оказаться любым).
Найти все текстовые файлы, отсортировать их по имени и склеить содержимое в один текстовый файл.

В каждом файле может быть ни одной, одна или несколько директив формата:

*require ‘<путь к другому файлу от корневого каталога>’*

Директива означает, что текущий файл зависит от другого указанного файла.
Необходимо выявить все зависимости между файлами, построить сортированный список, для которого выполняется условие: 
если файл А, зависит от файла В, то файл А находится ниже файла В в списке.
Осуществить конкатенацию файлов в соответствии со списком.

Пример:
- Каталог “Folder 1”
    - Файл “File 1-1”. Содержимое:

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse id enim euismod erat elementum cursus. In hac habitasse platea dictumst. Etiam vitae tortor ipsum. Morbi massa augue, lacinia sed nisl id, congue eleifend lorem.

***require ‘Folder 2/File 2-1’***

Praesent feugiat egestas sem, id luctus lectus dignissim ac. Donec elementum rhoncus quam, vitae viverra massa euismod a. Morbi dictum sapien sed porta tristique. Donec varius convallis quam in fringilla.

- Каталог “Folder 2”
    - Файл “File 2-1”. Содержимое:

Phasellus eget tellus ac risus iaculis feugiat nec in eros. Aenean in luctus ante. In lacinia lectus tempus, rutrum ipsum quis, gravida nunc. Fusce tempor eleifend libero at pharetra. Nulla lacinia ante ac felis malesuada auctor. Vestibulum eget congue sapien, ac euismod elit. Fusce nisl ante, consequat et imperdiet vel, semper et neque.

- Файл “File 2-2”. Содержимое:

***require ‘Folder 1/File 1-1’***

***require ‘Folder 2/File 2-1’***

In pretium dictum lacinia. In rutrum, neque a dignissim maximus, dolor mi pretium ante, nec volutpat justo dolor non nulla. Vivamus nec suscipit nisl, ornare luctus erat. Aliquam eget est orci. Proin orci urna, elementum a nunc ac, fermentum varius eros. Mauris id massa elit.

<aside>
💡 Для указанной структуры каталогов и файлов должен быть построен список:
Folder 2/File 2-1
Folder 1/File 1-1
Folder 2/File 2-2
</aside>

* Файлы для обработки перемещены в локальную директорию проекта для удобства 
* Файл с выводом содержимого, согласно списку зависимостей между файлами, находится по адресу output/output.txt в директории проекта