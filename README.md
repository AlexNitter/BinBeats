# BinBeats
Software zur Erzeugung binauraler Beats

### Inhaltsverzeichnis

- [Sicherheitshinweis](#sicherheitshinweis)
- [Systemvoraussetzungen](#systemvoraussetzungen)
- [Installation](#installation)
- [Benutzung](#benutzung)
- [Funktionsweise](#funktionsweise)
- [Known issues](#known-issues)
- [Mitarbeit am Projekt](#mitarbeit-am-projekt)
- [Inhalte von Drittanbietern](#inhalte-von-drittanbietern)

## Sicherheitshinweis
Personen mit Hirnerkrankungen wie Epilepsie, Schlafanfall-Patienten oder Personen mit Herz-Rythmus-Störungen sollten auf die binauralen Beats verzichten oder die Nutzung mit ihrem Arzt besprechen, da die Beats die Hirnströme beeinflussen und dementsprechend Auswirkungen auf Vorerkrankte haben können.

## Systemvoraussetzungen
Um die BinBeats-Software nutzen zu können ist ein Standard-PC mit Maus, Tastatur und Steroekopfhörern, sowie ein gängiges Betriebssystem erforderlich.

## Installation
Lade die ausführbare .jar-Datei [hier](https://github.com/AlexNitter/BinBeats/blob/master/build/BinBeats.jar) herunter. Es sind keine weiteren Installationsschritte nötig, die Datei kann direkt ausgeführt werden.

## Benutzung

Mit der "Carrier Frequency" lässt sich die Trägerfrequenz einstellen. Diese Frequenz bestimmt die Tonlage, mit welcher die Sinustöne wahrgenommen werden. Die "Beat Frequency" bestimmt den Abstand zwischen den beiden Sinustönen. Dieser Abstand ist es, durch welchem der eigentliche binauralen Beat entsteht.

1. Stelle den zu erzeugenden binauralen Beat über die Felder und Schieberegler ein oder wähle ein Preset aus der Liste aus.
2. Spiele den aktuell eingestellten Beat durch einen Klick auf den "Play"-Button.
3. Speichere den aktuell eingestellten Beat durch einen Klick auf den "Save"-Button.

Erhalte mit einem Mouseover über entsprechende Elemente weitere Hinweise zur Funktion und Benutzung.

## Funktionsweise
Binaurale Beats sind ein auditives Phänomen, durch das bei der Wahrnehmung von leicht unterschiedlichen Frequenzen auf dem linken und auf dem rechten Ohr die Differenzfrequenz als pulsierender Ton durch das Hörzentrum erzeugt wird.
Hört man beispielsweise auf dem rechten Ohr einen Ton der Frequenz 200 Hz und auf dem linken Ohr 210 Hz, generiert der Wahrnehmungsapparat einen Ton der mittleren Frequenz von 205 Hz, der mit 10 Hz pulsiert.

Binauralen Beats wird eine Wechselwirkung mit Gehirnwellen zugeschrieben. Erzeugt man binaurale Beats in einer Frequenz, die mit der Gehirnwellenfrequenz eines bestimmten Zustands übereinstimmt, kann man diesen Zustand künstlich herbeiführen und sich verschiedene mögliche Effekte zunutze machen. Die Existenz dieser Effekte ist umstritten und wissenschaftlich nicht erwiesen.

Eine übersichtliche Tabelle, die zeigt, mit welcher Beatfrequenz welcher Zustand herbeigeführt werden kann, findet sich auf [Wikipedia](https://de.wikipedia.org/wiki/Elektroenzephalografie#Beeinflussung_der_Gehirnwellen).

## Known issues
- Der Ton spielt noch etwa eine Sekunde weiter, nachdem der stop-Button geklickt wurde
- Bei abnehmender Lautstärke verändert sich der Klang des Tons und wird "kratziger"
- ... to be continued

## Mitarbeit am Projekt
Du bist Entwickler und hast Interesse an dem Thema? Dann bist du herzlich eingeladen, das Projekt zu forken und weiter daran zu arbeiten. Im Moment ist das Repository nicht in der aktiven Entwicklung, deshalb schicke uns bitte keine pull requests.

## Inhalte von Drittanbietern
* Die Benutzeroberfläche benutzt den [MiG Java Layout Manager](http://miglayout.com/) unter der [3-Clause BSD Lizenz](https://opensource.org/licenses/BSD-3-Clause)
* Die verwendeten Icons wurden von [Madebyoliver](http://www.flaticon.com/authors/madebyoliver) für [Flaticon](http://www.flaticon.com/packs/essential-collection) designed
