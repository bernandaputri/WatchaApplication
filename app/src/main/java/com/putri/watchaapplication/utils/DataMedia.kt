package com.putri.watchaapplication.utils

import com.putri.watchaapplication.data.entity.MediaEntity

object DataMedia {

    fun setDataMovie() : List<MediaEntity> {

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

        movies.add(MediaEntity(
            567189,
            "Tom Clancy's Without Remorse",
            "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            7.3
        ))

        movies.add(MediaEntity(
            804435,
            "Vanquish",
            "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
            6.4
        ))

        movies.add(MediaEntity(
            635302,
            "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
            "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
            8.4
        ))

        movies.add(MediaEntity(
            632357,
            "The Unholy",
            "A/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
            5.7
        ))

        movies.add(MediaEntity(
            726684,
                "Miraculous World: Shanghai – The Legend of Ladydragon",
                "/msI5a9TPnepx47JUb2vl88hb80Rjpg",
                7.8
        ))

        movies.add(MediaEntity(
            791373,
            "Zack Snyder's Justice League",
            "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
            8.5
        ))

        movies.add(MediaEntity(
            813258,
            "Monster Pets: A Hotel Transylvania Short",
            "/dkokENeY5Ka30BFgWAqk14mbnGs.jpg",
            7.6
        ))

        return movies
    }

    fun setDataShow() : List<MediaEntity> {

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

        shows.add(MediaEntity(
            71712,
            "The Good Doctor",
            "/53P8oHo9cfOsgb1cLxBi4pFY0ja.jpg",
            8.6
        ))

        shows.add(MediaEntity(
            1416,
            "Grey's Anatomy",
            "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
            8.2
        ))

        shows.add(MediaEntity(
            79008,
            "Luis Miguel: The Series",
            "/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg",
            8.0
        ))

        shows.add(MediaEntity(
            63174,
            "Lucifer",
            "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
            8.5
        ))

        shows.add(MediaEntity(
            69050,
            "Riverdale",
            "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
            8.6
        ))

        shows.add(MediaEntity(
            97180,
            "Selena: The Series",
            "/mYsWyfiIMxx4HDm0Wck7oJ9ckez.jpg",
             7.5
        ))

        shows.add(MediaEntity(
            1399,
            "Game of Thrones",
            "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
            8.4
        ))

        return shows
    }
}