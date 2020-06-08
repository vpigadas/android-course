# Introduction

The patterns i selected to compare were the MVVM pattern and the MVC.
For my project i chose MVVM design pattern because it focuses on the compartmentalization of the components within a project. And it is easier maintainable.



# MVVP design pattern

Its most valuable characteristics among others are the following:

__1__. The UI components are decoupled with the business logic

__2__. The business logic is decoupled with the database functions

__3__. It can be read easier due to its organized structure

__4__. Its easier maintainable and easier upgradable

__5__. It is strong for testing and modularity

__6__. And finally all its components are reusable


## Description

The __Model-View-ViewModel__ pattern has various benefits especially when it comes to reducing the lines of code and creating a maintainable application. Furthermore, its logical parts are the following:

+ ### Model

> This layer is actually responsible for all the data, business logic and the state of the application at hand.

+ ### View

> The __View__ layer includes accepts observable data coming from the __ViewModel__ and proccess them effectively

+ ### ViewModel

> This part is surrounds the __Model__ and prepares all the observable data that will be further used by the __View__ Layer. It can also trigger events that are needed when the __View__ needs to transfer data towards the __Model__.


***


# MVC design pattern


## Description

The __Model-View-Controller__ design pattern subdivides the logical parts of the application into 3 discrete layers.

+ ### Model

> The Model Layer incorporates all the data , the bussiness logic and the state of our application.

+ ### View

> The View Layer is responsible for the representation of the data.

+ ### Controller

> The Controller Layer binds all the above by transferring the information from the Model towards the View and from the View towards the Model.


***

## Implemantion of MVVP Desing pattern

For my project i chose MVVM design pattern because it focuses on the compartmentalization of the components within a project. And it is easier maintainable.

__1__. The _Model_ which contains 2 entities using Room Persistence , the Channel one and the ChannelItem. These instances are used in their databases respectively which then are instantiated using singletton pattern.The DAO is interface is used on the repository to handle the CRUD operations and other various queries.

__2__. The _View_ layer holds the Activities and their respective adapters.

__3__. The _ViewModel_ is responsible for hooking events interchangeably between the Model and the View.

***

## Comparison

The major distinctions between the two is that MVVP pattern is more flexible, more organized, modularized , produces less code and it is easy to test. But it can also produce more complexity as the application scales.
In the other hand MVC is more suitable when the application is actually very simple (ex. it has about 2 activities).


***

## References

[1] Jan 26 2017, "MVC vs. MVP vs. MVVM on Android", <https://academy.realm.io/posts/eric-maxwell-mvc-mvp-and-mvvm-on-android/>

[2] Jul 28, 2018, "Common Android Architectures (MVC vs MVP vs MVVM)", <https://medium.com/@mr.anmolsehgal/common-android-architectures-mvc-vs-mvp-vs-mvvm-afd8461e1fee>

[3] Oct 7, 2019, "MVC vs MVP vs MVVM architecture in Android" <https://blog.mindorks.com/mvc-mvp-mvvm-architecture-in-android>

[4] N/A, "MVC vs MVP vs MVVM for Android Application Development", <https://www.simform.com/mvc-mvp-mvvm-android-app-development/>

[5] Feb 16, 2020, "MVC vs. MVP vs. MVVM: Which Pattern to Choose for Android App Development?", <https://hackernoon.com/mvc-vs-mvp-vs-mvvm-which-pattern-to-choose-for-android-application-development-8u493ay1>

[6] Feb 8, 2020, "MVC vs MVP vs MVVM for Android", <https://blog.usejournal.com/mvc-vs-mvp-vs-mvvm-for-android-5e2c83b8d4f2>

[7] Apr 1, 2019, "MVVM and DataBinding: Android Design Patterns", <https://www.raywenderlich.com/636803-mvvm-and-databinding-android-design-patterns>
