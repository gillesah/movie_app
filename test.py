import random
from django.shortcuts import render
from imdb import Cinemagoer
import imdb

ia = imdb.Cinemagoer('http')
top = ia.get_top250_movies()
# top2 = ia.nv_mv_250()

print(top)
for i in top:
    print(i)
# for f in Cinemagoer(get_top250_movies()):
#     print(f)
movie = ia.get_movie('0133093')  # Exemple avec l'ID de "The Matrix"
print(movie)
bottom = ia.get_bottom100_movies()
print(bottom)
for i in bottom:
    print(i.title)
# ia.analyze_title('"The Series" The Episode (2005)')
