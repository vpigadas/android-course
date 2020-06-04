# Design Patterns

### MVC: Model-View-Controller vs MVVM: Model-View-ViewModel

One of the most broadly-used ways in which the data model is connected to the view (what the user sees) of an application is through an interface called a controller. In the _MVC_ pattern the controller acts as a tool that directly manipulates the data in its given model. Each controller is designed to both receive data and send back appropriate information based on the data received.The _MVC_ pattern was specifically designed in such way that the view and the model don’t need to know anything about each other.

However, the _MVC_ design pattern has its drawbacks. For one, the use of controllers to manipulate data models creates clutter in the backend. It’s a standard for each model in a database to have its own controller, so when an application scales much larger and evolves into an operation with many related models, the amount of controllers used grow in tandem. This, coupled with the introduction of new layers of abstraction brought on by most frameworks creates a codebase that becomes very difficult to navigate through.

Another popular method to connect the view to the model is through something elaborately called a ViewModel. Unlike the controller method, the ViewModel method relies heavily on the frontend of the application. It instead acts as a binder that binds data between the view and the model. Whereas the _MVC_ format is specifically designed to create a separation of concerns between the model and view, the _MVVM_ format with data-binding is designed specifically to allow the view and model to communicate directly with each other. It’s simple and allows the view to directly communicate with the backend. Because of this, _MVVM_ single page applications can move quickly and fluidly and save information to the database continuously.

However, the MVVM format comes with its own flaws as well. Because it relies on data binding, the ViewModel consumes a considerable amount of memory in comparison to it’s controlling counterparts. Larger applications that use the ViewModel method regularly become  difficult to run. For this reason, the _MVVM_ design pattern is used mostly for single/few page applications on the web.

Considering all the aforementioned, I found the _MVVM_ design pattern most suitable for my project.

### MVVM in the project

The _MVVM_ is the design pattern based on which the whole project is structured.

* Views (activities) display a certain shape of data. They have no idea where the data comes from.
* ViewModels hold a certain shape of data and commands, they do not know where the data, or code, comes from or how it is displayed.
* Model hold the actual data
