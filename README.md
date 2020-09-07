# Introduction

The purpose of the specific project is the creation of an application capable of obtaining information regarding different TV programs. 
There are many ways and design patterns that you can implement such an application. 
For the specific project I selected to use the MVC design pattern because of the simplicity of the project in comparison to MVVM that could be another viable alternative for the specific project.

***

# MVC DESIGN PATTERN

## Description

The specific design pattern separates the application at a macro level into 3 different sets of responsibilities. The model, the view and the controller are those three different sets.

+ ### Model

> The model is the is responsible for the business Logic of the application and the data handling. It is usually the brains of an application. The model it is not tied with other two sets and for this reason it is easily reusable.

+ ### View

> The view is responsible for the direct representation of the model. The view has the responsibility to deliver the user interface and to communicate with the controller set every time that the user is interacting with the application. View sets are ideally created under the idea that the less they know the more loosely coupled they are with the model therefore they are more flexible to changes.

+ ### Controller

> The controller is the one that interconnects the model with the view. The controller is responsible to interpret what a pushed button on the application should do. Furthermore, the controller based on the changes that may occur on the model may alternate the view if it feels that this is appropriate.


***


# MVVM DESIGN PATTERN


## Description

The specific design pattern has a lot of similarities with MVC design pattern, but it has the benefits of easier testing and modularity. Furthermore, it reduces the code that is needed to connect the view with the model set.

+ ### Model

> The model for the MVVM design pattern has the same principles and the same usage as the MVC design pattern.

+ ### View

> The View layer receives data that is coming from the ViewModel and process them accordingly.

+ ### ViewModel

> The ViewModel is responsible for wrapping the model and prepare the data that is required by the View Layer. Moreover, it provides hooks to pass events to the model but the ViewModel it is not tied to the view layer.


***

# Implementation of MVC Design pattern at my project

The reason why I choose MVC design pattern over the MVVM is because of the simplicity of the specific project. Below is how I have implemented the specific design pattern.

__1__. The reason why I choose MVC design pattern over the MVVM is because of the simplicity of the specific project. Below is how I have implemented the specific design pattern.

__2__. The Controller layer is responsible for hooking events interchangeably between the Model and the View.

__3__. The Controller layer is responsible for hooking events interchangeably between the Model and the View.

***

# Comparison of the two Design Patterns (MVC vs MVVM)

The differences between the two design patters is that MVVM design pattern produces less code and it easier to test your project in comparison to MVC design pattern. In the contradiction MVC design pattern is a simple design pattern and it is suitable for very trivial application like the one that we have implemented for the specific project.

***

## References

[1] Jan 26 2017, "MVC vs. MVP vs. MVVM on Android", <https://academy.realm.io/posts/eric-maxwell-mvc-mvp-and-mvvm-on-android/>

[2] Feb 8, 2020, "MVC vs MVP vs MVVM for Android", <https://blog.usejournal.com/mvc-vs-mvp-vs-mvvm-for-android-5e2c83b8d4f2>


