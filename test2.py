from imdb import Cinemagoer
ia = Cinemagoer('http')
movie = ia.get_movie('0411008')
print(movie.getAsXML('keywords'))
