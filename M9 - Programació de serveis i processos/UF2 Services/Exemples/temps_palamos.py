import urllib.request

url = 'https://www.eltiempo.es/palamos.html'
data = urllib.request.urlopen(url)
charset = data.headers.get_content_charset()
html = data.read().decode(charset)

initial_position = html.find('dataLayer = [{')
final_position = html.find(';', initial_position)
print(html[initial_position:final_position])