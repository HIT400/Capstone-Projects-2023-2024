# Proctoring System 
#### Munashe Sam Mudabura H200239Z - Capstone Project

## Installation

### 1. Install Python 3.11

Make sure you have Python 3.11 installed on your system. You can download it from the official Python website: [Python Downloads](https://www.python.org/downloads/)


### 2. Install `virtualenv` 

If you haven't installed `virtualenv` globally, run the following command to install it:

```shell
pip install virtualenv
```

### 3: Activate the Virtual Environment
To activate the virtual environment, use the appropriate command for your operating system:

#### Windows:
```
.\venv\Scripts\activate
```
#### macOS/Linux:
```
source venv/bin/activate
```
After executing the activation command, your terminal prompt should change to indicate that the virtual environment is active.


### 4. Install Required Packages

To install the required Python packages, navigate to the project directory and execute the following command:

```shell
pip install -r requirements.txt

//This command will install all the necessary dependencies listed in the requirements.txt file.
```

### 5. Install dlib Library
The proctoring system requires the dlib library. Run the command below:


```
cd models
pip install dlib-19.24.1-cp311-cp311-win_amd64.whl
```
### 6. Install CMake and Visual C++
To compile the dlib library, you need to have CMake and Visual C++ installed on your system. Follow the steps below to install them:

Download and install CMake from the official CMake website: CMake Downloads
Download and install Visual C++ from the official Microsoft website: Visual Studio Downloads. Import the Database
The proctoring system uses a database to store its data. To import the necessary database, follow the steps below:

### 7. Import Database

Locate the proctoring.sql file in the database folder of the proctoring system.
Import the proctoring.sql file into your preferred database management system (e.g., MySQL, PostgreSQL).
Usage
After completing the installation steps, you can now run the proctoring system. 

### 8. Execution

Execute the following command in the project directory:
```
python app.py
```
This command will start the Flask development server, and you can access the proctoring system by opening a web browser and navigating to http://localhost:8080, where 8080 is the port number specified in the Flask app configuration.
