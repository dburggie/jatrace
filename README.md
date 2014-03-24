# jatrace

## About this Package

This is a Java package that you can use to easily render 3D images of mathematical solids. It is designed to be easily extensible. The rendering is accomplished with relatively simple recursive sampling of the world by tracing of rays. This package contains a library of classes useful for performing your own renders, and will eventually provide a user interface with which to set up and render a world.

## The Library

The API for the jatrace library is relatively simple. The package layout is as follows:

* `jatrace`
  * `Vector` - describes a position in space or a direction
  * `Color` - describes an RGB color with floating point precision
  * `World` - maintains a list of bodies and the sky
  * `Camera` - describes camera position, orientation, focal window
  * `Tracer` - uses a `World` object and a `Camera` object to render an image
* `jatrace.bodies`
  * `Plane` - a simple mathematical plane of a single color described uniquely by a point and a direction
  * `Sphere` - a simple mathematical sphere of a single color described uniquely by a point and a radius
  * `CheckPlane` - a Plane object with two colors forming a checkered pattern on the surface
  * `OneWayPlane` - a Plane object that is transparent when viewed from one direction, but opaque from the other
* `jatrace.skies`
  * `BlueSky` - a simple blue sky with a single bright sun.
  * `Horizon` - a sky with variable color, a single sun, and a bright band

### `jatrace.Tracer` usage

* Constructors:
  1. `Tracer(jatrace.World w, jatrace.Camera c)
    * Tracer object will render the given world using the given camera.
  2. `Tracer()`
    * Tracer object initialized with default World and Camera objects. Calls to `setWorld(World)` and `setCamera(Camera)` should be made before calling `draw()`.
* Useful Methods:
  1. `setWorld(World world)`
    * Tracer object will render the given world instead of its old one (if any).
  2. `setCamera(Camera camera)`
    * Tracer object will render its world using the given camera.
  3. `setSampleDepth(int depth)`
    * Sets how many reflections are calculated per ray.
  4. `draw(int passes)`
    * Renders image by tracer `passes` rays per pixel.
  5. `write(String filename)`
    * Writes a rendered image to disk using the given filename.

### `jatrace.Vector` usage

* Constructors:
  1. `Vector(double x, double y, double z)`
    * Vector object will have the given xyz coordinates.
  2. `Vector()`
    * Vector object will be a zero vector.

* Useful Methods:
  1. `norm()`
    * Normalizes vector. Vector will be length one after calling this method. Normalizing a zero vector will result in an up vector.
  2. `trans( Vector v)`, `trans( Vector v, double s )`, `trans(double dx, double dy, double dz)`
    * Translates the vector by the given amount. In the second form, v is multiplied by s before the vector addition.
  3. `dup()`
    * Returns a new Vector object identical to the calling object.
  4. `copy(Vector v)`
    * Copies the argument's position.
  5. `cross(Vector v)`
    * Returns a new Vector object that is the cross product of caller and v. `u.cross(v)` is equivilent to uXv.

### `jatrace.Color` usage

* Constructors:
  1. `Color(double r, double g, double b)`
    * New color object will have the given rgb values. Note that acceptible values are in the range of (0.0,1.0).

* Useful Methods:
  1. `setRGB(double r, double g, double b)`
    * Resets color sample values to the arguments.
  2. `dup()`
    * Returns new Color object identical to calling object.
  3. `copy(Color c)`
    * Copies the sample values of the argument.

### `jatrace.World` usage

* Constructors:
  1. `World( Body [] bodies, Sky sky )`
    * Initializes world with given Body array and the given sky object.
  2. `World()`
    * Initializes world with no bodies and no sky. Rendering before `setSky(Sky sky)` has been called is an error.

* Useful Methods
  1. `addBody(Body body)`
    * Adds the given body to the world.
  2. `setSky(Sky sky)`
    * Sets the world's sky to the given object.
  3. `setBaseBrightness(double brightness)`
    * Sets the brightness of surfaces in shadow. Argument must be between zero and one.



