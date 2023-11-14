import random
from django.shortcuts import render
from imdb import Cinemagoer


# def home(request):
#     return render(request, 'home.html')

ia = Cinemagoer()


def get_movie_data(movie_id):
    ia = Cinemagoer()
    ia.get_movie_infoset()
    movie = ia.get_movie(movie_id)
    return movie


def get_random_movie():
    ia = Cinemagoer()
    top = ia.get_top250_movies()
    # i = random.randint(1, 250)

    random_movie = top[0]
    ia.update(random_movie, info=['main', 'episodes'])
    return random_movie


def movie_view(request):
    movie = ia.get_movie('0133093')
    # movie = get_random_movie()
    for genre in movie['genres']:
        print(genre)
    return render(request, 'home.html', {'movie': movie})


movie = ia.get_movie('0133093')
print(dir(movie))
print(movie.infoset2keys)
top = ia.get_top250_movies()
# i = random.randint(1, 250)

print(top)
