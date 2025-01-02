# Create a Python program (download_multiple_files_with_threads.py) that performs the same work as the previous program using threading (one main and one for each image to download). Indicate whether the execution time of the program that uses three threads is better than that which only uses one.

import threading as th
import requests

images_to_download = [
    {"url": "https://esahubble.org/media/archives/images/original/heic1502a.psb", "filename": "andromeda.psb"},
    {"url": "https://esahubble.org/media/archives/images/original/heic1909a.tif","filename": "universe.tif"},
    {"url": "https://esahubble.org/media/archives/images/original/heic1901a.tif", "filename": "triangulum.tif"},
    {"url": "https://esahubble.org/media/archives/images/original/heic1620a.tif", "filename": "goods_south.tif"}
]


def download_image(file_info):
    file_url = f"{file_info['url']}"
    file_name = file_info['filename']

    print(f"Descarregant imatge {file_name}")

    r = requests.get(file_url, headers={'user-agent': 'Mozilla/5.0'})

    with open(file_name, 'wb') as f:
        f.write(r.content)

    print(f"Imatge {file_name} descarregada!")


if __name__ == '__main__':

    threads = []

    for file_info in images_to_download:

        t = th.Thread(target=download_image, args=(file_info,))
        threads.append(t)
        t.start()


    for t in threads:
        t.join()

    print("Totes les imatges descarregades!!")
