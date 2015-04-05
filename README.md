# Ghost Hunter
A really lame game for CS class

## Required functionality:
  - ~~Splash screen showing:~~
    - ~~All team names~~
    - ~~UVA CS2110 Spring 2015~~
    - ~~Some kind of logo or name~~
    - ~~Shoutout to someone who gave a lot of help (if applicable)~~
  - ~~See the user/character on the screen. Must move by one of the following:~~
    - ~~Buttons (e.g. up, down, left, right) to move the user/character~~
  
  - Auto ~~generate~~ ghosts near user Needs to be auto-generated. Right now it's on startup
    - Don't need to move after spawning
  - ~~Have a view of the world where the user and ghosts are both seen on the screen~~

  - Technique for killing ghosts

  - Keep track of some sort of progress statistics (points, kills, etc)
    - Must be displayed

#### Another 50 points of optional requirments must be implemented to get full credit

### Optional Functionality:

Points        | Tag           | Functionality                       | Requires
------------- | ------------  |-------------------------------------|-------------------|
50            | Requirements  | Above                               |
7             | Move          | Ghosts move on their own            |
5             | Dangerous     | Close to ghost hurts user           |
5             | Front         | A ghost's front is dangerous        | Move & dangerous
3             | Back          | A ghost's back is vunerable         | Move
5             | Proximity     | Alert that user is close to ghost   |
5 - 20        | Objects       | Objects laying around               |
5             | Loot          | Dead ghost drops something          |
7             | Special item  | Item can kill ghost                 |
5 - 15        | Stems         | Killing a ghost takes steps         |
3             | Ignore        | User can ignore ghost in some way   | Proximity
5             | Money         | Some kind of money system           |
5             | Costview      | Features that cost money appear     | Money
5             | Ongoing       | Some feature has an ongoing cost    | Money
5             | Bombs         | You can find/buy ghost bombs        |
5             | Detectors     | Find detectors for special items    | Special item
5             | Wildcard      | Wildcard that bypasses usual steps  | Steps
5             | Repellent     | Repels nearby ghosts                | Move
5             | Barriers      | You can lay ghost barriers          | Move
5             | Discover      | Discover how ghost can be killed    | Special item
3             | Limited       | Barriers are limited in some way    | Barriers
5             | Enclosed      | An enclosed ghost dies              | Barriers
10            | Saved         | Progress is saved between runs      |
10            | Restore       | On restore,danger level is restored | Saved and Dangerous
10            | Background    | Background mode when paused         |
0 - 5         | Visual        | How visually impressive the app is  |
3+            | Var. move     | Different ways to move around       |
5+            | Sound         | Sound effects and background music  |
5             | Animation     | Some kind of animation              |
