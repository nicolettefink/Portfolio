===========================================================
MATH 2605 Final Project Application
Version 3.1 (11/24/2014)

Author: Nicolette Fink
===========================================================

Running the Application:
1. Open command prompt/terminal and navigate to the
   directory with the java source files necessary for
   this application.
2. Compile the source code with the command:
   javac Application.java
   (Note: this step only has to be done once, if
   the code has already been compiled, skip to step 3.)
3. Run the application with the command: java Application

Entering Valid Inputs:
The application is set up to handle some instances of
typos or invalid input parameters by displaying error
messages, however, it is possible that not entering
data in correctly could cause the program to crash (in
which case you would just re-run it using the above
instructions). To ensure data is entered correctly,
below are details about the formatting the program
can accept:

QR Factorization
    Enter the matrix into the input box in the center of
    the screen with each number separated by a single space
    and each row on a separate line. For example:

    2.145 9.483 1.302
    1.288 4.181 1.392
    6.449 2.839 8.270

    *Note: the matrix does not have to be a square matrix.

    Then select either Householder Reflections or Given's
    Rotation from the drop down menu to choose which method
    to calculate the QR decomposition with. Click 
    "Calculate" to display the solution.
    
Modified Gauss-Newton Method
    File Pathway: If the text files containing the list of
    data points is in the same folder as the java source
    files, you can simply enter the name of the .txt file.
    For example: quad_points.txt
    
    Otherwise, you'll have to enter the entire pathway to
    the .txt file. For example:
    C:\Users\Nicolette\Documents\quad_points.txt
    
    *Note: When typing it in, you must type the .txt extension
    for the program to read the file.
     
    The data file itself should be formatted with each
    point on a single line, and it's components separated
    by a comma. The x-coordinate should be listed first,
    followed by the y-coordinate. For example:
    
    0.1,2.0
    2.4,-8.1
    4.2,-1.1
    0.55,0.37
    12.101,0.735
    
    The points must start on the first line of the text
    file and must not contain any blank spaces in between
    them. 

    Initial Guesses (a,b,c): Enter a single floating point
    number in each of the text fields corresponding to an
    initial guess parameter.
    
    Number of iterations: Enter a single integer value
    (not a floating point number).
    
    From the first drop down menu, choose either Quadratic,
    Exponential, Logarithmic, or Rational as the type of
    function you are attempting to approximate.
    
    From the second drop down menu, choose either
    Householder Reflections or Given's Rotations as
    the method used to calculate QR within the
    Gauss-Newton method.
    
    Click "Calculate" to display the approximations for
    a, b, and c.
    
Power Method
    Matrix: Enter a matrix into the matrix input field 
    using the same format described above in the QR 
    factorization section. For example:
    
    2.145 9.483 1.302
    1.288 4.181 1.392
    6.449 2.839 8.270
    
    Guess: Enter an initial guess in the form of a non-zero
    vector. Each line should contain a single value and the
    number of elements should equal the number of columns
    of the input matrix. For example:
    
    1.0
    1.0
    1.0

    Tolerance Parameter: A single, floating point number.
    
    Maximum Iterations: A single, integer value.
    
    Click "Calculate" to display the approximated
    largest eigenvalue, it's corresponding eigenvector,
    and the number of iterations required to reach
    that solution.
    
    *Note: If the program reaches the maximum number of
    iterations before estimating an eigenvalue within the
    input tolerance parameter, the values returned for
    the eigenvalue and eigenvector will be "null".
    
Graph Power Method
    Click the "Run" button to display
    two scatter plots representing the data collected
    though the method explained on that screen of the
    application. The two plots will immediately be opened
    after the calculations are complete, but these plots
    will also be saved as images under the names
    "Power_Method" and "Inverse_Power_Method" within
    the current directory. (Note: these images will
    be overwritten each time "Run" is called within
    the application. To save these images for later
    use, copy them into another folder.)
    
Animation
    Click the "Play/Pause" button to begin or stop
    the animation. The animation will continue to run
    in a loop until either the user pauses it or
    switches to a different screen within the application.
    
    
Other Notes:
- The written component for each part of the project is
in the .pdf document titled "WrittenComponent".
- Plot.java, Pic.java, and Pixel.java were not written
by me. Those are external files strictly used to
plot data and display the images. The liscense for
Plot.java is included (see License.txt). Pic.java and 
Pixel.java were used as example code in CS 1331.
    
===========================================================
