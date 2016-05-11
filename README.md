# KSwe SoSe 2016 - Aufgabe 5 - Scrum

Diese Aufgabe stellt eine kleine Fingerübung dar, um das Konzept von Scrum - auf abstrakter Ebene - zu veranschaulichen.

## Beschreibung

Die verschiedenen Entitäten sind in einem einfachen
Java-Modell abgebildet. Ziel ist es, einen korrekten Ablauf der Scrum-Ereignisse
(wie im [Scrum Guide](http://www.scrumguides.org/download.html) beschrieben)
sicherzustellen.

## Vorgehensweise

Die Klasse `Main` steuert die Ausführung der Scrum-Ereignisse, insbesondere
deren Reihenfolge. Stelle zunächst (mit Hilfe des Scrum Guide) eine korrekte
Reihenfolge her.

In einem nächsten Schritt muss in der Klasse `Scrum` die Method `moveToNextEvent`
vervollständigt werden. Die Idee ist, hier zu überprüfen, ob das geplante Ereignis
den Scrum-Regeln entspricht. Nutze hier insbesondere die verfügbaren Exceptions.

Final müssen noch die Klassen angepasst werden, welche die Scrum-Ereignisse
repräsentieren. Diese befinden sich im Paket `de.hsbochum.fbg.kswe.scrum.events`
und implementieren alle das Interface `Event`. Implementiere für jede dieser
Klassen die `followingEventType`-Methode.

## Ergebnis

Die Ausführung der Main-Klasse sollte ohne Exception beendet werden und ein
Log aufweisen, das die Scrum-Ereignisse in ihrer chronologisch korrekten
Reihenfolge widergibt.
