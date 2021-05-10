package com.putri.watchaapplication.utils

import com.putri.watchaapplication.data.entity.DetailMediaEntity
import com.putri.watchaapplication.data.entity.MediaEntity

object DataMedia {
    fun setDummyMovie() : ArrayList<MediaEntity> {

        val movies = ArrayList<MediaEntity>()

        movies.add(MediaEntity(
                460465,
                "Mortal Kombat",
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                7.7
        ))

        movies.add(MediaEntity(
                399566,
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                8.1
        ))

        movies.add(MediaEntity(
                615457,
                "Nobody",
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                8.5
        ))

        return movies
    }

    fun setDetailDummyMovie(mediaId: Int) : ArrayList<DetailMediaEntity> {
        val detailMovie = ArrayList<DetailMediaEntity>()

        detailMovie.add(DetailMediaEntity(
                mediaId,
                "Mortal Kombat",
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2021-04-07",
                "Action\nFantasy\nAdventure\nScience Fiction"
        ))

        detailMovie.add(DetailMediaEntity(
                mediaId,
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "2021-03-24",
                "Science Fiction\nAction\nDrama"
        ))

        detailMovie.add(DetailMediaEntity(
                mediaId,
                "Nobody",
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "2021-03-26",
                "Action\nThriller\nCrime"
        ))

        return detailMovie
    }

    fun setDummyShow() : ArrayList<MediaEntity> {

        val shows = ArrayList<MediaEntity>()

        shows.add(MediaEntity(
                95557,
                "Invincible",
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                8.9
        ))

        shows.add(MediaEntity(
                88396,
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                7.9
        ))

        shows.add(MediaEntity(
                60735,
                "The Flash",
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7
        ))

        return shows
    }

    fun setDetailDummyShow(mediaId: Int) : ArrayList<DetailMediaEntity> {
        val detailShow= ArrayList<DetailMediaEntity>()

        detailShow.add(DetailMediaEntity(
                mediaId,
                "Invincible",
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "2021-03-26",
                "Animation\nAction & Adventure\nDrama\nSci-Fi & Fantasy"
        ))

        detailShow.add(DetailMediaEntity(
                mediaId,
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2021-04-23",
                "Sci-Fi & Fantasy\nAction& Adventure\nDrama\n War & Politics"
        ))

        detailShow.add(DetailMediaEntity(
                mediaId,
                "The Flash",
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014-10-07",
                "Drama\nSci-Fi & Fantasy"
        ))

        return detailShow
    }
}