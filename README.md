# Exercices 


Cloner le repository ou télécharger son contenu en zip et extraire les données.




## Python 

`cd tests-comwatt/python/`

L'exercice a été réalisé sur un jupyter notebook. Vous pouvez donc l'ouvrir soit en local si vous avez installé Jupyter soit en important le fichier "*Yen_analysis.ipynb*" sur Google Collab. Il est aussi possible de consulter le fichier directement sur [github](https://github.com/albanmi/tests-comwatt/blob/main/python/Yen_analysis.ipynb)






## Scala 

Cet exercice ci présente deux solutions en Scala. 
L'une utilisant un tableau Scala. L'autre utilise Scala pour manipuler un ***Spark SQL DataFrame*** et lancer des calculs sur un cluster, dans un container Docker.


### Command Line

`cd tests-comwatt/scala/commandLine/speeding-radar/`

Vous trouverez les données dans "*/src/main/resources/*".

Cette solution utilise [sbt](https://www.scala-sbt.org/download.html).

Une fois **sbt** installé, tapez les commandes suivantes afin d'afficher la réponse au problème. (*sbt*, puis *run*)

```zsh
% sbt

[info] started sbt server
sbt:scala> run
```

### Zeppelin on Docker 

`cd tests-comwatt/scala/zeppelin/`

Vous trouverez les données dans "*/data/*".

Pour cette solution assurez-vous d'avoir installé docker et docker-compose.
De plus vous pouvez modifier la mémoire allouée au container en modifiant le fichier *docker-compose.yaml* afin d'accélérer les calculs.
La solution a été développée avec 6g mais ramenée à 2g au vu de la quantité de données à manipuler.

Saisissez `docker-compose up` (avec `sudo` si nécessaire).

Une fois le container créé, ouvrez n'importe quel navigateur et allez sur cette [URL](http://localhost:8080/).

Il ne vous reste plus qu'à importer et ouvrir le notebook "*/notebooks/Speeding_radar.zpln*".
