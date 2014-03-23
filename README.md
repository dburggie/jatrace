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
* Methods:
  1. `setWorld(World world)`
    * Tracer object will render the given world instead of its old one (if any).
  2. `setWorld()`
    * Sets Tracer object up with a default world that is empty and has a blue sky.
  3. `setCamera(Camera camera)`
    * Tracer object will render its world using the given camera.
  4. `setCamera()`
    * Sets Tracer object up with a default camera.
  5. `setSampleDepth(int depth)`
    * Sets how many reflections are calculated per ray.
  6. `draw(int passes)`
    * Renders image by tracer `passes` rays per pixel.
  7. `draw()`
    * Renders image using a default number of rays per pixel.
  8. `write(String filename)`
    * Writes a rendered image to disk using the given filename.
  9. `write()`
    * Writes a rendered image to disk using a default filename.



