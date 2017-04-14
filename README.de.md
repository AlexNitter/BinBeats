# BinBeats
Software zur Erzeugung binauraler Beats
Version 1 - 15.04.2017 


### Inhaltsverzeichnis

- [Sicherheitshinweis](#sicherheitshinweis)
- [Systemvoraussetzungen](#systemvoraussetzungen)
- [Installation](#installation)
- [Funktionsweise](#funktionsweise)
- [Benutzung](#benutzung)
- [Nutzungsszenarien](#nutzungsszenarien)
- [Known issues](#known-issues)
- [Mitarbeit am Projekt](#mitarbeit-am-projekt)
- [Inhalte von Drittanbietern](#inhalte-von-drittanbietern)

## Sicherheitshinweis
Personen mit Hirnerkrankungen wie Epilepsie, Schlafanfall-Patienten oder Personen mit Herz-Rythmus-Störungen sollten auf die binauralen Beats verzichten oder die Nutzung mit ihrem Arzt besprechen, da die Beats die Hirnströme beeinflussen und dementsprechend Auswirkungen auf Vorerkrankte haben können.

## Systemvoraussetzungen
Um die BinBeats-Software nutzen zu können ist ein Standard-PC mit Maus, Tastatur und Steroekopfhörern, sowie ein gängiges Betriebssystem erforderlich. Außerdem muss eine aktuelle Java-Runtime (JRE) installiert sein.

## Installation
Lade die ausführbare .jar-Datei [hier](https://github.com/AlexNitter/BinBeats/raw/master/build/BinBeats.jar) herunter. Es sind keine weiteren Installationsschritte nötig, die Datei kann direkt durch einen Doppelklick ausgeführt werden. Auf einem Linux-System ist die Anwendung ggf. mit dem Konsolenbefehl "java -jar BinBeats.jar" zu starten.

## Funktionsweise
Binaurale Beats sind ein auditives Phänomen, durch das bei der Wahrnehmung von leicht unterschiedlichen Frequenzen auf dem linken und auf dem rechten Ohr die Differenzfrequenz als pulsierender Ton durch das Hörzentrum erzeugt wird.
Hört man beispielsweise auf dem rechten Ohr einen Ton der Frequenz 200 Hz und auf dem linken Ohr 210 Hz, generiert der Wahrnehmungsapparat einen Ton der mittleren Frequenz von 205 Hz, der mit 10 Hz pulsiert.

Binauralen Beats wird eine Wechselwirkung mit Gehirnwellen zugeschrieben. Erzeugt man binaurale Beats in einer Frequenz, die mit der Gehirnwellenfrequenz eines bestimmten Zustands übereinstimmt, kann man diesen Zustand künstlich herbeiführen und sich verschiedene mögliche Effekte zunutze machen. Die Existenz dieser Effekte ist umstritten und wissenschaftlich nicht erwiesen.

Eine übersichtliche Tabelle, die zeigt, mit welcher Beatfrequenz welcher Zustand herbeigeführt werden kann, findet sich auf [Wikipedia](https://de.wikipedia.org/wiki/Elektroenzephalografie#Beeinflussung_der_Gehirnwellen).

## Benutzung

Mit der "Carrier Frequency" lässt sich die Trägerfrequenz einstellen. Diese Frequenz bestimmt die Tonlage, mit welcher die Sinustöne wahrgenommen werden. Die "Beat Frequency" bestimmt den Abstand zwischen den beiden Sinustönen. Dieser Abstand ist es, durch welchen der eigentliche binaurale Beat entsteht.

1. Stelle den zu erzeugenden binauralen Beat über die Felder und Schieberegler ein oder wähle ein Preset aus der Liste aus.
2. Spiele den aktuell eingestellten Beat durch einen Klick auf das "Play"-Symbol.
3. Speichere den aktuell eingestellten Beat durch einen Klick auf das "Save"-Symbol.

Erhalte mit einem Mouseover über entsprechende Elemente weitere Hinweise zur Funktion und Benutzung.

## Nutzungsszenarien

### Auswählen und Abspielen von Voreinstellungen
 
Mit einem Maus-Klick auf das schwarze Dreieck im Feld "Preset" öffnet sich das Dropdown-Menü, welches verschiedene Voreinstellungen enthält. Ein Klick auf eine Voreinstellung wählt diese aus. Nach der Auswahl kann der aktuell eingestellte Beat durch einen Klick auf das "Play"-Symbol abgespielt werden. Durch einen Klick auf das "Stop"-Symbol kann der Ton gestoppt werden.
 
### Einstellen und Abspielen von Träger- und Differenzfrequenz mit Schiebereglern

Die Schieberegler für Frequenzen und Lautstärke lassen sich per drag and drop verstellen. Bringe die Regler in eine von dir gewünschte Position. Den von dir eingestellen binauralen Beat, kannst du nun durch einen Klick auf das "Play-Symbol" abspielen. Durch einen Klick auf das "Stop"-Symbol kann der Ton gestoppt werden.

### Einstellen von Träger- und Differenzfrequenz per Eingabefeld

Gewünschte Frequenzen und die Lautstärke eines BinBeats lassen sich auch über die Eingabefelder eingeben. Klicke in das gewüschte editierbare Feld . Gebe die von dir gewünschten Frequenzen und die gewünschte Lautstärke ein und bestätige deine Eingaben jeweils mit der Entertaste. Den von dir eingestellen binauralen Beat, kannst du nun durch einen Klick auf das "Play-Symbol" abspielen. Durch einen Klick auf das "Stop"-Symbol kann der Ton gestoppt werden.

Die Carrier Frequency-Werte müssen zwischen 20 und 1500 liegen. 
Die Beat Frequency-Wert müssen zwischen 0.5 und 30 liegen.
Die Werte für die Lautstärke müssen zwischen 0 und 100 liegen. 

### Speichern der aktuellen Einstellungen von Träger- und Differenzfrequenz

Eigene Einstellungen können unter einem eigenen Namen gespeichert werden. Stelle zunächst den Beat so ein, wie er gespeichert werden soll. Mit einem Klick in das editierbare Feld "Preset" und der Eingabe eines selbst gewählten Namens erhält der Beat den Namen unter dem er gespeichert und wieder abgerufen wird. Durch einen Klick auf das "Speichern"-Symbol wird die Einstellung gespeichert.
 
Eine selbst erstellte Einstellung kann wieder gelöscht werden, indem die Voreinstellung im Dropdown-Menü des Preset-Feldes mit einem Klick ausgewählt wird. Durch einen Klick auf das "Löschen"-Symbol wird die Einstellung gelöscht. 

Der Name, unter dem die Einstellung eines binaulen Beats gespeichert wird, muss sich von den Namen der vorhandenen Voreinstellungen unterscheiden. 

Die vom System zur Verfügung gestellten Voreinstellungen (Falling Asleep, Trance,Deep Meditation, Creativity, Concentrated Learning, Problem Solving) können nicht gelöscht werden.

## Known issues
- Manchmal ist ein kurzes "Knacken" zu hören während ein Beat abgespielt wird
- Eine Änderung der Einstellungen tritt erst nach dem Stoppen und neu Starten ein
- Bei abnehmender Lautstärke verändert sich der Klang des Tons und wird "kratziger"
- Der Ton spielt noch etwa eine Sekunde weiter, nachdem der stop-Button geklickt wurde


## Mitarbeit am Projekt
Du bist Entwickler und hast Interesse an dem Thema? Dann bist du herzlich eingeladen, das Projekt zu forken und weiter daran zu arbeiten. Im Moment ist das Repository nicht in der aktiven Entwicklung, deshalb schicke uns bitte keine pull requests. https://github.com/AlexNitter/BinBeats 

## Inhalte von Drittanbietern
* Die Benutzeroberfläche benutzt den [MiG Java Layout Manager](http://miglayout.com/) unter der [3-Clause BSD Lizenz](https://opensource.org/licenses/BSD-3-Clause)
* Die verwendeten Icons wurden von [Madebyoliver](http://www.flaticon.com/authors/madebyoliver) für [Flaticon](http://www.flaticon.com/packs/essential-collection) designed
