import re
import urllib.request

# La URL a la qual voles fer la sol·licitud
url = 'https://www.eltiempo.es/palamos.html'
data = urllib.request.urlopen(url)

# Obtenir el conjunt de caràcters
charset = data.headers.get_content_charset()

# Llegir el contingut HTML de la pàgina
html = data.read().decode(charset)

# Trobar la posició del 'dataLayer'
initial_position = html.find('dataLayer = [{')
final_position = html.find('];', initial_position)

# Extraure el contingut del 'dataLayer'
data_layer_content = html[initial_position:final_position + 1]

# Usar expressions regulars per obtenir la informació que necessitem
temperature = re.search(r"'currentTemperature':'(.*?)'", data_layer_content)
wind_speed = re.search(r"'windSpeed':'(.*?)'", data_layer_content)

# La humitat, hora de sortida i posta de sol no estan explícites en aquest fragment
# Posa com a mostra a sota si hi ha altres dades disponibles o una forma més específica d'obtenir-les
humidity = "No disponible"  # Exemple si la informació de la humitat no està en aquest fragment
sunrise = "No disponible"  # Exemple per obtenir l'hora de sortida del sol si estigués disponible
sunset = "No disponible"  # Exemple per obtenir l'hora de posta del sol si estigués disponible

if temperature:
    temperature = temperature.group(1)
else:
    temperature = "No disponible"

if wind_speed:
    wind_speed = wind_speed.group(1)
else:
    wind_speed = "No disponible"

# Mostrar el resultat
print(f"Temperature: {temperature}")
print(f"Wind Speed: {wind_speed}")
print(f"Humidity: {humidity}")
print(f"Sunrise: {sunrise}")
print(f"Sunset: {sunset}")
