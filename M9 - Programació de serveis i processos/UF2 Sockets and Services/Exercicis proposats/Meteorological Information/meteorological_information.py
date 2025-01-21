import http.server
import re
import socketserver
import urllib.request

citys = ["barcelona", "girona", "tarragona", "lleida", "palamos"]

def get_weather():
    city_temperatures = []

    for city in citys:
        try:
            url = f"https://www.eltiempo.es/{city}.html"
            data = urllib.request.urlopen(url)
            charset = data.headers.get_content_charset()
            html = data.read().decode(charset)


            if "'currentTemperature':'" in html:
                part1 = html.split("'currentTemperature':'")[1]  # Agafar primer part despres de currentTemperature (que sera el nombre)
                temperature = part1.split("'")[0]
                city_temperatures.append((city.capitalize(), temperature))
            else:
                city_temperatures.append((city.capitalize(), "N/A"))


        except Exception as e:
            city_temperatures.append((city.capitalize(), "Error"))

    return city_temperatures

def update_html(city_temperatures):
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
    
    for city, temperature in city_temperatures:
        content += f"<li>{city}: {temperature}</li>\n"

    final_html = html_template.format(content = content)

    with open("index.html", "w", encoding="utf-8") as f:
        f.write(final_html)


def run_server() :
    address = ('127.0.0.1', 8000)
    handler = http.server.SimpleHTTPRequestHandler
    httpd = socketserver.TCPServer(address, handler)

    print(f"serving on port {address[1]}")

    try:
        httpd.serve_forever()
    except KeyboardInterrupt:
        print("Interrupted!")

if __name__ == '__main__':
    city_temperatures = get_weather()
    update_html(city_temperatures)
    run_server()
