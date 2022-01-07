package com.tiago.superherosquadmaker.responses

object ApiResponse {

    val superheroesResponse = """
        {
            "code": 200,
            "status": "Ok",
            "copyright": "© 2021 MARVEL",
            "attributionText": "Data provided by Marvel. © 2021 MARVEL",
            "attributionHTML": "<a href=\"http://marvel.com\">Data provided by Marvel. © 2021 MARVEL</a>",
            "etag": "c715c29a056f3d738798e6f54cb76001e7cca0ba",
            "data": {
                "offset": 0,
                "limit": 20,
                "total": 1559,
                "count": 20,
                "results": [
                    {
                        "id": 1011334,
                        "name": "3-D Man",
                        "description": "",
                        "modified": "2014-04-29T14:18:17-0400",
                        "thumbnail": {
                            "path": "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                            "extension": "jpg"
                        },
                        "resourceURI": "http://gateway.marvel.com/v1/public/characters/1011334"
                    },
                    {
                        "id": 1017100,
                        "name": "A-Bomb (HAS)",
                        "description": "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! ",
                        "modified": "2013-09-18T15:54:04-0400",
                        "thumbnail": {
                            "path": "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16",
                            "extension": "jpg"
                        },
                        "resourceURI": "http://gateway.marvel.com/v1/public/characters/1017100"
                    },
                    {
                        "id": 1009144,
                        "name": "A.I.M.",
                        "description": "AIM is a terrorist organization bent on destroying the world.",
                        "modified": "2013-10-17T14:41:30-0400",
                        "thumbnail": {
                            "path": "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec",
                            "extension": "jpg"
                        },
                        "resourceURI": "http://gateway.marvel.com/v1/public/characters/1009144"
                    },
                    {
                        "id": 1010699,
                        "name": "Aaron Stack",
                        "description": "",
                        "modified": "1969-12-31T19:00:00-0500",
                        "thumbnail": {
                            "path": "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available",
                            "extension": "jpg"
                        },
                        "resourceURI": "http://gateway.marvel.com/v1/public/characters/1010699"
                    },
                    {
                        "id": 1009146,
                        "name": "Abomination (Emil Blonsky)",
                        "description": "Formerly known as Emil Blonsky, a spy of Soviet Yugoslavian origin working for the KGB, the Abomination gained his powers after receiving a dose of gamma radiation similar to that which transformed Bruce Banner into the incredible Hulk.",
                        "modified": "2012-03-20T12:32:12-0400",
                        "thumbnail": {
                            "path": "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04",
                            "extension": "jpg"
                        },
                        "resourceURI": "http://gateway.marvel.com/v1/public/characters/1009146"
                    }
                ]
            }
        }
    """.trimIndent()

}