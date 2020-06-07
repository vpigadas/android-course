# Introduction

This document compares two well known code design patters, *MVC*, a pattern first introduced in 1987 in the Smalltalk language and widely used today, especially in web applications [1] and Clean Architecture. A software design pattern can be defined as a standardized solution to many often occurring issues in software design, improving the maintainability of the produced code [2][3].

## The MVC Pattern

*Model-View-Controller*, mode widely known as **MVC*, is a software design pattern that is notable for separating an application into three distinct groups of componments, each of which has unique functionality making it easier to code, debug and test the application due to the separation of concerms.

![Image of the MVC Model](https://docs.microsoft.com/en-us/aspnet/core/mvc/overview/_static/mvc.png?view=aspnetcore-3.1)  
*Figure 1. Chart that displays the componments of the MVC architecture.*

In partiuclar, the *Model* represnets the part of the application that holds the main application and any logic that should be applied by it, while the *View* is responsible for pressenting content to the user through the application's user interface (UI). Last but not least, the *Controller* is the part of the application that handles user input and controls which parts of the view are rendered and how they are updated to respond to interactions [4].

Through this parition of the application, MVC aims to separate the business logic from the application interface, enable the creation of extendable, reusable and more easily maintainable code, as well as facilitate simultenous works on diffrent componements of the application, even by more than one developer [5].

## Clean Architecture

*Clean Architecture* is another software design pattern, whose heart is found in what is called as *The Dependency Rule*, which describes the idea of encapuslating dependecings in the application through a ring-like structure, where dependencies only point inward. Core entities that are vital for the application's purpose (such as business rules or representations of real world objects) are kept in the inner layers, while implementation details such as frameworks and database systems are housed in the outer layers [6].

![Image of the Clean Architecture Model](https://cdn-media-1.freecodecamp.org/images/oVVbTLR5gXHgP8Ehlz1qzRm5LLjX9kv2Zri6)  
*Figure 2. Chart that displays the layers of Clean Architecture.*

In more detail, Clean Architecture separates the application to four layer, each of which is included in the next. The fist, *Entities* contains only plain objects realted to the business domain while the second, *Use Cases* represents business rules and the logic that applies them and ensures their valid application. The third layer, referred to as *Interfaces/Adapters* contains any operations that are related to retrieving and storing data (from sources like the network, a database or the file system) as well as defines related interfaces and implements ones defined by the Use Cases layer. Last but not least, the outermost layer, *External Interfaces* contains all external and 3rd Party frameworks and libraries that are used by the application, separating them from the application's internal logic [7].

# Comparison

While both valid and structuered, the two software design patters analyzed are radically different in their approach to code organization. The MVC model is implementation centric, organizing code based on it's function to the application. In contrast, Clean Architecture is more entity centric, organizing the application with a focus on the business logic and the needs for which it were created. In addition, the MVC model is bidirectional, with different componements interacting with each other in both directions, which comes in contrast to the Clean Architecture's logic of inner componments only being used by more outer componments. In general, MVC focuses on the separation between business logic, the interface and interaction, which resembles the front and back end separation common in web applications, while Clean Architecture attemps to structure software based on the principes of encapsulation that define object oriented programming languages. It is as such clear, that no model is directly superior to the other. The model that is best suited for every application will depend on the project's nature and requirements as well as the programing paradigm, language and platform utilized.

# MVC In Project

After comparison of the alternatives, MVC was chosen as the most suitable design pattern for this project. The MVC structure is clear, extensible and compatible with the structure of the existing Android API component that are used in the application. Following the *Model-View-Contoller* model, the Java files of the application have been distributed into three top level packatges, each one representing one of the component groups.

In particular, the *View* directory contains all Activity classes. Activities are the classes that are responsible for calling the Android API methods that are responsible for rendering the application's interface. In contrast, the *Controller* directory holds classes that contain the application's internal object representations (the Channel class) and logic. Logic classes have also been categorized in directories according to the function they serve (such as database and communication).

Inside the all packages, classes are named in accordance to either the objects they represent (for classes that will be primarily use as instances to represnet objects manipulated by the application logic, whether they include related methods or not) or the function they enclose (for classes that will be instaced to or provide static methods that are vital for pressenting or updating the application UI or enclose internal application functions). In addition, methods contained in these classes are always named according to the functionality they offer.

Closing, it is important to note that the model is only used for the organization of code found in the Java classes of the project. Other files and resources, such as bitmaps, XML layout files and others are not included, as their organization is enforced by Android's project specifications which should not be modified. Last but not least, it should also be noted that, if beneficial, some methods and inner classes (for example reconnectTask in MainActivity) related to the mode but operating directly as part of a given activity, and only as part of the given activity, may be contained in the activity instead of the Model. This is jusdged as an acceptable sacrifise to avoid the use of unnecessary intents, instancing or getter/setter calls that would complicate the code and reduce performance as well as readability.

# References

[1]Guru99, "MVC Tutorial for Beginners: What is, Architecture & Example", 2020, https://www.guru99.com/mvc-tutorial.html  
[2]Refactoring.guru, “What’s a design pattern?”, 2020, https://refactoring.guru/design-patterns/what-is-pattern  
[3]Son, B., “Understanding software design patterns”, 2019, https://opensource.com/article/19/7/understanding-software-design-patterns  
[4]Smith, S., "Overview of ASP.NET Core MVC", 2020, https://docs.microsoft.com/en-us/aspnet/core/mvc/overview?view=aspnetcore-3.1  
[5]Google, inc, "MVC Architecture", 2020, https://developer.chrome.com/apps/app_frameworks  
[6]Macneil, M., "Better Software Design with Clean Architecture", 2018, https://fullstackmark.com/post/11/better-software-design-with-clean-architecture  
[7]FreeCpdeCamp, "A quick introduction to clean architecture", 2018, https://www.freecodecamp.org/news/a-quick-introduction-to-clean-architecture-990c014448d2/
