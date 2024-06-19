from flask import Flask, render_template, request,redirect,url_for
from functools import wraps
from flask_login import current_user
import MySQLdb as mysql
import pandas as pd
from flask_mysqldb import MySQL
from flask_bcrypt import Bcrypt
import datetime
from datetime import date
import joblib
import matplotlib.pyplot as plt
from io import BytesIO
import base64
from flask import make_response
from flask_login import LoginManager, login_user, login_required, logout_user,UserMixin
from flask import flash
from werkzeug.security import generate_password_hash, check_password_hash
from flask import session
from sklearn.ensemble import BaggingRegressor
import os
import numpy as np
import random
import json
import math
from secrets import token_bytes
import pycountry
import warnings
from reportlab.lib.pagesizes import letter
from reportlab.platypus import SimpleDocTemplate, Table, TableStyle, Paragraph, PageTemplate, Frame, Image
from reportlab.lib import colors
from reportlab.lib.units import inch
from reportlab.lib.styles import getSampleStyleSheet
from reportlab.lib.styles import ParagraphStyle
from datetime import datetime
from io import BytesIO
from reportlab.pdfgen import canvas  # Import canvas module
# from sklearn.exceptions import InconsistentVersionWarning

# warnings.filterwarnings("ignore", category=InconsistentVersionWarning)

# Generate a random secret key using secrets.token_bytes
secret_key = token_bytes(32)
secret_key_hex = secret_key.hex()


app = Flask(__name__)
countries = [country.name for country in pycountry.countries]
app.config.from_pyfile('db_config.py')
model = joblib.load('bagging_regressor_model.pkl')

valid_feature_names = model.feature_names_in_

# print(valid_feature_names)
conn = mysql.connect(host=app.config['MYSQL_HOST'], user=app.config['MYSQL_USER'], password=app.config['MYSQL_PASSWORD'], database=app.config['MYSQL_DB'])
cursor = conn.cursor()
app.config['SECRET_KEY'] = secret_key_hex
bcrypt = Bcrypt(app)

login_manager = LoginManager()
login_manager.init_app(app)

# @login_manager.user_loader
# def load_user(user_id):
#     # Replace with your logic to retrieve user by ID from database or other source
#     if user_id == 1:  # Replace with actual user retrieval logic
#         return User(1, 'admin', 'password')  # Replace with actual user data
#     return None

@login_manager.user_loader
def load_user(id):
    cursor.execute("SELECT * FROM users WHERE id = %s", (id,))
    data = cursor.fetchone()
    if data:
        user = User(data[0])  # Assuming user ID is in the first column
        return user
    else:
        return None

@app.route('/prediction')
def predict():
    with open('encoded_values_mapping.json', 'r') as file:
        countries = json.load(file)

    return render_template('prediction.html',countries=countries)

def loginMethod(email, password):
    try:
        print("line1")
        cursor.execute(f"SELECT * FROM users WHERE email = '{email}';")
        user = cursor.fetchone()
        print(user[3])
        print(password)

        if user[2] == email and bcrypt.check_password_hash(user[3], password):
            # User authenticated, store user object in session
            #print("line2")
            #session['user_id'] = user[0]
            flash("Logged In","success")
            return True
        else:
            #print("line3")
            #flash("Invalid email or password.","danger")
            return False
    except Exception as e:
        #print("line4")
        # flash("Incorrect Login details","danger")
        return False
    finally:
        print("line5")
        #cursor.close()
@app.route('/login')
def loginPage():
    return render_template('login.html')

@app.route('/login-user',methods=['GET', 'POST'])
def login():
    
    if request.method == 'POST':
        session['role'] = ""
        email = request.form['email']
        password = request.form['password']
        remember = request.form.get('remember')
        cursor.execute(f"SELECT * FROM users WHERE email = '{email}';")
        user_data = cursor.fetchone()
        #user_data = get_user(user[0])

        if loginMethod(email, password):
            user = User(user_data[0])
            session['role'] = user_data[4]
            #remember_me = False;
            #print(f"Logged in user: {user.id}")  # Debugging statement
            login_user(User(user_data[0]),remember = remember)
            return redirect(url_for('dashboard'))
        else:
            flash("Incorrect Login Details","danger")
            return render_template('login.html')
    else:
        return render_template('login.html')


class User(UserMixin):
    def __init__(self, user_id):
        self.id = user_id
    
    def get_username(self):
        cursor.execute(f"SELECT username FROM users WHERE id = {self.id}")
        data = cursor.fetchone()
        if data:
             return data[0]  # Assuming username is in the first column
        else:
            return None
    
    def is_admin(self):
        cursor.execute(f"SELECT role FROM users WHERE id = {self.id}")
        data = cursor.fetchone()
        if data is not None:  # Check if data is not None before accessing its elements
            if data[0] == "admin":
                return True
        
        return False

def get_user(user_id):
    cursor.execute("SELECT * FROM users WHERE id = %s",(str(user_id),))
    user_data = cursor.fetchone()
    if user_data:
        
        return User(user_data[0])  # Assuming the user ID is in the first column of the users table
    else:
        return None


# Define table structure (modify as needed)
table = """
CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL,
  date_created DATE NOT NULL,
  date_modified DATE NOT NULL
)
"""

cursor.execute(table)


def has_permission(required_role):
    def decorator(func):
        @wraps(func)
        def wrapper(*args, **kwargs):
            if current_user.is_authenticated:
                #cursor = mysql.connection.cursor()
                cursor.execute(f"SELECT role FROM users WHERE username = '{current_user.get_username()}'")
                role = cursor.fetchone()
                if role and role[0] == required_role:
                    return func(*args, **kwargs)
                else:
                    return render_template('401.html')
            else:
                return "Please log in to access this page."
        return wrapper
    return decorator

@app.route('/')
def index():
    if current_user.is_authenticated:
        return redirect(url_for('dashboard'))
    else:
        return redirect(url_for('loginPage'))
    

@app.route('/update-profile', methods = ['POST'])
@login_required
def updateuser():
    name = request.form['username']
    email = request.form['email']
    password = hash_password(request.form['password'])
    id = current_user.get_id()
    if request.method == 'POST':
        cursor.execute("UPDATE users SET username = %s, email = %s, password = %s WHERE id = %s", (name, email, password,id))
        conn.commit()
        logout_user()
        return redirect(url_for('login'))
    


@app.route('/profile')
@login_required
def profile():
    username = current_user.get_username()
    try:
        cursor.execute(f"SELECT * FROM users WHERE username = '{username}'")
        user_data = cursor.fetchone()
        print(user_data)
    except Exception as e:
        # Handle database error
        print(f"Error fetching user data: {e}")
        user_data = None

    return render_template('profile.html', user=user_data)

def get_current_date():
    current_date = date.today().strftime('%Y-%m-%d')  # Format as YYYY-MM-DD
    return current_date  # Format as YYYY-MM-DD

@app.route('/users')
@login_required
@has_permission('admin')
def users():
    cursor.execute("SELECT * FROM users")
    data = cursor.fetchall()
    return render_template('users.html', users=data,role = session['role'])

def fetch_prediction_data():
    

    # SQL query to fetch dates and prediction values from database
    cursor.execute("SELECT date, predicition FROM predictions")
    data = cursor.fetchall()

    # Fetch data into a DataFrame using pandas
    df = pd.DataFrame(data, columns=['date', 'prediction'])

    

    return df


def plot_graph():
    # Fetch prediction data from database
    df = fetch_prediction_data()

    # Extract dates and prediction values from DataFrame
    dates = df['date']
    predictions = df['prediction']

    # Plotting the graph using matplotlib
    plt.figure(figsize=(10, 6))
    plt.plot(dates, predictions, marker='o', linestyle='-')
    plt.xlabel('Date')
    plt.ylabel('Prediction')
    plt.title('Predictions Over Time')
    plt.xticks(rotation=45)
    plt.tight_layout()

    # Convert plot to base64 encoding
    buffer = BytesIO()
    plt.savefig(buffer, format='png')
    buffer.seek(0)
    plot_data = base64.b64encode(buffer.getvalue()).decode()
    return plot_data


@app.route('/statistics')
@login_required
def dashboard():
    cursor.execute("SELECT COUNT(*) FROM predictions")
    user_count = cursor.fetchone()[0]
    counter_file = 'counter.txt'
    
    # # , report_count = counter
    with open(counter_file, 'r') as file:
        counter = int(file.read().strip())

    try:
        username = current_user.get_username()
    except AttributeError:
        username = None  # Or redirect to login page
    return render_template('dashboard.html',username=username, prediction = user_count, report_count = counter)

@app.route('/new-user')
@login_required
def addUser():
    return render_template('newuser.html')

@app.context_processor
def inject_session_data():
    role = session.get('role')
    return dict(role=role)

@app.route('/prediction')
@login_required
def prediction():
    with open('encoded_values_mapping.json', 'r') as file:
        countries = json.load(file)
    print(countries)     
    return render_template('prediction.html',countries=countries)

@app.route('/add-user', methods=['GET', 'POST'])
@login_required
@has_permission('admin')
def add():
    
    

    if request.method == 'POST':
        name = request.form['username']
        email = request.form['email']
        password = hash_password(request.form['password'])
        role = request.form['role']
        date_created = get_current_date()
        date_modified = get_current_date()
        cursor.execute(f"SELECT COUNT(*) FROM users WHERE username = '{name}'")
        user_count = cursor.fetchone()[0]

        if user_count > 0:
    
            flash("Username already exists. Please choose a different username.","danger") 
            return render_template('register.html', username=name, email=email,password=password,role=role) # ... other form data ...)
        else:
    
            cursor.execute("INSERT INTO users (username, email,role, password, date_created, date_modified) VALUES (%s, %s, %s, %s, %s, %s)", [name, email, role,password, date_created, date_modified])
            conn.commit()
            return redirect(url_for('users'))
   

@app.route('/edit', methods=['GET', 'POST'])
@login_required
def edit():
    user_id = request.args.get('id')
    
    cursor.execute("SELECT * FROM users WHERE id = %s", (user_id,))
    data = cursor.fetchone()
    if request.method == 'POST':
       
        role = request.form['role']
        id = request.form['id']
        print("id:",id)
        cursor.execute("UPDATE users SET role = %s WHERE id = %s", (role, id))
        conn.commit()
        return redirect(url_for('users'))
    return render_template('profileedit.html', user=data)

@app.route('/delete')
@login_required
def delete():
    user_id = request.args.get('id')
    logged_id = current_user.get_id()
    if(user_id == logged_id):
        flash("Cannot delete a logged in user","warning")
        #return False
    else:
        cursor.execute("DELETE FROM users WHERE id = %s", (user_id,))
        conn.commit()
    
    return redirect(url_for('users'))

def hash_password(password):
    hashed_password = bcrypt.generate_password_hash(password).decode('utf-8')
    return hashed_password

@app.route('/logout')
@login_required
def logout():
    session.clear()
    logout_user()
    
    #flash('You have been logged out.')
    
    # Create a response with cache control headers to prevent caching
    response = make_response(redirect(url_for('loginPage', _cache=random.random())))
    response.headers['Cache-Control'] = 'no-store, no-cache, must-revalidate, max-age=0'
    response.headers['Pragma'] = 'no-cache'
    response.headers['Expires'] = '-1'
    response.headers['Vary'] = 'Cookie'
    
    return response


# Load crop values from the JSON file
with open('encoded_values_mapping.json') as f:
    crop_values = json.load(f)

# Function to predict yield for each crop type and select the one with the highest yield
def predict_yield_for_crops(features, model):
    # Array to store predicted yields for each crop
    predicted_yields = []
    # feature_names = list(features.keys())
    feature_names = ['Area', 'Item','Year', 'average_rain_fall_mm_per_year', 'pesticides_tonnes', 'avg_temp']
    # features_array = pd.DataFrame(features_array, columns=feature_names)
    # Loop through each crop value
    
    for crop, value in crop_values['Item'].items():
        features_copy = features.copy() 
       
        # Set the crop value in the features
        #print(f"{crop}, {value}")
        features['Item'] = value
        # Reshape features to a 2D array with a single row
        print("Current features:", features)
        features_array = np.array(list(features.values())).reshape(1, -1)
        features_array = pd.DataFrame(features_array, columns=feature_names)
        # Make prediction for the current crop
        prediction = model.predict(features_array)
        # Print prediction for debugging
        print("Prediction for", crop, ":", prediction)
        predicted_yields.append(round(prediction[0],2))
        # prediction = ""  

    return predicted_yields


@app.route('/predict',methods=['POST'])
@login_required
def predictoutput():

    if request.method == 'POST':
        # Get the form data from the request
        features = []
        rainfall = float(request.form['average_rain_fall_mm_per_year'])
        temperature = float(request.form['avg_temp'])
        pesticides = float(request.form['pesticides_tonnes'])
        item = float(request.form['Item'])
        country = float(request.form['Area'])
        year = float(request.form['Year'])
        date = get_current_date()
        current_crop =  list(crop_values['Item'].keys())[int(item)]
        session['current_crop'] = current_crop
        # print("item",item)
        # print("country",country)
        feature_names = ['Area', 'Item', 'Year', 'average_rain_fall_mm_per_year', 'pesticides_tonnes', 'avg_temp']
        features2 = {
            'Area' : country,
            'Item': 0,
            'Year': year,
            'average_rain_fall_mm_per_year':rainfall,
            'pesticides_tonnes':pesticides,
            'avg_temp':temperature}
        
        features =[country,item,year,rainfall,pesticides,temperature]
        # features =[0,8,1991,89,2461.8,16.26]
        
        # Convert the list to a numpy array and reshape it
        features_array = np.array(features).reshape(1, -1)
        # # Set feature names to the features array
        features_array = pd.DataFrame(features_array, columns=feature_names)
        # Make prediction using the loaded model
        prediction = model.predict(features_array)
        # print("prediction",prediction[0])
        predicted_yields = predict_yield_for_crops(features2, model)
        crop_predictions = []
        # Loop through each predicted yield
        for i, yield_value in enumerate(predicted_yields):
            # Get the crop name corresponding to the current index
            crop_name = list(crop_values['Item'].keys())[i]
            # Append crop name and predicted yield to the list
            crop_predictions.append((crop_name, yield_value))
        max_yield_crop_index = np.argmax(predicted_yields)
        max_yield_crop = list(crop_values['Item'].keys())[max_yield_crop_index]
        predval=float(prediction[0])
        # print(date)
        print(predicted_yields)
        print(max_yield_crop)
        # print(type(rainfall, temperature, pesticides))
        # for var in features:
        #     print(f"The type of {var} is {type(var)}")
        # Render prediction on a new page
        cursor.execute("INSERT INTO predictions (rainfall, temperature, pesticides, item, country, year, predicition, date) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)", (rainfall, temperature, pesticides, item, country, year, predval, date))
        conn.commit()
        session['predicted_array'] = crop_predictions
        session['recommended'] = max_yield_crop
        session['prediction'] = round(predval,2)
    return redirect(url_for('prediction'))




def increment_counter():
    counter_file = 'counter.txt'
    
    
    # Read current counter value from file
    try:
        with open(counter_file, 'r') as file:
            counter = int(file.read().strip())
    except FileNotFoundError:
        # If file does not exist, create it and initialize counter to 0
        with open(counter_file, 'w') as file:
            counter=0
            file.write(str(counter))
    except ValueError:
        # If file exists but does not contain a valid integer, reset to 0
        with open(counter_file, 'w') as file:
            counter=0
            file.write(str(counter))

    # Increment counter
    counter += 1
    
    # Write updated counter value back to file
    with open(counter_file, 'w') as file:
        file.write(str(counter))
    
    return counter




@app.route('/reports')
@login_required
def reports():
    cursor.execute("SELECT * FROM predictions")
    data = cursor.fetchall()
    return render_template('reports.html', predictions=data)

@app.route('/generate_report')
@login_required
def generate_report():
    # Generate the PDF report using ReportLab
    response = make_response(create_pdf_report())
    response.headers['Content-Type'] = 'application/pdf'
    response.headers['Content-Disposition'] = 'attachment; filename=report.pdf'
    increment_counter()
    return response

def create_pdf_report():
    # Create a PDF document
    pdf_buffer = BytesIO()
    pdf = SimpleDocTemplate(pdf_buffer, pagesize=letter)

    # Define a custom page template with fixed body height
    body_height = 600  # Adjust as needed
    page_template = PageTemplate(id='body', frames=Frame(inch, inch, 7 * inch, body_height, id='body_frame'))
    pdf.addPageTemplates([page_template])

    # Define a page footer
    def page_footer(canvas, doc):
        canvas.saveState()
        # Draw horizontal line at the bottom
        canvas.line(inch, inch, 7 * inch, inch)
        # Add copyright notice
        canvas.setFont('Helvetica', 9)
        text_width = canvas.stringWidth("Agricision 2024 All Copyrights Reserved", 'Helvetica', 9)
        canvas.drawString((7 * inch - text_width) / 2, 0.25 * inch, "Agricision 2024 All Copyrights Reserved")
        canvas.restoreState()

    # Add the page footer to the document
    pdf.addPageTemplates([PageTemplate(id='footer', frames=Frame(inch, 0.5 * inch, 7 * inch, 0.5 * inch, id='footer_frame', showBoundary=0), onPage=page_footer)])

    cursor.execute("SELECT * FROM predictions")
    report_data = cursor.fetchall()
    # Data for the table
    data = [
        ['ID', 'Country', 'Item', 'Pesticides', 'Temperature', 'Year', 'Rainfall', 'Prediction', 'Date']
    ]

    for item in report_data:
        data.append([
            item[0],
            item[1],
            item[2],
            item[3],
            item[4],
            item[5],
            item[6],
            item[7],
            item[8]  # Format date as needed
        ])
    # Create a table
    table = Table(data)

    # Add style to the table
    style = TableStyle([('ALIGN', (0, 0), (-1, -1), 'CENTER'),
                        ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
                        ('BACKGROUND', (0, 0), (-1, 0), colors.green),
                        ('TEXTCOLOR', (0, 0), (-1, 0), colors.whitesmoke),
                        ('BACKGROUND', (0, 1), (-1, -1), colors.beige),
                        ('GRID', (0, 0), (-1, -1), 1, colors.black),
                        ('LINEABOVE', (0,0), (-1,0), 1, colors.black)])  # Add line above column headers
    table.setStyle(style)

    # Current date
    current_date = datetime.now().strftime("%Y-%m-%d")

    # Define the elements
    elements = []

    # Get the directory path of the current file
    current_directory = os.path.dirname(os.path.abspath(__file__))

    # Construct the relative path to the logo
    logo_path = os.path.join(current_directory, 'static/images/logo.PNG')

    # Add logo
    logo = Image(logo_path, width=200, height=100)
    elements.append(logo)

    # Define paragraph style for tagline
    tagline_style = ParagraphStyle(name='Tagline', fontSize=12, alignment=1)
    # Define paragraph style for prediction report
    prediction_report_style = ParagraphStyle(name='PredictionReport', fontSize=12, alignment=1)

    # Add tagline
    elements.append(Paragraph("Pioneering precision agriculture", prediction_report_style))

    # Add date and report title
    elements.append(Paragraph("Date: {}".format(current_date), tagline_style))
    elements.append(Paragraph("Prediction Report", tagline_style))

    # Add empty line before the table
    elements.append(Paragraph("\n\n\n\n", tagline_style))

    # Add table
    elements.append(table)

    # Build PDF document
    pdf.build(elements, canvasmaker=NumberedCanvas)

    return pdf_buffer.getvalue()

class NumberedCanvas(canvas.Canvas):
    def __init__(self, *args, **kwargs):
        canvas.Canvas.__init__(self, *args, **kwargs)
        self._saved_page_states = []

    def showPage(self):
        self._saved_page_states.append(dict(self.__dict__))
        self._startPage()

    def save(self):
        """add page info to each page (page x of y)"""
        num_pages = len(self._saved_page_states)
        for state in self._saved_page_states:
            self.__dict__.update(state)
            self.draw_page_number(num_pages)
            canvas.Canvas.showPage(self)
        canvas.Canvas.save(self)

    def draw_page_number(self, page_count):
        self.setFont("Helvetica", 9)
        self.drawRightString(7.5 * inch, 0.75 * inch, "Page %d of %d" % (self._pageNumber, page_count))

if __name__ == "__main__":
    app.run(debug=True,port=8001)
