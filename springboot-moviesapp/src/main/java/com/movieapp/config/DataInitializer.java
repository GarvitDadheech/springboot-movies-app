package com.movieapp.config;

import com.movieapp.model.Director;
import com.movieapp.model.Movie;
import com.movieapp.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final DirectorRepository directorRepository;

    @Override
    public void run(String... args) {
        // Check if database is already populated
        if (directorRepository.count() > 0) {
            return;
        }

        // Create directors
        Director christopherNolan = Director.builder()
                .firstName("Christopher")
                .lastName("Nolan")
                .dateOfBirth(LocalDate.of(1970, 7, 30))
                .nationality("British-American")
                .biography("Christopher Nolan is a British-American film director, producer, and screenwriter known for his innovative storytelling and visually distinctive films.")
                .build();

        Director stevenSpielberg = Director.builder()
                .firstName("Steven")
                .lastName("Spielberg")
                .dateOfBirth(LocalDate.of(1946, 12, 18))
                .nationality("American")
                .biography("Steven Spielberg is an American film director, producer, and screenwriter. He is considered one of the founding pioneers of the New Hollywood era.")
                .build();

        Director quentinTarantino = Director.builder()
                .firstName("Quentin")
                .lastName("Tarantino")
                .dateOfBirth(LocalDate.of(1963, 3, 27))
                .nationality("American")
                .biography("Quentin Tarantino is an American film director, screenwriter, producer, and actor known for his nonlinear storylines and stylized violence.")
                .build();

        Director martinScorsese = Director.builder()
                .firstName("Martin")
                .lastName("Scorsese")
                .dateOfBirth(LocalDate.of(1942, 11, 17))
                .nationality("American")
                .biography("Martin Scorsese is an American film director, producer, screenwriter, and actor. One of the major figures of the New Hollywood era.")
                .build();

        Director jamesCameron = Director.builder()
                .firstName("James")
                .lastName("Cameron")
                .dateOfBirth(LocalDate.of(1954, 8, 16))
                .nationality("Canadian")
                .biography("James Cameron is a Canadian filmmaker and environmentalist who has directed, written, and produced many successful films.")
                .build();

        Director ridleyScott = Director.builder()
                .firstName("Ridley")
                .lastName("Scott")
                .dateOfBirth(LocalDate.of(1937, 11, 30))
                .nationality("British")
                .biography("Ridley Scott is an English film director and producer known for his visually striking films.")
                .build();

        Director francisLawrence = Director.builder()
                .firstName("Francis")
                .lastName("Lawrence")
                .dateOfBirth(LocalDate.of(1971, 3, 26))
                .nationality("American")
                .biography("Francis Lawrence is an American filmmaker and producer known for directing films in the Hunger Games series.")
                .build();

        Director denisVilleneuve = Director.builder()
                .firstName("Denis")
                .lastName("Villeneuve")
                .dateOfBirth(LocalDate.of(1967, 10, 3))
                .nationality("Canadian")
                .biography("Denis Villeneuve is a Canadian film director and screenwriter known for his visually striking and often cerebral films.")
                .build();

        Director gretaGerwig = Director.builder()
                .firstName("Greta")
                .lastName("Gerwig")
                .dateOfBirth(LocalDate.of(1983, 8, 4))
                .nationality("American")
                .biography("Greta Gerwig is an American actress, screenwriter, and director known for her collaborations with Noah Baumbach and her solo directorial work.")
                .build();

        Director jamesWan = Director.builder()
                .firstName("James")
                .lastName("Wan")
                .dateOfBirth(LocalDate.of(1977, 2, 26))
                .nationality("Australian")
                .biography("James Wan is an Australian film producer, screenwriter and film director of Malaysian Chinese descent. He is widely known for directing the horror film Saw.")
                .build();

        // Create Nolan's movies
        Movie inception = Movie.builder()
                .title("Inception")
                .releaseDate(LocalDate.of(2010, 7, 16))
                .description("A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.")
                .genre("Sci-Fi")
                .durationMinutes(148)
                .rating(8.8)
                .director(christopherNolan)
                .build();

        Movie interstellar = Movie.builder()
                .title("Interstellar")
                .releaseDate(LocalDate.of(2014, 11, 7))
                .description("A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.")
                .genre("Sci-Fi")
                .durationMinutes(169)
                .rating(8.6)
                .director(christopherNolan)
                .build();

        // Create Spielberg's movies
        Movie jurassicPark = Movie.builder()
                .title("Jurassic Park")
                .releaseDate(LocalDate.of(1993, 6, 11))
                .description("A pragmatic paleontologist touring an almost complete theme park on an island in Central America is tasked with protecting a couple of kids after a power failure causes the park's cloned dinosaurs to run loose.")
                .genre("Adventure")
                .durationMinutes(127)
                .rating(8.1)
                .director(stevenSpielberg)
                .build();

        Movie savingPrivateRyan = Movie.builder()
                .title("Saving Private Ryan")
                .releaseDate(LocalDate.of(1998, 7, 24))
                .description("Following the Normandy Landings, a group of U.S. soldiers go behind enemy lines to retrieve a paratrooper whose brothers have been killed in action.")
                .genre("War")
                .durationMinutes(169)
                .rating(8.6)
                .director(stevenSpielberg)
                .build();

        // Create Tarantino's movies
        Movie pulpFiction = Movie.builder()
                .title("Pulp Fiction")
                .releaseDate(LocalDate.of(1994, 10, 14))
                .description("The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.")
                .genre("Crime")
                .durationMinutes(154)
                .rating(8.9)
                .director(quentinTarantino)
                .build();

        // Create Scorsese's movies
        Movie goodfellas = Movie.builder()
                .title("Goodfellas")
                .releaseDate(LocalDate.of(1990, 9, 21))
                .description("The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito.")
                .genre("Crime")
                .durationMinutes(146)
                .rating(8.7)
                .director(martinScorsese)
                .build();

        // Create James Cameron's movies
        Movie avatar = Movie.builder()
                .title("Avatar")
                .releaseDate(LocalDate.of(2009, 12, 18))
                .description("A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.")
                .genre("Action")
                .durationMinutes(162)
                .rating(7.8)
                .director(jamesCameron)
                .build();

        // Create Ridley Scott's movies
        Movie gladiator = Movie.builder()
                .title("Gladiator")
                .releaseDate(LocalDate.of(2000, 5, 5))
                .description("A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.")
                .genre("Action")
                .durationMinutes(155)
                .rating(8.5)
                .director(ridleyScott)
                .build();

        // Create Denis Villeneuve's movies
        Movie blade2049 = Movie.builder()
                .title("Blade Runner 2049")
                .releaseDate(LocalDate.of(2017, 10, 6))
                .description("Young Blade Runner K's discovery of a long-buried secret leads him to track down former Blade Runner Rick Deckard.")
                .genre("Sci-Fi")
                .durationMinutes(164)
                .rating(8.0)
                .director(denisVilleneuve)
                .build();

        // Create Greta Gerwig's movies
        Movie barbie = Movie.builder()
                .title("Barbie")
                .releaseDate(LocalDate.of(2023, 7, 21))
                .description("Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.")
                .genre("Comedy")
                .durationMinutes(114)
                .rating(7.0)
                .director(gretaGerwig)
                .build();

        // Set movies to directors
        christopherNolan.setMovies(Arrays.asList(inception, interstellar));
        stevenSpielberg.setMovies(Arrays.asList(jurassicPark, savingPrivateRyan));
        quentinTarantino.setMovies(List.of(pulpFiction));
        martinScorsese.setMovies(List.of(goodfellas));
        jamesCameron.setMovies(List.of(avatar));
        ridleyScott.setMovies(List.of(gladiator));
        denisVilleneuve.setMovies(List.of(blade2049));
        gretaGerwig.setMovies(List.of(barbie));

        // Save directors (which will cascade to movies)
        directorRepository.saveAll(Arrays.asList(
                christopherNolan, stevenSpielberg, quentinTarantino, martinScorsese, 
                jamesCameron, ridleyScott, francisLawrence, denisVilleneuve, gretaGerwig, jamesWan
        ));
    }
}