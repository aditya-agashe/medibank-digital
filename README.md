To run from the command line:

git clone https://github.com/aditya-agashe/medibank-digital.git

Ensure that Docker is installed.

To build the application and the Docker image (image name is medibank-digital), run: 
mvn clean install -Ddocker

Ensure to replace value for UNSPLASH_ACCESS_KEY in docker-compose.yml

To run the application stack: 
docker-compose up (see the docker-compose.yml for details) 

