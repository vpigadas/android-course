# Design Patterns

### Adapter

This pattern lets two incompatible classes work together by converting the interface of a class into another interface the client expects. A classic example is a RecyclerView.Adapter that binds a RecyclerView and a ViewHolder. 

The _Adapter pattern_ is used for both RecyclerView classes in the two activities of the project.

### Facade

The Facade pattern provides a higher-level interface that makes a set of other interfaces easier to use.

For example, if our Activity needs a list of channels, it should be able to ask a single object for that list without understanding the inner workings of our local storage, cache, and API client. Beyond keeping our Activities and Fragments code clean and concise, this facilitates any required future changes to the API implementation without any impact on the Activity.

The _Facade patter_ is used with Retrofit in the project; an interface is created to provide API data to client classes. This lets us make all types of customizations underneath without affecting the client.
