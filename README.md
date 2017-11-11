[<img src=https://user-images.githubusercontent.com/6883670/31999264-976dfb86-b98a-11e7-9432-0316345a72ea.png height=75 />](https://reactome.org)

# Diagram-Core
#### What is the Diagram Core?
The Diagram core contains a collection of server-side tools, mainly focusing on the generation of a set of 
JSON files to be consumed by the Pathway Diagram Viewer v3.0. As illustrated in the following figure, 
the Diagram-core utilises the Reactome-Core to generate two different types of files for every single pathway diagram. 
The first file includes layout information (nodes, edges, co-ordinates, glyphs, names, shades, etc.), 
while the second file includes a graph of the pathway diagram and all its contained entities.

![Overview of the Diagram-Core](./doc/diagramCore.png "Overview of the Diagram-Core")

#### How do I generate all pathway diagrams ?
** Maven Setup: https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html.

1. Clone the Diagram-Core repository on your end
    ```console
    git clone https://github.com/reactome-pwp/diagram-core.git
    ```
 
2. Navigate into diagram-core

3. Package with Maven 
    ```console
    mvn clean package
    ```
  
4. Diagram-core Help: --help 
    ```console
    java -jar target/tools-jar-with-dependencies.jar --help
    ```

5. Convert all pathway diagrams
  
    1. command help (ensure special characters are escaped):

        ```console
        java -jar target/tools-jar-with-dependencies.jar Convert
             -h dbhost 
             -d dbschema
             -u dbuser
             -p dbpass 
             -o output_folder
             -r list_of_trivial_molecules
             -v 
        ```

    2. Please note that the current list of trivial molecules can be found in [./src/main/resources/trivialchemicals.txt](./src/main/resources/trivialchemicals.txt)

        ```console
        java -jar target/tools-jar-with-dependencies.jar Convert \
             -h localhost \
             -d reactome \
             -u root \
             -p root \ 
             -o /Users/reactome/diagram/static \
             -r /Users/reactome/diagram/trivialchemicals.txt \
             -v 
        ```

#### How do I generate specific pathway diagrams ?
The user can also specify a target list of diagrams to be generated by using the -t switch and the list of DBIDs 
like in the example:

  ```console
  java -jar target/tools-jar-with-dependencies.jar Convert \
       -h localhost \
       -d reactome \
       -u root \
       -p root \ 
       -o /Users/reactome/diagram/static \
       -r /Users/reactome/diagram/trivialchemicals.txt \
       -t:5654738,5655291,2219530,5637815
       -v 
  ```

##### Important Notice
In case of connection to a remote server via ssh there might be a "No X11 DISPLAY variable was set" error. Then try unsetting the DISPLAY using the following command:

  ```console
  unset DISPLAY
  ```
