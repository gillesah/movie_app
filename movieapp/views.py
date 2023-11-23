import random
from django.shortcuts import render
from imdb import Cinemagoer
import imdb


# def home(request):
#     return render(request, 'home.html')

ia = Cinemagoer()


def get_movie_data(movie_id):
    ia = Cinemagoer()
    ia.get_movie_infoset()
    movie = ia.get_movie(movie_id)
    return movie


def get_random_movie():
    ia = imdb.Cinemagoer('http')
    top_movies = ia.get_top250_movies()
    random_movie = random.choice(top_movies)
    ia.update(random_movie, info=['main', 'title',
              'plot', 'full-size cover url', 'genres', 'rating', 'hero-media__slate'])
    imdb_id = random_movie.movieID  # Obtient l'ID IMDb du film

    # Construit l'URL de la page de la bande-annonce
    trailer_url = f"https://www.imdb.com/title/tt{imdb_id}/?ref_=ttvi_vi_imdb_1"

    movie_data = {
        'title': random_movie .get('title'),
        'synopsis': random_movie.get('plot', 'No synopsis available'),
        'cover': random_movie.get('full-size cover url'),
        'genres': random_movie.get('genres'),
        'rating': random_movie.get('rating'),
        'trailer_url': trailer_url,
        # Ajoutez d'autres attributs si nécessaire
    }
    return movie_data


def movie_view(request):
    movie = get_random_movie()
    synops = movie.get('plot')
    url = movie.get('full-size cover url')

    return render(request, 'home.html', {'movie': movie})


# i = random.randint(1, 250)
