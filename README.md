# Design Patterns

### _MVC - Model View Controller_

_MVC_'s structure depends on three things. Model, View and Controller. 

- The _Model_ is actually the data we want to display at the UI, the rules that we use to manipulate those data and it also describes the business logic behind. 
- The _View_ is actually the UI presenting the data we want. The _Model_ and the _View_ are connected with each other, with the **Observer** pattern.
- The _Controller_ is the component that processes the data from the _Model_ and send the results to _View_. It actually connects the above components.

### _MVVM - Model View View-Model_

_MVVM_ is a design pattern similar to _MVC_.
This design pattern has 3 main components too, the Model, the View and the View-Model.

The _Model_ and the _View_ here have actually the same functionality with the _MVC_'s. 

- The _View-Model_ connects the _View_ and the _Model_ in the way that, the _View_ can have an instance of a _View-Model_ and then have access to the data in the _Model_. The amazing thing is that _View-Model_ does not know anything about the _View_ and from the other side, _View-Model_ has a one to many relationship with the _View_.
That means, that many and different _Views_ can have an instance of a _View-Model_. The main reason of using _MVVM_ was that is recommended for applications with bi-directional data binding, like in our **TV-Guide** we need to bind the data between the DB and the API and of'course the UI.
The idea behind this design pattern in our application is to create a _View-Model_ for **Programs** and **Channels** in order to store information to DB from the API and fetch data from DB to UI.
