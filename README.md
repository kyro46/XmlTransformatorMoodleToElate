# elateXam - Moodle-XML to elateXam-XML Converter #

Transformator f�r aus Moodle exportierte �bungsfragen in das von ElateXam genutzte XML-Format (complexTaskDef.xsd).

Unterst�tzte Fragetypen aus Moodle:
* cloze
* multichoice
* truefalse
* essay
* shortanswer
* matching

Der volle Konfigurationsumfang von Moodlefragen wird nicht unterst�tzt:

* Kein Feedback f�r verschiedene Antworten
* Keine halbrichtigen Antworten
* Keine Falschantworten (da f�r Autokorrektur nur korrekte Antworten notwendig sind)

Achtung: Cloze-Typ im Elate-Moodle
Der Elate-Cloze-Typ unterst�tzt mehrere korrekte Antwortalternativen pro Frage. Elate unsterst�tzt dagegen KEINE "halbrichtigen"
oder sonstigen Falschantworten. Es darf kein Feedback f�r die einzelnen Antworten angegeben werden.
Jede Antwortalternative ist eine gleichwertige Option, die beim Durchlauf der automatischen
Korrektur als korrekt gewertet wird. Alle anderen Eingaben werden zur manuellen Nachkorrektur einem Tutor/Pr�fungsleiter vorgelegt. 

Schreibkonvention:
Eingeleitet wird eine L�cke mit geschweiften Klammern und entweder:
:SHORTANSWER:
:MULTICHOICE:
:NUMERICAL:
Danach folgt, wie in Moodle gewohnt ein = um eine korrekte Antwort einzuleiten. Antwortalternativen werden
mittels ~ voneinander getrennt. Danach kann wieder eine mit = eingeleitete korrekte Antwort folgen. 

Wir empfehlen die Nutzung von :MULTICHOICE: zur Erstellung der Elate-Cloze-Fragen, da sie
in der Moodlevorschau alle m�glichen korrekten Antwortalternativen anzeigt.

Beispiel:
<pre>
{:MULTICHOICE:=Richtige Antwort~=Richtige Antwort 2~= Richtige Antwort 3}
</pre>


# Nutzung: #
Moodle-XML-Dateipfad als Startparameter �bergeben







## TODO ##
 * TODO Metadatablock in ComplexTaskDef einf�gen
 * TODO Mehrere Schachtelungsebenen f�r Category �bernehmen, sobald complexTaskDef.xsd final
 * TODO Weitere Fragetypen einbauen
 * TODO Punkte = Anzahl der L�cken/Matchings - inkonsistent, da in Frageinstanzen nicht einheitlich viele L�cken/Matchings

