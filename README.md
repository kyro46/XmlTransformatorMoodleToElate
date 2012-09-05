# elateXam - Moodle-XML to elateXam-XML Converter #

Transformator für aus Moodle exportierte Übungsfragen in das von ElateXam genutzte XML-Format (complexTaskDef.xsd).

Unterstützte Fragetypen aus Moodle:
* cloze
* multichoice
* truefalse
* essay
* shortanswer
* matching

Der volle Konfigurationsumfang von Moodlefragen wird nicht unterstützt:

* Kein Feedback für verschiedene Antworten
* Keine halbrichtigen Antworten
* Keine Falschantworten (da für Autokorrektur nur korrekte Antworten notwendig sind)

Achtung: Cloze-Typ im Elate-Moodle
Der Elate-Cloze-Typ unterstützt mehrere korrekte Antwortalternativen pro Frage. Elate unsterstützt dagegen KEINE "halbrichtigen"
oder sonstigen Falschantworten. Es darf kein Feedback für die einzelnen Antworten angegeben werden.
Jede Antwortalternative ist eine gleichwertige Option, die beim Durchlauf der automatischen
Korrektur als korrekt gewertet wird. Alle anderen Eingaben werden zur manuellen Nachkorrektur einem Tutor/Prüfungsleiter vorgelegt. 

Schreibkonvention:
Eingeleitet wird eine Lücke mit geschweiften Klammern und entweder:
:SHORTANSWER:
:MULTICHOICE:
:NUMERICAL:
Danach folgt, wie in Moodle gewohnt ein = um eine korrekte Antwort einzuleiten. Antwortalternativen werden
mittels ~ voneinander getrennt. Danach kann wieder eine mit = eingeleitete korrekte Antwort folgen. 

Wir empfehlen die Nutzung von :MULTICHOICE: zur Erstellung der Elate-Cloze-Fragen, da sie
in der Moodlevorschau alle möglichen korrekten Antwortalternativen anzeigt.

Beispiel:
<pre>
{:MULTICHOICE:=Richtige Antwort~=Richtige Antwort 2~= Richtige Antwort 3}
</pre>


# Nutzung: #
Moodle-XML-Dateipfad als Startparameter übergeben







## TODO ##
 * TODO Metadatablock in ComplexTaskDef einfügen
 * TODO Mehrere Schachtelungsebenen für Category übernehmen, sobald complexTaskDef.xsd final
 * TODO Weitere Fragetypen einbauen
 * TODO Punkte = Anzahl der Lücken/Matchings - inkonsistent, da in Frageinstanzen nicht einheitlich viele Lücken/Matchings

