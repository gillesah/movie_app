from imdb import Cinemagoer

ia = Cinemagoer('http')
top250 = ia.get_top250_movies()
print(top250)
# Parcourir la liste des 250 meilleurs films et imprimer leur titre
try:
    top250 = ia.get_top250_movies()
    print(f"Nombre de films récupérés : {len(top250)}")
    for movie in top250:
        print(movie['title'])
except Exception as e:
    print(f"Une erreur s'est produite : {e}")


print(top250[:5])

movie = ia.get_movie('0133093')  # ID de 'The Matrix', par exemple
print(movie.keys())
print(movie)
print(movie['plot'])
print(movie.get('plot'))
