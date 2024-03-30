# Assignment II Pair Blog Template

## Task 1) Code Analysis and Refactoring ⛏️

### a) From DRY to Design Patterns

[Links to your merge requests](/put/links/here)

> i. Look inside src/main/java/dungeonmania/entities/enemies. Where can you notice an instance of repeated code? Note down the particular offending lines/methods/fields.

[Answer]
### **Common Characteristics:**

1. **Inheritance from `Enemy` Class**:

   All enemy classes (**`Mercenary`**, **`Spider`**, **`ZombieToast`**, etc.) extend the **`Enemy`** abstract class. This inheritance ensures that all enemies share common functionalities and characteristics defined in the **`Enemy`** class, such as battle statistics and interaction with the game map.

2. **Implementation of `move(Game game)` Method**:

   Each enemy class implements a **`move(Game game)`** method, which defines the behavior of the enemy's movement within the game environment. This method is essential for controlling enemy behavior during gameplay.


> ii. What Design Pattern could be used to improve the quality of the code and avoid repetition? Justify your choice by relating the scenario to the key characteristics of your chosen Design Pattern.

[Answer]
The scenario provided involves different types of enemies in a game, each with its own unique behavior and characteristics. To improve the quality of the code and avoid repetition, the Strategy design pattern can be applied.

### **Strategy Design Pattern:**

### Justification:

1. **Eliminating Repetition:** The Strategy pattern allows you to define a family of algorithms, encapsulate each one, and make them interchangeable. In the provided code, each enemy type (**`Mercenary`**, **`Spider`**, **`ZombieToast`**, etc.) has its own **`move()`** method implementation. By applying the Strategy pattern, you can extract the movement logic into separate strategy classes, eliminating the repetition of similar code in each enemy class.
2. **Encapsulating Algorithms:** Each enemy type may have different movement behaviors. By encapsulating the movement logic into separate strategy classes, you encapsulate these algorithms independently of the enemy classes, promoting better code organization and maintainability.
3. **Easy to Extend and Modify:** With the Strategy pattern, adding new enemy types or modifying the behavior of existing ones becomes easier. You can simply create a new strategy class or modify an existing one without impacting other parts of the codebase.

> iii. Using your chosen Design Pattern, refactor the code to remove the repetition.

[Briefly explain what you did]
### Implementation:

To apply the Strategy pattern:

- Define an interface (**`MoveStrategy`**) that declares a method for enemy movement, such as **`move()`**.
- Implement concrete strategy classes (**`MercenaryMoveStrategy`**, **`SpiderMoveStrategy`**, **`ZombieToastMoveStrategy`**) that encapsulate the specific movement logic for each enemy type.
- In each enemy class, instead of implementing the movement logic directly, maintain a reference to the **`MoveStrategy`** interface and delegate the movement behavior to the corresponding strategy object.

This approach allows for better code reuse, easier maintenance, and facilitates the addition of new enemy types with different movement behaviors without modifying existing code extensively.

### b) Observer Pattern

> Identify one place where the Observer Pattern is present in the codebase, and outline how the implementation relates to the key characteristics of the Observer Pattern.

[Answer]
In the provided codebase, the Observer Pattern is present in the `Switch` class. Here's an outline of how its implementation relates to the key characteristics of the Observer Pattern:

### Implementation of the Observer Pattern in the `Switch` Class:

1. **Subject (Publisher):**
    - The `Switch` class serves as the subject or publisher in the Observer Pattern.
    - It maintains a list of subscribers (observers) in the `bombs` list.
2. **Subscribers (Observers):**
    - The `Bomb` objects act as observers or subscribers.
    - They subscribe to the `Switch` subject by calling the `subscribe` method.
3. **Notification:**
    - When a certain condition is met (i.e., when a `Boulder` overlaps with the `Switch`), the `Switch` notifies all its subscribers (bombs).
    - This notification is achieved by calling the `notify` method on each subscribed `Bomb` object.
4. **Subscription Management:**
    - Subscription management is handled through the `subscribe` and `unsubscribe` methods of the `Switch` class.
    - When a `Bomb` object wants to subscribe to the `Switch`, it calls the `subscribe` method.
    - Conversely, when a `Bomb` object wants to unsubscribe, it calls the `unsubscribe` method.

### Key Characteristics of the Observer Pattern Present in the Implementation:

1. **Loose Coupling:**
    - The `Switch` class and the `Bomb` class are loosely coupled.
    - The `Switch` does not need to have direct knowledge of the specific types of `Bomb` objects it interacts with, enhancing flexibility.
2. **Event Handling:**
    - The `Switch` class triggers events when certain conditions occur (e.g., when a `Boulder` overlaps with it).
    - These events are handled by notifying the subscribed `Bomb` objects, allowing them to react accordingly.
3. **Dynamic Subscription:**
    - Subscribers (`Bomb` objects) can dynamically subscribe and unsubscribe from the `Switch`.
    - This allows for flexible management of subscriptions based on runtime conditions.
4. **Scalability:**
    - The pattern supports scalability, allowing for multiple `Bomb` objects to subscribe to the `Switch` without modifying the `Switch` class.
    - Additionally, new types of observers can be added without altering existing code, promoting extensibility.
5. **Encapsulation:**
    - The `Switch` class encapsulates the logic for managing subscribers and notifying them of changes.
    - This encapsulation ensures that the internals of the `Switch` class are hidden from observers, promoting maintainability and reducing code complexity.

Overall, the implementation of the Observer Pattern in the `Switch` class enables decoupling between the subject (`Switch`) and its observers (`Bomb` objects), facilitating flexible and scalable event-driven behavior within the codebase.

### c) Inheritance Design

[Links to your merge requests](/put/links/here)

> i. Name the code smell present in the above code. Identify all subclasses of Entity which have similar code smells that point towards the same root cause.

[Answer]

> ii. Redesign the inheritance structure to solve the problem, in doing so remove the smells.

[Briefly explain what you did]

### d) More Code Smells

[Links to your merge requests](/put/links/here)

> i. What design smell is present in the above description?

[Answer]
There are several code smells present in the previous implementation of the collectable entities:

1. **Duplication of Logic**: Many of the collectable entities (**`Arrow`**, **`Bomb`**, **`Key`**, **`Sword`**, **`Wood`**, etc.) contain almost identical methods for interaction with the player (**`onOverlap`**, **`onMovedAway`**, **`onDestroy`**). This duplication violates the DRY (Don't Repeat Yourself) principle and makes the code more error-prone and harder to maintain.
2. **Violation of Single Responsibility Principle (SRP)**: The collectable entities are responsible for both representing their behavior in the game world and handling their interaction with the player. This violates the SRP, which states that a class should have only one reason to change. Separating concerns such as game behavior and player interaction can lead to cleaner and more maintainable code.
3. **Tight Coupling**: The previous implementation tightly couples the pickup logic with the collectable entities themselves. This makes it difficult to change the pickup behavior without modifying each individual collectable class.

> ii. Refactor the code to resolve the smell and underlying problem causing it.

[Briefly explain what you did]
For this project, I refactored the collectable entity classes within the **`Collectable`** folder to adhere to a new design pattern. The goal was to centralize the handling of picking up items by introducing a common interface named **`Collectable`**.

Firstly, I extracted the **`Collectable`** interface, defining a contract that all collectable entities must adhere to. This interface includes a method **`onPickup(Player player, GameMap map)`** to encapsulate the specific pickup behavior of each collectable entity.

Next, I modified each collectable entity class (e.g., **`Arrow`**, **`Bomb`**, etc.) to implement the **`Collectable`** interface. This allowed each entity to provide its own implementation of the **`onPickup`** method, defining its unique pickup behavior.

Within the **`Arrow`** class, I refactored the **`onOverlap`** method to delegate the pickup logic to the **`onPickup`** method defined in the **`Collectable`** interface. This ensured consistency across all collectable entities and promoted code reusability.

By adhering to the common **`Collectable`** interface, I established consistent naming and behavior throughout the codebase. This not only improved code readability but also made future enhancements and maintenance easier.

Overall, the refactoring introduced a more modular and maintainable design for handling the picking up of items in the game. It decoupled the pickup logic from individual collectable entity classes, making the codebase more extensible and easier to maintain.


### e) Open-Closed Goals

[Links to your merge requests](/put/links/here)

> i. Do you think the design is of good quality here? Do you think it complies with the open-closed principle? Do you think the design should be changed?

[Answer]

> ii. If you think the design is sufficient as it is, justify your decision. If you think the answer is no, pick a suitable Design Pattern that would improve the quality of the code and refactor the code accordingly.

[Briefly explain what you did]

### f) Open Refactoring

[Merge Request 1](/put/links/here)

[Briefly explain what you did]

[Merge Request 2](/put/links/here)

[Briefly explain what you did]

Add all other changes you made in the same format here:

## Task 2) Evolution of Requirements 👽

### a) Microevolution - Enemy Goal

[Links to your merge requests](/put/links/here)

**Assumptions**

[Any assumptions made]

**Design**

[Design]

**Changes after review**

[Design review/Changes made]

**Test list**

[Test List]

**Other notes**

[Any other notes]

### Choice 1 (Insert choice)

[Links to your merge requests](/put/links/here)

**Assumptions**

[Any assumptions made]

**Design**

[Design]

**Changes after review**

[Design review/Changes made]

**Test list**

[Test List]

**Other notes**

[Any other notes]

### Choice 2 (Insert choice)

[Links to your merge requests](/put/links/here)

**Assumptions**

[Any assumptions made]

**Design**

[Design]

**Changes after review**

[Design review/Changes made]

**Test list**

[Test List]

**Other notes**

[Any other notes]

### Choice 3 (Insert choice) (If you have a 3rd member)

[Links to your merge requests](/put/links/here)

**Assumptions**

[Any assumptions made]

**Design**

[Design]

**Changes after review**

[Design review/Changes made]

**Test list**

[Test List]

**Other notes**

[Any other notes]

## Task 3) Investigation Task ⁉️

[Merge Request 1](/put/links/here)

[Briefly explain what you did]

[Merge Request 2](/put/links/here)

[Briefly explain what you did]

Add all other changes you made in the same format here:
