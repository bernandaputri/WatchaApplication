package com.putri.watchaapplication.utils

import com.putri.watchaapplication.data.local.entity.MovieEntity
import com.putri.watchaapplication.data.local.entity.ShowEntity
import com.putri.watchaapplication.data.remote.response.*

object DataMedia {
    fun setDummyMovie() : ArrayList<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add(MovieEntity(
                567189,
                "Cruella",
                "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
                8.8,
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021-05-26"
        ))

        movies.add(MovieEntity(
                399566,
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                8.1,
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "2021-03-24"
        ))

        movies.add(MovieEntity(
                423108,
                "The Conjuring: The Devil Made Me Do It",
                "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
                8.4,
                "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense.",
                "2021-05-25"
        ))

        return movies
    }

    fun setDetailDummyMovie() : MovieEntity {

        return  MovieEntity(
                567189,
                "Cruella",
                "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
                8.8,
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021-05-26"
        )

    }

    fun setDummyShow() : ArrayList<ShowEntity> {

        val shows = ArrayList<ShowEntity>()

        shows.add(ShowEntity(
                1399,
                "Game of Thrones",
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                8.4,
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "2015-08-23"
        ))

        shows.add(ShowEntity(
                1402,
                "The Walking Dead",
                "/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                8.1,
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "2010-10-31"
        ))

        shows.add(ShowEntity(
                1416,
                "Grey's Anatomy",
                "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                8.2,
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "2005-03-27"
        ))

        return shows
    }

    fun setDetailDummyShow() : ShowEntity {

        return ShowEntity(
                1399,
                "Game of Thrones",
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                8.4,
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "2015-08-23"
        )

    }

    fun setRemoteDummyMovie() : ArrayList<MovieResultsItem> {

        val remoteMovies = ArrayList<MovieResultsItem>()

        remoteMovies.add(MovieResultsItem(
                567189,
                "Cruella",
                "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
                8.8,
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021-05-26"
        ))

        remoteMovies.add(MovieResultsItem(
                399566,
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                8.1,
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "2021-03-24"
        ))

        remoteMovies.add(MovieResultsItem(
                423108,
                "The Conjuring: The Devil Made Me Do It",
                "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
                8.4,
                "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense.",
                "2021-05-25"
        ))

        return remoteMovies
    }

    fun setDetailRemoteDummyMovie(mediaId: Int) : DetailMovieResponse {

        return DetailMovieResponse(
                mediaId,
                "Cruella",
                "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021-05-26",
                8.8
        )

    }

    fun setRemoteDummyShow() : ArrayList<TvShowResultsItem> {

        val remoteShows = ArrayList<TvShowResultsItem>()

        remoteShows.add(TvShowResultsItem(
                1399,
                "Game of Thrones",
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                8.4,
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "2015-08-23"
        ))

        remoteShows.add(TvShowResultsItem(
                1402,
                "The Walking Dead",
                "/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                8.1,
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "2010-10-31"
        ))

        remoteShows.add(TvShowResultsItem(
                1416,
                "Grey's Anatomy",
                "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                8.2,
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "2005-03-27"
        ))

        return remoteShows
    }

    fun setDetailRemoteDummyShow(mediaId: Int) : DetailTvShowResponse {

        return DetailTvShowResponse(
                mediaId,
                "Game of Thrones",
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "2015-08-23",
                8.4
        )

    }
}