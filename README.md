# Design Patterns

### _MVC - Model View Controller_

_MVC_'s structure depends on three things. Model, View and Controller. 

-The _Model_ is actually the data we want to display at the UI, the rules that we use to manipulate those data and 
	it also describes the business logic behind. 
-The _View_ is actually the UI presenting the data we want. The _Model_ and the _View_ are connected with each other,
	with the ####Observer#### pattern.
-The _Controller_ is the component that processes the data from the _Model_ and send the results to _View_.
	It acutally connects the above components.

### _MVP - Model View Presenter_

_MVP_ is a design pattern very similar to _MVC_. In fact, the _MVP_ is developed from _MVC_.
This design pattern has 3 main components too, the Model, the View and the Presenter.

The _Model_ and the _View_ here have actually the same finctionality with the _MVC_'s. 

	-The _Presenter_ connects the _View_ and the _Model_ in the way that, it gets the user's input in the _View_, 
	then processes the data with the _Model_ and sends back the output to the _View_. 
	The _Presenter_ defines an interface which is implemented in any component that has to do with the functionality of the View, like         an ####Activity#### or a ####Fragment####.
