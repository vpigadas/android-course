# MVC design pattern


## Description

The __Model-View-Controller__ approach separates the application into one of three MVC components, each one having its own responsibilities.

+ ### Model

> Contains application data and business logic (the rules of the system). For example, user accounts, products you sell, a set of photos, etc. The model component has no knowledge of the interface.

+ ### View

> Contains everything that is visible on the screen and offers interaction to the user.

+ ### Controller

> This is the "glue" between the view and the model, which also manages the application logic. The controller reacts to the user's input and presents the data requested by the user.


***


# MVP design pattern


## Description

The __Model-View-Presenter__ breaks the controller up so that the natural view/activity coupling can occur without tying it to the rest of the __controller__ responsibilities.

+ ### Model

> Same as __MVC__ pattern

+ ### View

> The Activity/Fragment is considered part of the _View_ and will contain a reference to the _Presenter_. A good practice is to have the Activity implement a view interface so that the presenter has an interface to code to so the only thing that the _View_ will do is to call a method from the _Presenter_ every time there is an interface action. This eliminates coupling.

+ ### Presenter

> Responsible to act as the middle man between _View_ and _Model_. It retrieves data from the _Model_ and returns it formatted to the _View_. But unlike the typical MVC, it also decides what happens when you interact with the _View_.


***

## Implemantion of MVP Desing pattern on application

I chose to use the MVP pattern. So the application is divided in 3 different layers.

__1__. The _Model_ layer has the application login. In my case the model is actual a `SQLite` Database. The _Presenter_ layer has a reference of that model so in case no internet connection detected, it interacts with the `SQLite` Database to fetch data, manipulate them and then expose them to a Activity/Fragment.

__2__. The _View_ layer which contains the UI of the application (Activities, Fragments) and essentially _triggers_ the _Presenter_ (has the presentation logic) on user interactions with UI (ex clicking a button).

__3__. The _Presenter_ layer which stands between the _View_ and the _Model_ (has the model logic) layers. In my case I have created two presenters 1. `Presenter.java` for the Activities and 2. `Presenter2.java` for the Fragments. The logic is that the Activity/Fragment has a reference of its presenter and implements an interface of the presenter, it uses implemented methods of the presenter, and the presenter uses its interface's methods in order to expose the data in the specific Activity/Fgrament. Also, the presenter has a reference of the _Model_ layer (ex Local Database).

