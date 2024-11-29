[![Actions Status](https://github.com/NankouFuraku/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/NankouFuraku/java-project-78/actions)<a href="https://codeclimate.com/github/NankouFuraku/java-project-78/test_coverage"><img src="https://api.codeclimate.com/v1/badges/d71017cc5ca002387520/test_coverage" /></a>

### Data Validator
#### This application is designed to validate various types of data according to different rules you can add to an object of Validator; Doing so you can verify String, Integer and Map, including all it's filling.
#### To run this application you'll need JDK 21 or higher and Gradle 8.5 or higher.
#### In the core directory run:
    gradle build

#### Then start the application with:
    jshell --class-path build/classes/java/main

#### Create and object of Validator.
    Validator validator = new Validator();

### StringSchema
#### Using string() on the object of Validator creates schema for String validation.
    StringSchema schema = validator.string();

#### This schema has following methods:
* __required()__ - makes this fields required. Forbids usage of null of empty String.
* __minLength(int length)__ - creates a minimum length requirement. The String must be equal or longer than __length__ argument.
* __contains(String substring)__ - creates a containment requirement. The String must contain the __substring__ argument.
#### Example of StringSchema:

[![asciicast](https://asciinema.org/a/F7neZ8S0PJZtfePBCrsIFWp9D.svg)](https://asciinema.org/a/F7neZ8S0PJZtfePBCrsIFWp9D)

### NumberSchema 
#### Using number() on the object of Validator creates schema for Integer validation.
    NumberSchema schema = validator.number(); 

#### This schema has following methods:
* __required()__ - makes this fields required. Forbids usage of null.
* __positive()__ - creates a number's sign requirement. The number must be positive.
* __range(int a, int b)__ - creates a range requirement. The number must be in range between __a__ and __b__, including borders.
#### Example of NumberSchema:
[![asciicast](https://asciinema.org/a/0D1XqQ9y5pPWwyWVdQYwnuK9j.svg)](https://asciinema.org/a/0D1XqQ9y5pPWwyWVdQYwnuK9j)

### MapSchema
#### Using map() on the object of Validator creates schema for Map validation.
    MapSchema schema = validator.map();

#### This schema has following methods:
* __required()__ - makes this fields required. Forbids usage of null.
* __sizeof(int size)__ - creates a requirement for Map's size. The number of key-value pairs must be equal to __size__.
* __shape(Map<String, BaseSchema<T>> schemas)__ - allows to validate every value of Map. This method requires Map<String, BaseSchema<String>>, where __key__ is an element that should be validated, and __value__ is an object of BaseSchema, that describes how this element should be validated.
#### Example of MapSchema:
[![asciicast](https://asciinema.org/a/Y34CUsMTN33S1tn6og56KKJeH.svg)](https://asciinema.org/a/Y34CUsMTN33S1tn6og56KKJeH)
