## :loudspeaker: How to run

Add your api key on the marvelapi.properties file

## :loudspeaker: This PR adds

- New flow to view superheroes and create squads
- 2 Activity screens 
  - SuperheroListActivity and SuperheroDetailActivity
- Persistent data for squad and heroes

## :bulb: Considerations and implementation

Implemented feature following clean architecture principles and multi-module approach

Modules
 - app
 - superheroes-domain
 - superheroes-data
   - superheroes-database
   - superheroes-api
 - superheroes-ui

Libraries used:

UI module: Jetpack compose, ViewModel, StateFlow, LiveData, Hilt
Data module: Room, Retrofit
Common: Flow, Coroutines,
Testing: mockito, assertj, mockwebserver

UI tests included written in BDD style class SquadFlowTest
e.g.

```
    @Test
    fun userSeesSuperheroesDetail() {
        given.apiReturnsSuperheroes()

        `when`.user.onSuperheroesListScreen()
            .navigatesToFirstHero()

        then.user.onSuperheroDetailScreen()
            .seesFirstHero()
    }
```



## :camera_flash: Screenshots

<delete section if not applicable>

| |  |
| ------ | ----- |
| ![image](https://user-images.githubusercontent.com/3733055/146434556-8b2f1d84-d9d6-415d-8bcf-91d510f398cb.png) | ![image](https://user-images.githubusercontent.com/3733055/146434796-d52c8591-35f0-46b0-8f77-e7b8b4063256.png) |
| ![image](https://user-images.githubusercontent.com/3733055/146434664-e5941bf9-913c-400b-aa7b-3bea37227045.png)| ![image](https://user-images.githubusercontent.com/3733055/146434727-0be63e72-b331-45ef-8165-883232f98234.png)  |