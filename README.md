# elateXam - Moodle-XML to elateXam-XML Converter #

Transformator f�r aus Moodle exportierte �bungsfragen in das von ElateXam genutzte XML-Format (complexTaskDef.xsd).

Unterst�tzte Fragetypen aus Moodle:
* cloze
* multichoice
* truefalse
* essay
* shortanswer
* matching

Der volle Konfigurationsumfang von Moodlefragen wird nicht unterst�tzt.

Nutzung:
Moodle-XML-Dateipfad als Startparameter �bergeben

## TODO ##
 * TODO Mehrere Schachtelungsebenen f�r Category �bernehmen, sobald complexTaskDef.xsd final
 * TODO Weitere Fragetypen einbauen
 * TODO Innere Inputaufteiler-Schleife (Taskblock-Erstellung)rekursiv machen
 * TODO Punkte = Anzahl der L�cken/Matchings - inkonsistent, da in Frageinstanzen nicht einheitlich viele L�cken/Matchings

