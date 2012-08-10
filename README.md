# elateXam - Moodle-XML to elateXam-XML Converter #

Transformator für aus Moodle exportierte Übungsfragen in das von ElateXam genutzte XML-Format (complexTaskDef.xsd).

Unterstützte Fragetypen aus Moodle:
* cloze
* multichoice
* truefalse
* essay
* shortanswer
* matching

Der volle Konfigurationsumfang von Moodlefragen wird nicht unterstützt.

Nutzung:
Moodle-XML-Dateipfad als Startparameter übergeben

## TODO ##
 * TODO Mehrere Schachtelungsebenen für Category übernehmen, sobald complexTaskDef.xsd final
 * TODO Weitere Fragetypen einbauen
 * TODO Innere Inputaufteiler-Schleife (Taskblock-Erstellung)rekursiv machen
 * TODO Punkte = Anzahl der Lücken/Matchings - inkonsistent, da in Frageinstanzen nicht einheitlich viele Lücken/Matchings

