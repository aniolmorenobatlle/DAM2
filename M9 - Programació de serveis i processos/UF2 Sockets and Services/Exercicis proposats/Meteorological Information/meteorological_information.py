import http.server
import socketserver
import time

import requests

citys = ["barcelona", "girona", "tarragona", "lleida", "palamos"]
api_key = "9b25e0adb03146d6a5b144231252201"

def get_weather():
    city_data = []

    for city in citys:
        try:
            url = f"https://api.weatherapi.com/v1/current.json?key={api_key}&q={city}&aqi=no"
            response = requests.get(url)
            data = response.json()

            temperature = data['current']['temp_c']
            humidity = data['current']['humidity']
            wind_speed_kmh = data['current']['wind_kph']

            city_data.append((city.capitalize(), temperature, humidity, wind_speed_kmh))
            
        except Exception:
            city_data.append((city.capitalize(), "Error", "Error"))

    return city_data


def update_html(city_data):
    html_template = """
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Weather Report</title>
    </head>
    <body>
        <h1>Weather Report</h1>
        <ul>
            {content}
        </ul>
    </body>
</html>
    """

    content = ""
    for city, temperature, humidity, wind_speed in city_data:
        content += f"""
            <li>
                <span style='font-size:20px; font-weight: bold;'>{city}</span>
                <ul>
                    <li>Temperatura: { temperature }°C</li>
                    <li>Humitat: { humidity }%</li>
                    <li>Velocitat del vent: { wind_speed } km/h</li>
                </ul>
            </li>
        """

    final_html = html_template.format(content=content)

    with open("index.html", "w", encoding="utf-8") as f:
        f.write(final_html)


def run_server():
    address = ('127.0.0.1', 8000)
    handler = http.server.SimpleHTTPRequestHandler
    httpd = socketserver.TCPServer(address, handler)

    print(f"Serving on port {address[1]}")

    try:
        httpd.serve_forever()
    except KeyboardInterrupt:
        print("Interrupted!")


if __name__ == '__main__':
    print("Starting server...\n")

    city_data = get_weather()
    update_html(city_data)
    run_server()
