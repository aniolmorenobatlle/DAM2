import requests

images_to_download = [
    {"url": "https://esahubble.org/media/archives/images/original/heic1502a.psb", "filename": "andromeda.psb"},
    {"url": "https://esahubble.org/media/archives/images/original/heic1909a.tif","filename": "universe.tif"},
    {"url": "https://esahubble.org/media/archives/images/original/heic1901a.tif", "filename": "triangulum.tif"},
    {"url": "https://esahubble.org/media/archives/images/original/heic1620a.tif", "filename": "goods_south.tif"}
]

for file_info in images_to_download:
    file_url = f"{file_info['url']}"
    file_name = file_info['filename']
    
    r = requests.get(file_url, headers={'user-agent': 'Mozilla/5.0'})

    with open(file_name, 'wb') as f:
        f.write(r.content)
